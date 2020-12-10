import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';
import { SaleModel } from './sale.model';

@Component({
  selector: 'jhi-dashboard-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['overview.component.scss'],
})
export class DashboardOverviewComponent implements OnInit {
  @Input() topSellers?: SaleModel[];
  @Input() showOverview = true;
  @Output() hideOverviewEmitter = new EventEmitter<string>();

  ngOnInit(): void {
    this.topSellers = [
      {
        name: 'Restaurant Promo',
        amount: 25852,
      },
      {
        name: 'Corporate Presentation',
        amount: 225326,
      },
      {
        name: 'Wedding Invitation',
        amount: 15826,
      },
      {
        name: 'Restaurant Promo',
        amount: 12568,
      },
    ];
  }
  hideOverview(): void {
    this.hideOverviewEmitter.emit('toggle');
  }
}
