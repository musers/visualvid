import { Component, Inject, OnInit, OnDestroy, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'jhi-user-upload',
  templateUrl: './user-upload.component.html',
  styleUrls: ['./user-upload.component.scss'],
})
export class UserUploadComponent implements OnInit, OnDestroy {
  constructor(@Inject(DOCUMENT) private document: Document, private renderer: Renderer2) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'customer-upload-active');
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'customer-upload-active');
  }
}
