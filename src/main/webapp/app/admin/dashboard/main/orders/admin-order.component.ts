import { Component, Input, OnInit } from '@angular/core';
import { AdminOrderModel } from './admin-order.model';

@Component({
  selector: 'jhi-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrls: ['admin-order.scss'],
})
export class AdminOrderComponent implements OnInit {
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
