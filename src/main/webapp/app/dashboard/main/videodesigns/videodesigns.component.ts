import { Component, Input, OnInit } from '@angular/core';
import { AdminVideoService } from './admin-video.service';
import { AdminVideoModel } from './admin-video.model';
import { ColumnSettingsModel, TablePaginationSettingsModel } from 'app/shared/table/table-settings.model';
// import { Pagination } from '../../../../../app/shared/util/request-util';
// import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-dashboard-videodesigns',
  templateUrl: './videodesigns.component.html',
  styleUrls: ['videodesigns.component.scss'],
})
export class DashboardVideoDesignsComponent implements OnInit {
  @Input() videoDesigns?: AdminVideoModel[];
  count: Number = 0;
  columnDefinition: ColumnSettingsModel[] = [];
  tablePaginationSettings: TablePaginationSettingsModel = {
    enablePagination: true,
    pageSize: 10,
    pageSizeOptions: [10, 20, 30],
    showFirstLastButtons: true,
  };
  rowData: Array<AdminVideoModel> = [];
  constructor(protected adminVideoService: AdminVideoService) {
    this.columnDefinition = [
      {
        name: 'name',
        displayName: 'Name',
        disableSorting: false,
      },
      {
        name: 'id',
        displayName: 'Video ID #',
        disableSorting: false,
      },
      {
        name: 'views',
        displayName: 'Views',
        disableSorting: false,
      },
    ];
  }

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
        // this.videoDesigns = res;
        this.rowData = res;
        this.rowData.forEach(vd => {
          vd.created = 'Sep23, 2020';
          vd.modified = 'Sep23, 2020';
          vd.views = 500;
          vd.sales = 250;
          vd.earnings = 75000;
        });
      }
    });
  }
  editVideoDesign(vd: AdminVideoModel): void {
    window.location.href = '/admin/upload/' + vd.id;
  }
  onNotifySelected(selectedRows: object[]): void {
    console.log(selectedRows);
  }
  search(evt: any): void {
    console.log(evt);
    // TODO call a back-end service awith evt.query and map result to this.rowData;
  }
}
