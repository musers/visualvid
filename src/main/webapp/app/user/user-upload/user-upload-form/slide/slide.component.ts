import { Component, OnInit, Input } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';

@Component({
  selector: 'jhi-user-upload-form-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.scss'],
})
export class SlideComponent implements OnInit {
  @Input() adminMediaSlide?: Slide ;
  constructor(private fileUploadService: FileUploadService) {}
  ngOnInit(): void {}
}
