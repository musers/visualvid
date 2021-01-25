import { Component, OnInit } from '@angular/core';

import { Category } from 'app/category/category.model';
import { VideoDesignsService } from './videodesigns.service';
import { CategoryService } from 'app/category/category.service';

@Component({
  selector: 'jhi-videodesigns',
  templateUrl: './videodesigns.component.html',
  styleUrls: ['videodesigns.scss'],
})
export class VideoDesignsComponent implements OnInit {
  categories: Category[] = [];
  activeId = 'ngb-nav-0';
  constructor(private videoDesignsService: VideoDesignsService,
    private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.categoryService.getCategories().subscribe(data => {
      if (data) {
        this.categories = data;
        this.categories.forEach(c => {
          c.nameFormatted = c.categoryName.split(' ').join('<br>');
        });
      }
    });
  }
}
