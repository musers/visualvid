import { Component, Inject, OnInit, OnDestroy, Renderer2, Optional, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DOCUMENT } from '@angular/common';
import { JhiAlertService } from 'ng-jhipster';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';
import { OrderService } from 'app/order/order.service';
import { OrderModel, OrderSlide } from 'app/order/order.model';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';

@Component({
  selector: 'jhi-user-upload-form',
  templateUrl: './user-upload-form.component.html',
  styleUrls: ['./user-upload-form.component.scss'],
})
export class UserUploadFormComponent implements OnInit, OnDestroy {
  @Input()
  adminMedia?: AdminMedia;
  item: OrderModel = {
    orderSlides: [],
  };
  activeSlide: Slide = {
    slideItems: [],
  };
  activeTabIndex = 0;
  hideSubmitPanel = true;
  userUploadSuccess = false;
  constructor(
    @Inject(DOCUMENT) private document: Document,
    private renderer: Renderer2,
    private adminMediaService: AdminMediaService,
    private orderService: OrderService,
    private alertService: JhiAlertService,
    private route: ActivatedRoute,
    @Optional() @Inject(MAT_DIALOG_DATA) data?: AdminMedia
  ) {
    console.log('injected data', data);
    this.adminMedia = data;
    this.updateOrderSlides();
  }
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'customer-upload-active');
    const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
    if (adminMediaId) {
      this.adminMediaService.get(adminMediaId).subscribe((res: AdminMedia) => {
        if (res != null) {
          this.adminMedia = res;
          this.loadOrder();
        }
      });
    }
    // TODO following needs to be executed after above
  }

  slideChanged(evt: any): void {
    if (this.adminMedia && this.adminMedia.slides) {
      this.activeSlide = this.adminMedia.slides[evt.index];
    }
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'customer-upload-active');
  }
  gotoPrev(): void {
    if (this.activeTabIndex > 0) {
      this.activeTabIndex--;
    }
  }
  gotoNext(): void {
    if (this.adminMedia && this.adminMedia.slides) {
      this.updateSubmitPanelTag();
      if (this.adminMedia?.slides?.length && this.activeTabIndex < this.adminMedia?.slides?.length - 1) {
        this.activeTabIndex++;
      }
    }
  }

  loadOrder(): void {
    // Update order
    const orderId = this.route.snapshot.paramMap.get('orderId');
    if (orderId && orderId !== 'new') {
      this.orderService.getForCustomerUpload(orderId).subscribe((order: OrderModel) => {
        if (order != null) {
          this.item = order;
        }
      });
    }
  }
  updateSubmitPanelTag(): void {
    if (this.adminMedia?.slides?.length && this.activeTabIndex === this.adminMedia?.slides?.length - 1) {
      this.hideSubmitPanel = false;
    }
  }
  cancel(): void {
    this.hideSubmitPanel = true;
  }
  saveCustomerUploadForm(): void {
    console.log(this.item);
    if (this.item.adminMediaId) {
      this.orderService.saveCustomerUpload(this.item).subscribe(() => {
        console.log('saveCustomerUpload');
        //         this.alertService.addAlert({ type: 'success', msg: 'user.uploadform.saved.successfully', timeout: 5000, toast: true }, []);
        this.userUploadSuccess = true;
      });
    }
  }
  updateOrderSlides(): void {
    this.item.orderSlides = [];
    if (this.adminMedia && this.adminMedia.slides) {
      this.adminMedia.slides.forEach(slide => {
        this.item.orderSlides.push(this.updateOrderSlide(slide));
      });
    }
  }
  updateOrderSlide(slide: Slide): any {
    const orderSlide = {} as OrderSlide;
    orderSlide.adminSlideId = slide.id;
    orderSlide.screenShotS3Key = slide.screenShotS3Key;
    orderSlide.screenShotS3Url = slide.screenShotS3Url;
    orderSlide.slideName = slide.slideName;
    orderSlide.slideOrder = slide.slideOrder;
    orderSlide.orderSlideItems = [];
    slide.slideItems.forEach(si => {
      if (orderSlide.orderSlideItems) {
        orderSlide.orderSlideItems.push({
          adminSlideItemId: si.id,
          itemOrder: si.order,
          itemLabel: si.label,
          itemType: si.type,
        });
      }
    });
    return orderSlide;
  }

  gotoHome(): void {
    window.location.href = '/';
  }
}
