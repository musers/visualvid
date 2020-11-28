import { Component, OnInit, Input } from '@angular/core';
import { JhiAlertService } from 'ng-jhipster';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { Slide } from './slide.model';

@Component({
  selector: 'jhi-admin-upload-form-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.scss'],
})
export class SlideComponent implements OnInit {
  @Input() item: Slide = {
    slideItems: [],
  };
  @Input()
  errors: any = {};
  @Input() index?: number;
  constructor(private fileUploadService: FileUploadService,
    private alertService: JhiAlertService) {}

  ngOnInit(): void {}
  addSlideItem(e: any): void {
    this.item.slideItems.push({
          type: e,
        });
  }
  removeSlideItem(ind: any): void {
    if(this.item.slideItems){
      if(this.item.slideItems.length === 1){
        this.alertService.addAlert({ type: 'warning', msg: 'atleastOneSlideItemRequired', timeout: 5000, toast: true }, []);
      }else {
        this.item.slideItems.splice(ind, 1);
      }
    }
  }
  removeScreenShot(): void {
    this.item.screenShotS3Url = undefined;
    this.item.screenShotS3Key = undefined;
  }
  uploadVideoFile(e: any): void {
    if (e && e.target) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe(data => {
        this.item.screenShotS3Url = data.url;
        this.item.screenShotS3Key = data.s3Key;
      });
    }
  }
}
