import { Component, OnInit, Input, ViewEncapsulation, AfterViewInit } from '@angular/core';
import videojs from 'video.js';
import { ActivatedRoute } from '@angular/router';

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
  constructor(private itemService: ItemService, private route: ActivatedRoute) {}

  ngOnInit(): void {
  const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
  if(adminMediaId){
      this.itemService.get(adminMediaId).subscribe((res: AdminMedia) => {
        if (res) {
          this.item = res;
        }
      });
    }
  }
  ngAfterViewInit(): void {
    if (this.item && this.item.id) {
      this.player = videojs(document.getElementById('item-' + this.item.id), {});
    }
  }
}
