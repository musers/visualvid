import { Component, OnInit, Input } from '@angular/core';

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
  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {}

  //  uploadVideoFile(e: any): void {
  //    if (e && e.target) {
  //      const fileToBeUploaded = e.target.files[0];
  //      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe();
  //    }
  //  }
}
