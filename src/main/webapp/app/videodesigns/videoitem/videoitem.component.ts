import { Component, OnInit, AfterViewInit, Input, ViewEncapsulation, Inject, Optional} from '@angular/core';
import videojs from 'video.js';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";

import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
@Component({
  selector: 'jhi-videoitem',
  templateUrl: './videoitem.component.html',
  styleUrls: ['videoitem.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class VideoItemComponent implements OnInit, AfterViewInit {
  public player?: videojs.Player;
  autoplay = false;
  @Input() item?: AdminMedia;
  constructor(@Optional() @Inject(MAT_DIALOG_DATA) data?: any) {
    if(data){
      this.item = data.item;
      this.autoplay = data.config?.autoplay;
    }

  }

  ngOnInit(): void {}
  ngAfterViewInit(): void {
    if (this.item && this.item.id) {
     const conf = {}
     conf['userActions'] = {
        doubleClick: () => {
          if(this.player){
            this.player.pause()
          }
       }
     }
     conf['autoplay'] = this.autoplay;
      this.player = videojs(document.getElementById(this.item.id), conf);
    }
  }


}
