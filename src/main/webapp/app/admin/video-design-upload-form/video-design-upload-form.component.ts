import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-video-design-upload-form',
  templateUrl: './video-design-upload-form.component.html',
  styleUrls: ['./video-design-upload-form.component.scss'],
})
export class VideoDesignUploadFormComponent implements OnInit {
  slides: Number[] = [0];
  constructor() {}
  
  ngOnInit(): void {}

  addSlide(): void {
   this.slides.push(this.slides.length);
  }
}


