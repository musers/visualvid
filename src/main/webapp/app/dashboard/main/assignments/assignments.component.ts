import { Component, Input, OnInit } from '@angular/core';
import { AssignmentsModel } from './assignments.model';

@Component({
  selector: 'jhi-assignments',
  templateUrl: './assignments.component.html',
  styleUrls: ['assignments.scss'],
})
export class DashboardAssignmentsComponent implements OnInit {
  @Input() assignments?: AssignmentsModel[];
  constructor() {}

  ngOnInit(): void {
    this.assignments = [
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
