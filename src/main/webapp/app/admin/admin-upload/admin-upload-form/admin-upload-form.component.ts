import { Component, OnInit } from '@angular/core';
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
  ],
})
export class AdminUploadFormComponent implements OnInit {
  item: AdminMedia = {
    slides: [],
  };
  categories: AdminCategory[] = [];

  constructor(private fileUploadService: FileUploadService, private adminMediaService: AdminMediaService) {}

  ngOnInit(): void {
    this.item.slides.push({
      slideName: '',
      slideItems: [
        {
          type: 'image',
        },
        {
          type: 'label',
        },
      ],
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
      slideItems: [
        {
          type: 'image',
        },
        {
          type: 'label',
        },
      ],
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
  saveData(): void {
    console.log(this.item);
    // TODO need to remove following
    this.item.categoryId = 'cate1';
    this.item.indianPrice = '0';
    this.item.usdPrice = 0;
    this.adminMediaService.save(this.item).subscribe();
  }
}
