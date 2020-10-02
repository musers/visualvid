import { Component, OnInit, Input } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { Slide } from './slide.model';

@Component({
  selector: 'jhi-admin-upload-form-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.scss'],
})
export class SlideComponent implements OnInit {
  @Input() item: Slide = {
    slideItems: [],
  };
  @Input() index?: number;
  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {}
  addSlideItem(e: any): void {
    if (e === 'image') {
      this.item.slideItems.push({
        type: 'image',
      });
    } else if (e === 'label') {
      this.item.slideItems.push({
        type: 'label',
      });
    }
  }
  uploadVideoFile(e: any): void {
    if (e && e.target) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe(data => {
        this.item.screenShotS3Url = data.url;
        this.item.screenShotS3Key = data.s3Key;
      });
    }
  }
}
