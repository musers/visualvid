import { Component, Inject, OnInit, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.scss'],
})
export class DashboardComponent implements OnInit {

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private renderer: Renderer2) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'dashboard-active');
  }
}
