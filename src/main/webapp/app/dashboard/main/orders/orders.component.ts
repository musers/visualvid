import { Component, Input, OnInit } from '@angular/core';
import { OrderService } from 'app/order/order.service';
import { OrderModel } from 'app/order/order.model';

@Component({
  selector: 'jhi-dashboard-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['orders.component.scss'],
})
export class DashboardOrdersComponent implements OnInit {
  @Input() orders?: OrderModel[];
  count: Number = 0;

  constructor(protected adminOrderService: OrderService) {}

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

    this.adminOrderService.findAll().subscribe((res: OrderModel[]) => {
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
  editOrder(ord: OrderModel): void {
    window.location.href = '/admin/upload/' + ord.id;
  }
}
