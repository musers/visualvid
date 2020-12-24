import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { JhiAlertService } from 'ng-jhipster';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TemplatePortalDirective } from '@angular/cdk/portal';

import { OverlayService } from 'app/shared/overlay/overlay.service';
import { FileUploadService } from 'app/fileupload/fileupload.service';
import { AdminMediaService } from '../admin-media.service';
import { AdminMedia } from './adminmedia.model';
import { AdminCategory } from 'app/admin/admin-upload/admin-upload-form/admincategory.model';
import { ItemComponent } from 'app/item/item.component';
import { PreviewComponent } from 'app/admin/admin-upload/preview/preview.component';
@Component({
  selector: 'jhi-admin-upload-form',
  templateUrl: './admin-upload-form.component.html',
  styleUrls: ['./admin-upload-form.component.scss'],
  animations: [
    trigger('fadeInOut', [
      state(
        'void',
        style({
          opacity: 0,
        })
      ),
      transition('void <=> *', animate(1000)),
    ]),
    trigger('EnterLeave', [
      state('flyIn', style({ transform: 'translateX(0)' })),
      transition(':leave', [animate('0.3s ease-out', style({ transform: 'translateX(100%)' }))]),
    ]),
  ],
})
export class AdminUploadFormComponent implements OnInit {
  @Input()
  item: AdminMedia = {
    slides: [],
  };
  @ViewChild('description') divRef?: ElementRef;
  categories: AdminCategory[] = [];
  errors: any = {};
  disabled = false;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly tagSeparatorKeysCodes: number[] = [ENTER, COMMA];
  tagList: string[] = [];
  matDialogRef?: MatDialogRef<ItemComponent>;
  previewMatDialogRef: MatDialogRef<PreviewComponent>;
  @ViewChild('uploading') uploadingTemplate: TemplatePortalDirective;

  constructor(
    private fileUploadService: FileUploadService,
    private adminMediaService: AdminMediaService,
    private alertService: JhiAlertService,
    private overlayService: OverlayService,
    private matDialog: MatDialog
  ) {}

  ngOnInit(): void {
    console.log('item loaded', this.item);
    this.tagList = this.convertTagsToTagList(this.item.tags);
    if (!this.item.id && this.item.slides) {
      this.item.slides.push({
        slideName: '',
        slideItems: this.getInitialSlideItems(),
      });
      this.adminMediaService.getCategories().subscribe((res: AdminCategory[]) => {
        if (res != null) {
          this.categories = res;
        }
      });
    }
  }

  drop(event: CdkDragDrop<string[]>): void {
    if (this.item.slides) {
      moveItemInArray(this.item.slides, event.previousIndex, event.currentIndex);
    }
  }
  deleteSlide(ind: number): void {
    if (this.item.slides) {
      if (this.item.slides.length === 1) {
        this.alertService.addAlert({ type: 'warning', msg: 'atleastOneSlideRequired', timeout: 5000, toast: true }, []);
      } else {
        this.item.slides.splice(ind, 1);
      }
    }
  }
  addTag(event: any): void {
    const input = event.input;
    const value = event.value;
    if ((value || '').trim() && !this.tagList.includes(value.trim())) {
      this.tagList.push(value.trim());
    }
    if (input) {
      input.value = '';
    }
  }
  removeTag(tag: string): void {
    const index = this.tagList.indexOf(tag);
    if (index >= 0) {
      this.tagList.splice(index, 1);
    }
  }

