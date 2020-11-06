import { Component, OnInit } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';

@Component({
  selector: 'jhi-user-upload-form-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.scss'],
})
export class SlideComponent implements OnInit {
  constructor(private fileUploadService: FileUploadService) {}

  ngOnInit(): void {}
}
