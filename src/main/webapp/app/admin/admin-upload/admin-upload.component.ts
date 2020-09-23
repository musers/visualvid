import { Component, OnInit } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
@Component({
  selector: 'jhi-admin-upload',
  templateUrl: './admin-upload.component.html',
  styleUrls: ['./admin-upload.component.scss'],
})
export class AdminUploadComponent implements OnInit {
  slides: Number[] = [0];
  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {}

  addSlide(): void {
    this.slides.push(this.slides.length);
  }
  uploadVideoFile(e: any): void {
    if (e && e.target) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe();
    }
  }
}
