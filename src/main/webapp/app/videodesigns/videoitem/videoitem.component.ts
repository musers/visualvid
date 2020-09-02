import { Component, OnInit, AfterViewInit, Input } from '@angular/core';
import videojs from 'video.js';

import { VideoItem } from '../videoitem.model';
@Component({
  selector: 'jhi-videoitem',
  templateUrl: './videoitem.component.html',
  styleUrls: ['videoitem.scss'],
})
export class VideoItemComponent implements OnInit, AfterViewInit {
  public vjs?: videojs.Player;

  @Input() item?: VideoItem;
  constructor() {}

  ngOnInit(): void {}
  ngAfterViewInit(): void {
    if (this.item) {
      const options = {
        sources: [
          {
            src: 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4',
            type: 'application/x-mpegURL',
          },
        ],
        poster: 'https://test-videos.co.uk/user/pages/images/big_buck_bunny.jpg',
        playToggle: false,
        captionsButton: false,
        chaptersButton: false,
        subtitlesButton: false,
        remainingTimeDisplay: false,
        progressControl: {
          seekBar: true,
        },
        fullscreenToggle: false,
        playbackRateMenuButton: false,
      };
      console.log(options);
      //      this.vjs = videojs(this.item.id,options);
    }
    //    this.vjs = videojs('my-player',options);
  }
}
