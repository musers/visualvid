import { Component, Inject, OnInit, OnDestroy, Renderer2} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DOCUMENT } from '@angular/common';

import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';
import { UserSlide } from './slide/user-slide.model';

@Component({
  selector: 'jhi-user-upload-form',
  templateUrl: './user-upload-form.component.html',
  styleUrls: ['./user-upload-form.component.scss'],
})
export class UserUploadFormComponent implements OnInit, OnDestroy {
  adminMedia?: AdminMedia;
  activeSlide: Slide = {
    slideItems : []
  };
  activeTabIndex = 0;
  userSlides: Array<UserSlide> = [];
  hideSubmitPanel = true;
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
          this.adminMedia = res;
          this.updateUserSlides();
        }
      });
    }
  }

  slideChanged(evt: any): void {
    if(this.adminMedia && this.adminMedia.slides){
      this.activeSlide = this.adminMedia.slides[evt.index];
    }
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
      this.updateSubmitPanelTag();
      if(this.adminMedia?.slides?.length && this.activeTabIndex < this.adminMedia?.slides?.length-1){
        this.activeTabIndex++;
      }
    }
  }

  updateSubmitPanelTag(): void{
    if(this.adminMedia?.slides?.length && this.activeTabIndex === this.adminMedia?.slides?.length-1){
      this.hideSubmitPanel = false;
    }
  }
  cancel(): void {
    this.hideSubmitPanel = true;
  }
  saveCustomerUploadForm(): void{
    console.log(this.userSlides);
  }
  updateUserSlides(): void {
    this.userSlides = [];
    if(this.adminMedia && this.adminMedia.slides){
      this.adminMedia.slides.forEach(slide => {
        this.userSlides.push(this.updateUserSlide(slide))
      });
    }
  }
  updateUserSlide(slide: Slide): any {
    const userSlide = {} as UserSlide;
    userSlide.adminId = slide.id;
    userSlide.userSlideItems = []
    slide.slideItems.forEach(si => {
    if(userSlide.userSlideItems){
      userSlide.userSlideItems.push({
        adminId: si.id
      })
      }
    })
    return userSlide;
  }
}
