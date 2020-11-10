import { Component, OnInit, Input } from '@angular/core';

import { VideoItem } from '../videoitem.model';
import { VideoDesignsService } from '../videodesigns.service';
import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';

@Component({
  selector: 'jhi-videolisting',
  templateUrl: './videolisting.component.html',
  styleUrls: ['videolisting.scss'],
})
export class VideoListingComponent implements OnInit {
  @Input() category: any;
  list: VideoItem[] = [];
  constructor(private videoDesignsService: VideoDesignsService,
    private adminMediaService: AdminMediaService) {}

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.adminMediaService.getAll(this.category.categoryId).subscribe(data => {
      if (data) {
        this.list = data;
      }
    });
  }
  onVideoItemClick(item: any): void {
    console.log('videoItem',item);
    // TODO need to remove following
    window.open('/customer/upload/'+item.id);
  }

}
