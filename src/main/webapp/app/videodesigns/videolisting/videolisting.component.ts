import { Component, OnInit } from '@angular/core';

import { VideoItem } from '../videoitem.model';
import { VideoDesignsService } from '../videodesigns.service';
@Component({
  selector: 'jhi-videolisting',
  templateUrl: './videolisting.component.html',
  styleUrls: ['videolisting.scss'],
})
export class VideoListingComponent implements OnInit {
  list: VideoItem[] = [];
  constructor(private videoDesignsService: VideoDesignsService) {}

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.videoDesignsService.getVideos('categoryId').subscribe(data => {
      if (data) {
        this.list = data;
      }
    });
  }
}
