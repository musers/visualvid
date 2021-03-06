import { Component, OnInit, Input } from '@angular/core';

import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';
import { VideoDesignsService } from '../videodesigns.service';
import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';

@Component({
  selector: 'jhi-videolisting',
  templateUrl: './videolisting.component.html',
  styleUrls: ['videolisting.scss'],
})
export class VideoListingComponent implements OnInit {
  @Input() category: any;
  list: AdminMedia[] = [];
  constructor(private videoDesignsService: VideoDesignsService, private adminMediaService: AdminMediaService) {}

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.adminMediaService.getAll(this.category.id).subscribe(data => {
      if (data) {
        this.list = data.content;
      }
    });
  }
  onVideoItemClick(item: any): void {
    console.log('videoItem', item);
  }
  onVideoItemDoubleClick(item: any): void {
    window.location.href = '/item/' + item.id;
  }
}
