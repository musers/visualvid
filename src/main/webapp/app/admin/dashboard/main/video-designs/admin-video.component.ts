import { Component, Input, OnInit } from '@angular/core';
import { AdminVideoModel } from './admin-video.model';

@Component({
  selector: 'jhi-admin-video',
  templateUrl: './admin-video.component.html',
  styleUrls: ['admin-video.scss'],
})
export class AdminVideoComponent implements OnInit {
  @Input() videoDesigns?: AdminVideoModel[];

  ngOnInit(): void {
    this.videoDesigns = [
      {
        id: '#0001',
        name: 'Restaurant Promo',
        created: 'Sep23, 2020',
        modified: 'Sep23, 2020',
        views: 500,
        sales: 250,
        earnings: 75000,
      },
      {
        id: '#0002',
        name: 'Corporate Presentation',
        created: 'Nov23, 2020',
        modified: 'Nov23, 2020',
        views: 800,
        sales: 850,
        earnings: 75000,
      },
    ];
  }
}
