import { Component, OnInit } from '@angular/core';

import { AdminCategory } from 'app/admin/admin-upload/admin-upload-form/admincategory.model';
import { VideoDesignsService } from './videodesigns.service';
import { AdminMediaService } from 'app/admin/admin-upload/admin-media.service';

@Component({
  selector: 'jhi-videodesigns',
  templateUrl: './videodesigns.component.html',
  styleUrls: ['videodesigns.scss'],
})
export class VideoDesignsComponent implements OnInit {
  categories: AdminCategory[] = [];
  activeId = 'ngb-nav-0';
  constructor(private videoDesignsService: VideoDesignsService,
    private adminMediaService: AdminMediaService) {}

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.adminMediaService.getCategories().subscribe(data => {
      if (data) {
        this.categories = data;
        this.categories.forEach(c => {
          c.nameFormatted = c.categoryName.split(' ').join('<br>');
        });
      }
    });
  }
}
