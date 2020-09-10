import { Component, OnInit } from '@angular/core';

import { Category } from './category.model';
import { VideoDesignsService } from './videodesigns.service';
@Component({
  selector: 'jhi-videodesigns',
  templateUrl: './videodesigns.component.html',
  styleUrls: ['videodesigns.scss'],
})
export class VideoDesignsComponent implements OnInit {
  categories: Category[] = [];
  active = 1;
  constructor(private videoDesignsService: VideoDesignsService) {}

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.videoDesignsService.getCategories().subscribe(data => {
      if (data) {
        this.categories = data;
        this.categories.forEach(c => {
          c.nameFormatted = c.name.split(' ').join('<br>');
        });
      }
    });
  }
}
