import { Component, OnInit } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { SlideItem } from './slideitem/slideitem.model';
@Component({
  selector: 'jhi-admin-upload-form-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.scss'],
})
export class SlideComponent implements OnInit {
  slideItems: Array<SlideItem> = [];
  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {
    this.slideItems.push(
      {
        type: 'image',
      },
      {
        type: 'image',
      }
    );
  }

  //  uploadVideoFile(e: any): void {
  //    if (e && e.target) {
  //      const fileToBeUploaded = e.target.files[0];
  //      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe();
  //    }
  //  }
}
