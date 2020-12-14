import { Component, Input, OnInit } from '@angular/core';
import { OrderModel } from 'app/order/order.model';

@Component({
  selector: 'jhi-dashboard-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['orders.scss'],
})
export class DashboardOrdersComponent implements OnInit {
  @Input() orders?: OrderModel[];
  constructor() {}

  ngOnInit(): void {
    this.orders = [
      {
        saleId: '0001',
        orderId: '0001',
        name: 'RestaurantPromoVideo',
        assignTo: 'Bobba Anil',
        status: 'Assigned',
        timeLeft: 120,
        action: 'select',
      },
    ];
  }
}
