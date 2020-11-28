<<<<<<< HEAD
import { Component, OnInit } from '@angular/core';
=======
import { Component, Input, OnInit } from '@angular/core';
import { AdminVideoService } from './admin-video.service';
import { AdminVideoModel } from './admin-video.model';
import { Pagination } from '../../../../../app/shared/util/request-util';
import { HttpResponse } from '@angular/common/http';
>>>>>>> 24c02b8cb6cc34fdde719c598482337a9d080cc7

@Component({
  selector: 'jhi-admin-video',
  templateUrl: './admin-video.component.html',
  styleUrls: ['admin-video.scss'],
})
export class AdminVideoComponent implements OnInit {
  @Input() videoDesigns?: AdminVideoModel[];
  count: Number = 0;

  constructor(protected adminVideoService: AdminVideoService) {}

  ngOnInit(): void {
    //    this.adminVideoService.findAllByPage({
    //        page: 0,
    //        size: 10,
    //        sort: [],
    //       })
    //        .subscribe((res: HttpResponse<AdminVideoModel[]>) => {
    //                 if(res != null && res.body != null) {
    //                     this.videoDesigns = res.body;
    //                 }
    //        });

    this.adminVideoService.findAll().subscribe((res: AdminVideoModel[]) => {
      if (res != null) {
        this.videoDesigns = res;
        this.videoDesigns.forEach(vd => {
          vd.id = '#000' + this.count + 1;
          vd.created = 'Sep23, 2020';
          vd.modified = 'Sep23, 2020';
          vd.views = 500;
          vd.sales = 250;
          vd.earnings = 75000;
        });
      }
    });
  }
}
