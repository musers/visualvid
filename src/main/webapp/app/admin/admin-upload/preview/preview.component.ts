import { Component, OnInit, Inject, Optional } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
@Component({
  selector: 'jhi-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss'],
})
export class PreviewComponent implements OnInit {
  item?: AdminMedia;
  constructor(@Optional() @Inject(MAT_DIALOG_DATA) data?: AdminMedia) {
    console.log('injected data in preview', data);
    this.item = data;
  }

  ngOnInit(): void {}
}
