import { Component, OnInit, Input, ViewEncapsulation, AfterViewInit } from '@angular/core';
import videojs from 'video.js';

import { AdminMedia } from '../../app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { ItemService } from '../../app/item/item.service';

@Component({
  selector: 'jhi-item',
  templateUrl: './item.component.html',
  styleUrls: ['item.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ItemComponent implements OnInit, AfterViewInit {
  public player?: videojs.Player;

  @Input() item?: AdminMedia = {
    slides: [],
  };
  constructor(private itemService: ItemService) {}

  ngOnInit(): void {
    this.itemService.get('88baed2f-6991-49df-9512-a2cef99c09bc').subscribe((res: AdminMedia) => {
      if (res != null) {
        this.item = res;
      }
    });
  }
  ngAfterViewInit(): void {
    if (this.item && this.item.id) {
      this.player = videojs(document.getElementById('item-' + this.item.id), {});
    }
  }
}
