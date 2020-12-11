import { Component, Inject, OnInit, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-db-main-panel',
  templateUrl: './db-main-panel.component.html',
  styleUrls: ['db-main-panel.scss'],
})
export class DbMainPanelComponent implements OnInit {
  constructor(@Inject(DOCUMENT) private document: Document, private renderer: Renderer2) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'dashboard-active');
  }
}
