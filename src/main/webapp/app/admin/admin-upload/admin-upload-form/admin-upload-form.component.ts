import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { trigger, state, style, animate, transition } from '@angular/animations';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { AdminMediaService } from '../admin-media.service';
import { AdminMedia } from './adminmedia.model';
import { AdminCategory } from 'app/admin/admin-upload/admin-upload-form/admincategory.model';

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
  item: AdminMedia = {
    slides: [],
  };
  @ViewChild('description') divRef?: ElementRef;
  categories: AdminCategory[] = [];

  constructor(private fileUploadService: FileUploadService, private adminMediaService: AdminMediaService) {}

  ngOnInit(): void {
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

  drop(event: CdkDragDrop<string[]>): void {
    moveItemInArray(this.item.slides, event.previousIndex, event.currentIndex);
  }
  deleteSlide(ind: number): void {
    this.item.slides.splice(ind, 1);
  }
  addSlide(): void {
    this.item.slides.push({
      slideName: '',
      slideItems: this.getInitialSlideItems(),
    });
  }
  uploadVideoFile(e: any, type: string): void {
    if (e && e.target) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe(data => {
        if (type === 'preview') {
          this.item.previewVideoS3Url = data.url;
          this.item.previewVideoS3Key = data.s3Key;
        } else if (type === 'thumbnail') {
          this.item.thumbNailS3Url = data.url;
          this.item.thumbNailS3Key = data.s3Key;
        }
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
      {
        type: 'label',
      },
    ];
  }
  saveData(): void {
    console.log(this.item);
    // TODO need to remove following
    this.item.categoryId = 'cate1';
    this.item.indianPrice = '0';
    this.item.usdPrice = 0;
    this.adminMediaService.save(this.item).subscribe();
  }
}
