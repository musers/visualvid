import { Component, Inject, OnInit, OnDestroy, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';

import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';

@Component({
  selector: 'jhi-user-upload-form',
  templateUrl: './user-upload-form.component.html',
  styleUrls: ['./user-upload-form.component.scss'],
})
export class UserUploadFormComponent implements OnInit, OnDestroy {
  adminMedia: AdminMedia = {};

  constructor(
    @Inject(DOCUMENT) private document: Document, private renderer: Renderer2,
    private adminMediaService: AdminMediaService) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'customer-upload-active');
    this.adminMediaService.get('41b62496-720b-47d4-988e-5f318119110e').subscribe((res: AdminMedia) => {
      if (res != null) {
        console.log(res);
        this.adminMedia = res;
      }
    });
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'customer-upload-active');
  }
}
