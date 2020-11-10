import { Component, Inject, OnInit, OnDestroy, Renderer2} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DOCUMENT } from '@angular/common';

import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';

@Component({
  selector: 'jhi-user-upload-form',
  templateUrl: './user-upload-form.component.html',
  styleUrls: ['./user-upload-form.component.scss'],
})
export class UserUploadFormComponent implements OnInit, OnDestroy {
  adminMedia?: AdminMedia;
  activeSlide?: Slide;
  activeTabIndex = 0;
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

  slideChanged(evt: any): void {
    if(this.adminMedia && this.adminMedia.slides){
      this.activeSlide = this.adminMedia.slides[evt.index];
    }
    console.log(this.activeSlide);
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'customer-upload-active');
  }
  gotoPrev(): void {
    if(this.activeTabIndex > 0){
      this.activeTabIndex--;
    }
  }
  gotoNext(): void {
    if(this.adminMedia && this.adminMedia.slides){
      if(this.activeTabIndex < this.adminMedia?.slides?.length-1){
        this.activeTabIndex++;
      }
    }
  }
}
