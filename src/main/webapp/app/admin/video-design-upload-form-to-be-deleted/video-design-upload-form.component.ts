import { Component, OnInit } from '@angular/core';
import { FileUploadService } from './video-design-upload-form.service';
@Component({
  selector: 'jhi-video-design-upload-form',
  templateUrl: './video-design-upload-form.component.html',
  styleUrls: ['./video-design-upload-form.component.scss'],
})
export class VideoDesignUploadFormComponent implements OnInit {
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
