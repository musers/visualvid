/* eslint-disable @typescript-eslint/camelcase */
import { Component, Input, OnInit, ViewEncapsulation, AfterViewInit, Inject, Optional } from '@angular/core';
import videojs from 'video.js';
import { RazorpayService } from 'app/shared/payment/razorpay/razorpay-service';
import { OrderService } from 'app/order/order.service';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { VideoItemComponent } from 'app/videodesigns/videoitem/videoitem.component';
import { CountryService } from 'app/shared/country.service';
import { OrderRequest } from 'app/order/order-request.model';

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
export class ItemComponent implements OnInit, AfterViewInit {
  public player?: videojs.Player;

  @Input()
  item?: AdminMedia = {
    slides: [],
  };
  isIpIndian = true;
  advCustomizationPriceChecked = false;
  premumDeliveryPriceChecked = false;
  pricing: Pricing= {};
  matDialogRef?: MatDialogRef<VideoItemComponent>;
  constructor(
    private itemService: ItemService,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private countryService: CountryService,
    private razorpayService: RazorpayService,
    private orderService: OrderService,
    private pricingService: PricingService,
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
    this.razorpayService.onPaymentSuccess.subscribe((resp: any)=> {
      if(resp && resp.length >0 ){
      // TODO need to get itemid from resp and append it to the end of following line
        window.location.href= '/customer/upload/'+ resp[0];
      }
    })
  }
  ngAfterViewInit(): void {
    if (this.item && this.item.divId) {
      //       this.player = videojs(document.getElementById('item-' + this.item.divId), {});
    }
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
    return categoryId? categoryId.replace('_',' '): '';
  }
  buyNow(): void {
    const orderRequest = this.prepareOrderRequest();
    console.log('buyNow');
    this.orderService.createPaymentOrder(orderRequest).subscribe(data => {
      console.log(data);
      const options = {
        description: 'Foo Description',
        key: data.razorPayKey,
        order_id: data.razorPayOrderId,
        amount: data.amount,
        name: 'Foo',
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
    });
  }
  computePrice(): void {
    console.log(this.advCustomizationPriceChecked)
    if(this.item && this.item.id){
       const itemCustomization = {
            adminMediaId: this.item.id,
            optedForAdvCustomization: this.advCustomizationPriceChecked,
            optedForPremumDelivery: this.premumDeliveryPriceChecked,
            currencyCode: this.isIpIndian ? 'INR': 'USD'
          };
       this.pricingService.computePricing(itemCustomization).subscribe((res: Pricing) => {
          if(res){
            console.log(this.pricing);
            this.pricing = res;
          }
       });
    }
  }
  prepareOrderRequest(): OrderRequest {
    const itemCustomization = {
      adminMediaId: this.item.id,
      optedForAdvCustomization: this.advCustomizationPriceChecked,
      optedForPremumDelivery: this.premumDeliveryPriceChecked,
      currencyCode: this.isIpIndian ? 'INR': 'USD'
    };

    const orderRequest = {
      couponCode : '',
      currencyCode: this.isIpIndian ? 'INR': 'USD',
      itemCustomizations : [itemCustomization]
    };
    return orderRequest;
  }
}
