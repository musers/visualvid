import { Component, OnInit, Input } from '@angular/core';

import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';
import { OrderSlideItem } from 'app/order/order.model';
@Component({
  selector: 'jhi-user-upload-form-slide',
  templateUrl: './user-slide.component.html',
  styleUrls: ['./user-slide.component.scss'],
})
export class UserSlideComponent implements OnInit {
  @Input() adminMediaSlide?: Slide;
  @Input() orderSlideItems?: Array<OrderSlideItem> = [];

  constructor() {
    if (!this.orderSlideItems) {
      this.orderSlideItems = [];
    }
  }
  ngOnInit(): void {}
}
