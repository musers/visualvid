import { Component, Input, OnInit } from '@angular/core';
import { OrderModel } from 'app/order/order.model';
import { OrderService } from 'app/order/order.service';

@Component({
  selector: 'jhi-dashboard-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['orders.scss'],
})
export class DashboardOrdersComponent implements OnInit {
  @Input() orders?: OrderModel[];
  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
  this.orderService.findAll().subscribe(data => {
        console.log(data);
        this.orders = data;
      });
  }
}
