import { Component, OnInit, Input } from '@angular/core';

import { FileUploadService } from 'app/fileupload/fileupload.service';
import { JhiAlertService } from 'ng-jhipster';
import { OrderSlideItem } from 'app/order/order.model';
import { SlideItem } from 'app/admin/admin-upload/admin-upload-form/slide/slideitem/slideitem.model';

@Component({
  selector: 'jhi-user-upload-form-slide-item',
  templateUrl: './user-slide-item.component.html',
  styleUrls: ['./user-slide-item.component.scss'],
})
export class UserSlideItemComponent implements OnInit {
  @Input() orderSlideItems?: Array<OrderSlideItem>;
  @Input() adminItem: SlideItem = {};
  @Input() index = 0;
  item: OrderSlideItem = {};
  constructor(private fileUploadService: FileUploadService, private alertService: JhiAlertService) {}
  ngOnInit(): void {
    if (this.orderSlideItems && this.orderSlideItems[this.index]) {
      this.item = this.orderSlideItems[this.index];
    } else {
      this.item = {};
    }
  }

  removeFile(): void {
    if (this.item) {
      this.item.s3Url = undefined;
      this.item.s3Url = undefined;
    }
  }
  uploadFile(e: any): void {
    if (e && e.target && this.adminItem.id) {
      const fileToBeUploaded = e.target.files[0];
      this.fileUploadService.uploadFile(fileToBeUploaded).subscribe(data => {
        this.alertService.addAlert({ type: 'success', msg: 'file.upload.successful', timeout: 5000, toast: true }, []);
        if (this.item) {
          this.item.s3Url = data.url;
          this.item.s3Key = data.s3Key;
        }
      });
    }
  }
}
