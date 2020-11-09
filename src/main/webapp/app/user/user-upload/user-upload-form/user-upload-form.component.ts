import { Component, Inject, OnInit, OnDestroy, Renderer2} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DOCUMENT } from '@angular/common';

import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';

@Component({
  selector: 'jhi-user-upload-form',
  templateUrl: './user-upload-form.component.html',
  styleUrls: ['./user-upload-form.component.scss'],
})
export class UserUploadFormComponent implements OnInit, OnDestroy {
  adminMedia?: AdminMedia;

  constructor(
    @Inject(DOCUMENT) private document: Document, private renderer: Renderer2,
    private adminMediaService: AdminMediaService,
    private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'customer-upload-active');
    const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
    if(adminMediaId){
      this.adminMediaService.get(adminMediaId).subscribe((res: AdminMedia) => {
        if (res != null) {
          console.log(res);
          this.adminMedia = res;
        }
      });
    }
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'customer-upload-active');
  }
  gotoPrev(): void {
    console.log('gotoPrev');
  }
  gotoNext(): void {
    console.log('gotoNext');
  }
}
