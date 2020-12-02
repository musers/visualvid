/* eslint-disable @typescript-eslint/camelcase */
import { Component, Input, OnInit, ViewEncapsulation, AfterViewInit, Inject, Optional} from '@angular/core';
import videojs from 'video.js';
import { RazorpayService } from 'app/shared/payment/razorpay/razorpay-service';
import { PaymentService } from 'app/shared/payment/payment.service';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { VideoItemComponent } from 'app/videodesigns/videoitem/videoitem.component';
import { CountryService } from 'app/shared/country.service';

import {
  MatDialog,
  MatDialogRef
} from "@angular/material/dialog";

import { AdminMedia } from '../../app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { ItemService } from '../../app/item/item.service';

@Component({
  selector: 'jhi-item',
  templateUrl: './item.component.html',
  styleUrls: ['item.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ItemComponent implements OnInit, AfterViewInit {
  public player?: videojs.Player;

  @Input() item?: AdminMedia = {
    slides: [],
  };
  isIpIndian  = true;
  matDialogRef ?: MatDialogRef<VideoItemComponent>;
  constructor(private itemService: ItemService,
    private route: ActivatedRoute,
    private matDialog: MatDialog,
    private countryService: CountryService,
    private razorpayService: RazorpayService,
    private paymentService: PaymentService,
    @Optional() @Inject(MAT_DIALOG_DATA) data?: AdminMedia
  ) {
    console.log('item',data)
    this.item = data;
    this.formatTags();
    this.isIpIndian = countryService.isIpIndian();
  }

  ngOnInit(): void {
    const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
    if(adminMediaId){
      this.itemService.get(adminMediaId).subscribe((res: AdminMedia) => {
        if (res) {
          this.item = res;
          this.item.divId = this.item.id;
          this.formatTags();
        }
      });
    }
  }
  ngAfterViewInit(): void {
    if (this.item && this.item.divId) {
//       this.player = videojs(document.getElementById('item-' + this.item.divId), {});
    }
  }
  formatTags() : void {
    if(this.item && this.item.tags){
      this.item.tagList = this.item.tags.split(',').map(
        function(t: string): string{
          return t.replace(/@@/g,'');
        }
      )
    }
  }
  playVideo(videoUrl: string): void {
    console.log(videoUrl);
      this.matDialogRef = this.matDialog.open(VideoItemComponent, {
      data: {
          item: this.item,
          config: {
            autoplay: true,
          }
        },
        width: '60%'
      });
  }
  pay(): void {
    console.log('pay');
    const orderId = 'test5'
      this.paymentService.createPaymentOrder(orderId).subscribe(data => {
         console.log(data);
      const options = {
          description: 'Foo Description',
          key: 'rzp_test_wTzvK2HN5T7KjZ',
          order_id: data.razorPayOrderId,
          amount: data.amount,
          name: 'Foo',
          prefill: {
              email: 'test@test.com',
              contact: '+918087930476',
              name: 'Bala'
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
}
