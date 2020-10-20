import { Component, Inject, OnInit, OnDestroy, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit, OnDestroy {
  constructor(@Inject(DOCUMENT) private document: Document, private renderer: Renderer2) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'customer-upload-active');
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'customer-upload-active');
  }
}
