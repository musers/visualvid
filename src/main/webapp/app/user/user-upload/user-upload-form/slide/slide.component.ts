import { Component, OnInit, Input } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { JhiAlertService } from 'ng-jhipster';
import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';
import { UserSlideItem } from './slideitem/userSlideItem.model';
@Component({
  selector: 'jhi-user-upload-form-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.scss'],
})
export class SlideComponent implements OnInit {
  @Input() adminMediaSlide?: Slide ;
  @Input() userSlideItems?: Array<UserSlideItem>  = [];

  constructor(
    private fileUploadService: FileUploadService,
    private alertService: JhiAlertService
    ) {
      if(!this.userSlideItems){
        this.userSlideItems = [];
      }
    }
  ngOnInit(): void {}

  removeFile(ind: any): void {
    if(this.userSlideItems){
      this.userSlideItems[ind].s3Url = undefined;
      this.userSlideItems[ind].s3Url = undefined;
    }
  }
  uploadFile(e: any, ind:any): void {
    if (e && e.target) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe(data => {
        this.alertService.addAlert({ type: 'success', msg: 'file.upload.successful', timeout: 5000, toast: true }, []);
          if(this.userSlideItems){
            this.userSlideItems[ind].s3Url = data.url;
            this.userSlideItems[ind].s3Key = data.s3Key;
          }
      });
    }
  }

}
