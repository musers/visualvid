import { Component, OnInit, TemplateRef, AfterViewInit } from '@angular/core';
import { OverviewService } from './overview.service';

@Component({
  selector: 'jhi-dashboard-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['overview.component.scss'],
})
export class DashboardOverviewComponent implements OnInit, AfterViewInit {
  overviewTemplate?: TemplateRef<any>;
  overviewData?: any;
  constructor(public overviewService: OverviewService) {}

  ngOnInit(): void {
    this.overviewService.updateOverviewTemplateEvt.subscribe((templateData: any) => {
      console.log('templateData in overview comp ts ', templateData);
      this.overviewTemplate = templateData.template;
      this.overviewData = templateData.data;
    });
  }
  ngAfterViewInit(): void {
    console.log('ngAfterViewInit');
  }
  hideOverview(): void {
    this.overviewService.showOverviewEmitter.emit('toggle');
  }
}