  addSlide(): void {
    if (this.item.slides) {
      this.item.slides.push({
        slideName: '',
        slideItems: this.getInitialSlideItems(),
      });
    }
  }
  uploadVideoFile(e: any, type: string): void {
    if (e && e.target) {
       this.overlayService.openTemplateOverlay(this.uploadingTemplate);
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe(data => {
        this.alertService.addAlert({ type: 'success', msg: 'file.upload.successful', timeout: 5000, toast: true }, []);
        if (type === 'preview') {
          this.item.previewVideoS3Url = data.url;
          this.item.previewVideoS3Key = data.s3Key;
        } else if (type === 'thumbnail') {
          this.item.thumbNailS3Url = data.url;
          this.item.thumbNailS3Key = data.s3Key;
        }
        this.overlayService.closeOverlay();
      });
    }
  }
  openFullscreen(): void {
    if (this.divRef) {
      const elem = this.divRef.nativeElement;
      if (elem.requestFullscreen) {
        elem.requestFullscreen();
      } else if (elem.msRequestFullscreen) {
        elem.msRequestFullscreen();
      } else if (elem.mozRequestFullScreen) {
        elem.mozRequestFullScreen();
      } else if (elem.webkitRequestFullscreen) {
        elem.webkitRequestFullscreen();
      }
    }
  }
  getInitialSlideItems(): Array<any> {
    return [
      {
        type: 'image',
      },
    ];
  }
  saveData(): void {
    console.log(this.item);
    this.errors = {};
    this.validateAdminForm();
    if (Object.keys(this.errors).length === 0 && !this.disabled) {
      this.item.tags = this.convertTagListToTags(this.tagList);
      this.adminMediaService.save(this.item).subscribe(() => {
        this.alertService.addAlert({ type: 'success', msg: 'uploadform.saved.successfully', timeout: 5000, toast: true }, []);
        this.disabled = true;
        window.location.href = '/dashboard/videodesigns';
      });
    }
  }
  validateAdminForm(): void {
    if (!this.item.name) {
      this.errors.firstnameRequired = true;
    }
    if (this.item.indianPrice === undefined || null) {
      this.errors.indianPrice = true;
    }
    if (this.item.indianDiscPrice === undefined || null) {
      this.errors.indianDiscPrice = true;
    }
    if (this.item.usdPrice === undefined || null) {
      this.errors.usdPrice = true;
    }
    if (this.item.usdDiscPrice === undefined || null) {
      this.errors.usdDiscPrice = true;
    }
    if (this.item.indianAdvCustomizationPrice === undefined || null) {
      this.errors.indianAdvCustomizationPrice = true;
    }
    if (this.item.usdAdvCustomizationPrice === undefined || null) {
      this.errors.usdAdvCustomizationPrice = true;
    }
    if (this.item.indianPremumDeliveryPrice === undefined || null) {
      this.errors.indianPremumDeliveryPrice = true;
    }
    if (this.item.usdPremumDeliveryPrice === undefined || null) {
      this.errors.usdPremumDeliveryPrice = true;
    }
    if (this.item.indianPrice && this.item.indianDiscPrice && this.item.indianPrice < this.item.indianDiscPrice) {
      this.errors.indianDiscPriceLargerThanPrice = true;
    }
    if (this.item.usdPrice && this.item.usdDiscPrice && this.item.usdPrice < this.item.usdDiscPrice) {
      this.errors.usdDiscPriceLargerThanPrice = true;
    }

    if (!this.item.categoryId) {
      this.errors.categoryIdRequired = true;
    }
    if (!this.item.previewVideoS3Url) {
      this.errors.previewVideoS3UrlRequired = true;
    }
    if (!this.item.thumbNailS3Url) {
      this.errors.thumbNailS3UrlRequired = true;
    }
    if (!this.item.slides || this.item.slides.length === 0) {
      this.errors.slidesRequired = true;
    }
    this.item.slides.forEach((slide, index) => {
      this.validateSlide(slide, index);
    });
  }
  validateSlide(s: any, index: number): void {
    if (!s.slideName) {
      this.errors['slide' + index + 'slideNameRequired'] = true;
    }
    if (!s.screenShotS3Url) {
      this.errors['slide' + index + 'screenShotS3UrlRequired'] = true;
    }
  }
  convertTagListToTags(list: Array<string>): string {
    let tags = '';
    if (list) {
      tags = list.map(t => '@@' + t + '@@').join(',');
    }
    return tags;
  }
  convertTagsToTagList(tags: string): Array<string> {
    if (tags) {
      return tags.split(',').map(function (t: string): string {
        return t.replace(/@@/g, '');
      });
    }
    return [];
  }
  previewItem(): void {
    this.errors = {};
    this.validateAdminForm();
    if (Object.keys(this.errors).length === 0 && !this.disabled) {
      this.item.tags = this.convertTagListToTags(this.tagList);
      this.item.divId = 'preview';
      this.matDialogRef = this.matDialog.open(ItemComponent, {
        data: this.item,
        width: '80%',
        height: '100%',
      });
    }
  }

  preview(): void {
    this.errors = {};
    this.validateAdminForm();
    if (Object.keys(this.errors).length === 0 && !this.disabled) {
      this.item.tags = this.convertTagListToTags(this.tagList);
      this.item.divId = 'preview';
      this.previewMatDialogRef = this.matDialog.open(PreviewComponent, {
        data: this.item,
        width: '80%',
        height: '100%',
      });
    }
  }
}
