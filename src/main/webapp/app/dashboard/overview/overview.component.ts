import { Component, Input, Output, OnInit, EventEmitter, TemplateRef } from '@angular/core';
import { SaleModel } from './sale.model';
import { OverviewService } from './overview.service';

@Component({
  selector: 'jhi-dashboard-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['overview.component.scss'],
})
export class DashboardOverviewComponent implements OnInit {
  @Input() topSellers?: SaleModel[];
  @Input() showOverview = true;
  @Output() hideOverviewEmitter = new EventEmitter<string>();

  overviewTemplate?: TemplateRef<any>;
  overviewData?: any;
  dashboardType = 'subscription';
  data = {
    id : ''
  };
  constructor(
    public overviewService: OverviewService
    ) {}

  ngOnInit(): void {
    this.overviewService.updateOverviewTemplateEvt.subscribe((templateData: any) =>{
      console.log('templateData in overview comp ts ',templateData);
      this.overviewTemplate = templateData.template;
      this.overviewData = templateData.data;
    });
    this.dashboardType = 'subscription';
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
