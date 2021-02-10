import { Component, Inject, OnInit, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-dashboard-main',
  templateUrl: './main.component.html',
  styleUrls: ['main.component.scss'],
})
export class DashboardMainComponent implements OnInit {
  constructor(@Inject(DOCUMENT) private document: Document, private renderer: Renderer2) {}
  ngOnInit(): void {
//     this.renderer.addClass(this.document.body, 'dashboard-active');
  }
}
