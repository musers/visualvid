import { Component, OnInit, AfterViewInit, Input, ViewEncapsulation } from '@angular/core';
import videojs from 'video.js';

import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
@Component({
  selector: 'jhi-videoitem',
  templateUrl: './videoitem.component.html',
  styleUrls: ['videoitem.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class VideoItemComponent implements OnInit, AfterViewInit {
  public player?: videojs.Player;

  @Input() item?: AdminMedia;
  constructor() {}

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
      this.player = videojs(document.getElementById(this.item.id), conf);
    }
  }


}
