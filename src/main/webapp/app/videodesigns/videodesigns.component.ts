import { Component, OnInit } from '@angular/core';

import { VideoDesignsService } from './videodesigns.service';
@Component({
  selector: 'jhi-videodesigns',
  templateUrl: './videodesigns.component.html',
  styleUrls: ['videodesigns.scss'],
})
export class VideoDesignsComponent implements OnInit {
  categories: any = [];
  active = 1;
  constructor(private videoDesignsService: VideoDesignsService) {}

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.videoDesignsService.getCategories().subscribe(data => {
      if (data) {
        this.categories = data;
        this.categories.forEach(function (c: any) {
          c.name = 'asdf';
        });
      }
    });
  }
}
