import { Component, OnInit } from '@angular/core';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { Slide } from './slide/slide.model';

@Component({
  selector: 'jhi-admin-upload-form',
  templateUrl: './admin-upload-form.component.html',
  styleUrls: ['./admin-upload-form.component.scss'],
})
export class AdminUploadFormComponent implements OnInit {
  slides: Array<Slide> = [];
  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {
    this.slides.push({
      name: '',
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
  drop(event: CdkDragDrop<string[]>): void {
    moveItemInArray(this.slides, event.previousIndex, event.currentIndex);
  }
  deleteSlide(ind: number): void {
    this.slides.splice(ind, 1);
  }
  addSlide(): void {
    this.slides.push({
      name: '',
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
  uploadVideoFile(e: any): void {
    if (e && e.target) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe();
    }
  }
  saveData(): void {
    console.log(this.slides);
  }
}
