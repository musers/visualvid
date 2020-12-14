import { Component, Input, OnInit } from '@angular/core';
import { AdminOrderModel } from './orders.model';

@Component({
  selector: 'jhi-admin-order',
  templateUrl: './orders.component.html',
  styleUrls: ['orders.scss'],
})
export class DashboardOrdersComponent implements OnInit {
  @Input() adminOrders?: AdminOrderModel[];
  constructor() {}

  ngOnInit(): void {
    this.adminOrders = [
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
