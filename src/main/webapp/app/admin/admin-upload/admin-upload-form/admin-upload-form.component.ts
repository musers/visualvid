import { Component, OnInit } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
@Component({
  selector: 'jhi-admin-upload-form',
  templateUrl: './admin-upload-form.component.html',
  styleUrls: ['./admin-upload-form.component.scss'],
})
export class AdminUploadFormComponent implements OnInit {
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
