import { Component, OnInit, AfterViewInit, Input, ViewEncapsulation } from '@angular/core';
import videojs from 'video.js';

import { VideoItem } from '../videoitem.model';
@Component({
  selector: 'jhi-videoitem',
  templateUrl: './videoitem.component.html',
  styleUrls: ['videoitem.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class VideoItemComponent implements OnInit, AfterViewInit {
  public player?: videojs.Player;

  @Input() item?: VideoItem;
  constructor() {}

  ngOnInit(): void {}
  ngAfterViewInit(): void {
    if (this.item && this.item.id) {
      this.player = videojs(document.getElementById(this.item.id), {});
    }
  }
}
