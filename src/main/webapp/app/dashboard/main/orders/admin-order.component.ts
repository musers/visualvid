import { Component, Input, OnInit } from '@angular/core';
import { AdminOrderService } from './admin-order.service';
import { AdminOrderModel } from './admin-order.model';
// import { Pagination } from '../../../../../app/shared/util/request-util';
// import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrls: ['admin-order.scss'],
})
export class AdminOrderComponent implements OnInit {
  @Input() orders?: AdminOrderModel[];
  count: Number = 0;

  constructor(protected adminOrderService: AdminOrderService) {}

  ngOnInit(): void {
    //    this.adminVideoService.findAllByPage({
    //        page: 0,
    //        size: 10,
    //        sort: [],
    //       })
    //        .subscribe((res: HttpResponse<AdminVideoModel[]>) => {
    //                 if(res != null && res.body != null) {
    //                     this.videoDesigns = res.body;
    //                 }
    //        });

    this.adminOrderService.findAll().subscribe((res: AdminOrderModel[]) => {
      if (res != null) {
        this.orders = res;
        this.orders.forEach(ord => {
          ord.created = 'Sep23, 2020';
          ord.modified = 'Sep23, 2020';
          ord.views = 500;
          ord.sales = 250;
          ord.earnings = 75000;
        });
      }
    });
  }
  editOrder(ord: AdminOrderModel): void {
    window.location.href = '/admin/upload/' + ord.id;
  }
}
