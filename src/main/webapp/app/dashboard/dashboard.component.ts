import { Component, OnInit, Renderer2, Inject} from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.scss'],
})
export class DashboardComponent implements OnInit {
  showOverview = true;
  constructor(@Inject(DOCUMENT) private document: Document, private renderer: Renderer2) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.getElementById('main'), 'dashboard-active');
  }
  toggleOverview(evt: any) : void {
    console.log('toggle',evt);
    this.showOverview = !this.showOverview;
  }
}
