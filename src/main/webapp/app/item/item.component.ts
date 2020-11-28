import { Component, Input, OnInit, ViewEncapsulation, AfterViewInit, Inject, Optional } from '@angular/core';
import videojs from 'video.js';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";

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
  constructor(private itemService: ItemService,
    private route: ActivatedRoute,
    @Optional() @Inject(MAT_DIALOG_DATA) data?: AdminMedia
  ) {
    console.log('item',data)
    this.item = data;
    this.formatTags();
  }

  ngOnInit(): void {
    const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
    if(adminMediaId){
      this.itemService.get(adminMediaId).subscribe((res: AdminMedia) => {
        if (res) {
          this.item = res;
          this.item.divId = this.item.id;
          this.formatTags();
        }
      });
    }
  }
  ngAfterViewInit(): void {
    if (this.item && this.item.divId) {
//       this.player = videojs(document.getElementById('item-' + this.item.divId), {});
    }
  }
  formatTags() : void {
    if(this.item && this.item.tags){
      this.item.tagList = this.item.tags.split(',').map(
        function(t: string): string{
          return t.replace(/@@/g,'');
        }
      )
    }
  }
}
