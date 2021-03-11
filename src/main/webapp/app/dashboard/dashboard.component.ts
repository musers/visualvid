import { Component, OnInit, Renderer2, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { OverviewService } from 'app/dashboard/overview/overview.service';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.scss'],
})
export class DashboardComponent implements OnInit {
  showOverview = false;

  constructor(@Inject(DOCUMENT) private document: Document, private renderer: Renderer2, private overviewService: OverviewService) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.getElementById('main'), 'dashboard-active');
    this.overviewService.showOverviewEmitter.subscribe((flag: string) => {
      this.showOverviewTab(flag);
    });
  }
  showOverviewTab(flag: any): void {
    if (flag === 'toggle') {
      this.showOverview = !this.showOverview;
    } else if (flag === 'true') {
      this.showOverview = true;
    } else if (flag === 'false') {
      this.showOverview = false;
    }
  }
}
