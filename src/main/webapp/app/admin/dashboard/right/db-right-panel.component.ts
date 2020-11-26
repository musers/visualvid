import { Component, Input, OnInit } from '@angular/core';
import { SaleModel } from './sale.model';

@Component({
  selector: 'jhi-db-right-panel',
  templateUrl: './db-right-panel.component.html',
  styleUrls: ['db-right-panel.scss'],
})
export class DashboardRightComponent implements OnInit {
  @Input() topSellers?: SaleModel[];

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
}
