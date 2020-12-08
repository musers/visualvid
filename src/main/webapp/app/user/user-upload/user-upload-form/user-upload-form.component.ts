import { Component, Inject, OnInit, OnDestroy, Renderer2, Optional } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DOCUMENT } from '@angular/common';
import { JhiAlertService } from 'ng-jhipster';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';
import { UserTemplateService } from 'app/user/user-upload/user-template.service';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';
import { UserSlide } from './slide/user-slide.model';
import { UserTemplate } from './user-template.model';

@Component({
  selector: 'jhi-user-upload-form',
  templateUrl: './user-upload-form.component.html',
  styleUrls: ['./user-upload-form.component.scss'],
})
export class UserUploadFormComponent implements OnInit, OnDestroy {
  adminMedia?: AdminMedia;
  item: UserTemplate = {
    userSlides: [],
  };
  activeSlide: Slide = {
    slideItems: [],
  };
  activeTabIndex = 0;
  userSlides: Array<UserSlide> = [];
  hideSubmitPanel = true;
  userUploadSuccess = false;
  constructor(
    @Inject(DOCUMENT) private document: Document,
    private renderer: Renderer2,
    private adminMediaService: AdminMediaService,
    private userTemplateService: UserTemplateService,
    private alertService: JhiAlertService,
    private route: ActivatedRoute,
    @Optional() @Inject(MAT_DIALOG_DATA) data?: AdminMedia
  ) {
    console.log('injected data', data);
    this.adminMedia = data;
    this.updateUserSlides();
  }
  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'customer-upload-active');
    const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
    if (adminMediaId) {
      this.adminMediaService.get(adminMediaId).subscribe((res: AdminMedia) => {
        if (res != null) {
          this.adminMedia = res;
          this.item.adminMediaId = res.id;
          this.updateUserSlides();
        }
      });
    }
  }

  slideChanged(evt: any): void {
    if (this.adminMedia && this.adminMedia.slides) {
      this.activeSlide = this.adminMedia.slides[evt.index];
    }
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'customer-upload-active');
  }
  gotoPrev(): void {
    if (this.activeTabIndex > 0) {
      this.activeTabIndex--;
    }
  }
  gotoNext(): void {
    if (this.adminMedia && this.adminMedia.slides) {
      this.updateSubmitPanelTag();
      if (this.adminMedia?.slides?.length && this.activeTabIndex < this.adminMedia?.slides?.length - 1) {
        this.activeTabIndex++;
      }
    }
  }

  updateSubmitPanelTag(): void {
    if (this.adminMedia?.slides?.length && this.activeTabIndex === this.adminMedia?.slides?.length - 1) {
      this.hideSubmitPanel = false;
    }
  }
  cancel(): void {
    this.hideSubmitPanel = true;
  }
  saveCustomerUploadForm(): void {
    console.log(this.item);
    if (this.item.adminMediaId) {
      this.userTemplateService.save(this.item).subscribe(() => {
        //         this.alertService.addAlert({ type: 'success', msg: 'user.uploadform.saved.successfully', timeout: 5000, toast: true }, []);
        this.userUploadSuccess = true;
      });
    }
  }
  updateUserSlides(): void {
    this.item.userSlides = [];
    if (this.adminMedia && this.adminMedia.slides) {
      this.adminMedia.slides.forEach(slide => {
        this.item.userSlides.push(this.updateUserSlide(slide));
      });
    }
  }
  updateUserSlide(slide: Slide): any {
    const userSlide = {} as UserSlide;
    userSlide.adminSlideId = slide.id;
    userSlide.screenShotS3Key = slide.screenShotS3Key;
    userSlide.screenShotS3Url = slide.screenShotS3Url;
    userSlide.slideName = slide.slideName;
    userSlide.slideOrder = slide.slideOrder;
    userSlide.userSlideItems = [];
    slide.slideItems.forEach(si => {
      if (userSlide.userSlideItems) {
        userSlide.userSlideItems.push({
          adminSlideItemId: si.id,
          itemOrder: si.order,
          itemLabel: si.label,
          itemType: si.type,
        });
      }
    });
    return userSlide;
  }

  gotoHome(): void {
    window.location.href = '/';
  }
}
