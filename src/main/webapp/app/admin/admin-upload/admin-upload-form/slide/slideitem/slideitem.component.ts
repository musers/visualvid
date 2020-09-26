import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { SlideItem } from './slideitem.model';
@Component({
  selector: 'jhi-admin-upload-form-slide-item',
  templateUrl: './slideitem.component.html',
  styleUrls: ['./slideitem.component.scss'],
})
export class SlideItemComponent implements OnInit {
  @Input() item?: SlideItem;
  @Input() index?: number;
  @Input() isLast = true;
  @Output() addSlideItemHandler = new EventEmitter();

  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {}
  addSlide(type: String): void {
    this.addSlideItemHandler.emit(type);
  }
  uploadVideoFile(e: any, sItem: SlideItem): void {
    if (e && e.target) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe(data => {
        if (data && this.item) {
          sItem.fileName = data.fileName;
          sItem.key = data.key;
          sItem.url = data.url;
        }
      });
    }
  }
}
