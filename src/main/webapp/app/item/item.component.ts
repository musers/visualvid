/* eslint-disable @typescript-eslint/camelcase */
import { Component, Input, OnInit, ViewEncapsulation, Inject, Optional, ViewChild } from '@angular/core';
import { TemplatePortalDirective } from '@angular/cdk/portal';
import videojs from 'video.js';
import { RazorpayService } from 'app/shared/payment/razorpay/razorpay-service';
import { OrderService } from 'app/order/order.service';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { VideoItemComponent } from 'app/videodesigns/videoitem/videoitem.component';
import { CountryService } from 'app/shared/country.service';
import { OrderRequest } from 'app/order/order-request.model';
import { OverlayService } from 'app/shared/overlay/overlay.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { ItemService } from 'app/item/item.service';
import { PricingService } from 'app/shared/pricing/pricing.service';
import { Pricing } from 'app/shared/pricing/pricing.model';

@Component({
  selector: 'jhi-item',
  templateUrl: './item.component.html',
  styleUrls: ['item.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ItemComponent implements OnInit {
  public player?: videojs.Player;

  @Input()
  item?: AdminMedia = {
    slides: [],
  };
  isIpIndian = true;
  advCustomizationPriceChecked = false;
  premumDeliveryPriceChecked = false;
  pricing: Pricing = {};
  couponCode = '';
  appliedCouponCode = '';
  couponError = '';
  couponSuccess = '';
  matDialogRef?: MatDialogRef<VideoItemComponent>;
  @ViewChild('buyNowTemplate') buyNowTemplate: TemplatePortalDirective;
  constructor(
    private itemService: ItemService,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private countryService: CountryService,
    private razorpayService: RazorpayService,
    private orderService: OrderService,
    private pricingService: PricingService,
    private overlayService: OverlayService,
    @Optional() @Inject(MAT_DIALOG_DATA) data?: AdminMedia
  ) {
    console.log('item', data);
    this.item = data;
    this.formatTags();
    this.isIpIndian = countryService.isIpIndian();
  }

  ngOnInit(): void {
    const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
    if (adminMediaId) {
      this.itemService.get(adminMediaId).subscribe((res: AdminMedia) => {
        if (res) {
          this.item = res;
          this.item.divId = this.item.id;
          this.formatTags();
          this.computePrice();
        }
      });
    }
    this.razorpayService.onPaymentSuccess.subscribe((orders: any) => {
      if (orders && orders.length > 0) {
        window.location.href = '/customer/upload/' + orders[0].adminMediaId + '/' + orders[0].id;
      }
    });
  }
  formatTags(): void {
    if (this.item && this.item.tags) {
      this.item.tagList = this.item.tags.split(',').map(function (t: string): string {
        return t.replace(/@@/g, '');
      });
    }
  }
  playVideo(videoUrl: string): void {
    console.log(videoUrl);
    this.matDialogRef = this.matDialog.open(VideoItemComponent, {
      data: {
        item: this.item,
        config: {
          autoplay: true,
        },
      },
      width: '60%',
    });
  }
  formatCategory(categoryId?: string): string {
    return categoryId ? categoryId.replace('_', ' ') : '';
  }
  buyNow(): void {
    this.overlayService.openTemplateOverlay(this.buyNowTemplate);
    const orderRequest = this.prepareOrderRequest();
    this.orderService.createPaymentOrder(orderRequest).subscribe(
      data => {
        console.log(data);
        this.overlayService.closeOverlay();
        const options = {
          description: 'VisualVid',
          key: data.razorPayKey,
          order_id: data.razorPayOrderId,
          amount: data.amount,
          name: 'Video',
          prefill: {
            email: 'test@test.com',
            contact: '+918087930476',
            name: 'Bala',
          },
          currency: data.currencyCode,
        };

        try {
          this.razorpayService.payWithRazor(options);
        } catch (e) {
          console.error(e);
        }
      },
      err => {
        this.overlayService.closeOverlay();
        this.couponError = this.getErrorMessage(err);
        this.couponSuccess = '';
      }
    );
  }
  computePrice(): void {
    console.log(this.advCustomizationPriceChecked);
    if (this.item && this.item.id) {
      this.pricingService.computePricing(this.getItemCustomization(this.appliedCouponCode)).subscribe(
        (res: Pricing) => {
          if (res) {
            console.log(this.pricing);
            this.pricing = res;
          }
        },
        err => {
          this.pricing = {};
          this.appliedCouponCode = '';
          this.couponSuccess = '';
          this.couponError = this.getErrorMessage(err);
          this.reloadPriceWithoutCoupon();
        }
      );
    }
  }
  applyCoupon(): void {
    const normalizedCouponCode = this.getNormalizedCouponCode();
    if (!normalizedCouponCode) {
      this.couponError = 'Enter a coupon code to apply.';
      this.couponSuccess = '';
      return;
    }

    if (this.item && this.item.id) {
      this.pricingService.computePricing(this.getItemCustomization(normalizedCouponCode)).subscribe(
        (res: Pricing) => {
          this.appliedCouponCode = normalizedCouponCode;
          this.couponCode = normalizedCouponCode;
          this.couponError = '';
          this.couponSuccess = 'Coupon applied successfully.';
          this.pricing = res;
        },
        err => {
          this.appliedCouponCode = '';
          this.couponError = this.getErrorMessage(err);
          this.couponSuccess = '';
          this.reloadPriceWithoutCoupon();
        }
      );
    }
  }
  removeCoupon(): void {
    this.couponCode = '';
    this.appliedCouponCode = '';
    this.couponError = '';
    this.couponSuccess = '';
    this.computePrice();
  }
  canApplyCoupon(): boolean {
    return this.getNormalizedCouponCode().length > 0;
  }
  prepareOrderRequest(): OrderRequest {
    const orderRequest = {
      couponCode: this.appliedCouponCode,
      currencyCode: this.isIpIndian ? 'INR' : 'USD',
      itemCustomizations: [this.getItemCustomization(this.appliedCouponCode)],
    };
    return orderRequest;
  }
  gotoHome(): void {
    window.location.href = '/';
  }
  private getItemCustomization(couponCode?: string): any {
    return {
      adminMediaId: this.item && this.item.id ? this.item.id : undefined,
      optedForAdvCustomization: this.advCustomizationPriceChecked,
      optedForPremumDelivery: this.premumDeliveryPriceChecked,
      currencyCode: this.isIpIndian ? 'INR' : 'USD',
      couponCode: couponCode || '',
    };
  }
  private getNormalizedCouponCode(): string {
    return this.couponCode ? this.couponCode.trim().toUpperCase() : '';
  }
  private reloadPriceWithoutCoupon(): void {
    if (this.item && this.item.id) {
      this.pricingService.computePricing(this.getItemCustomization()).subscribe((res: Pricing) => {
        this.pricing = res;
      });
    }
  }
  private getErrorMessage(error: any): string {
    if (error && error.error && error.error.detail) {
      return error.error.detail;
    }
    return 'Unable to apply coupon right now.';
  }
}
