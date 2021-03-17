import { Component,Input, OnInit, AfterViewInit, ViewChild, TemplateRef, SimpleChanges, OnChanges } from '@angular/core';
import { AdminVideoService } from './admin-video.service';
import { AdminVideoModel } from './admin-video.model';
import { OverviewService } from 'app/dashboard/overview/overview.service';
import { ColumnSettingsModel, ITableChangeCallback, TableDataModel } from 'app/shared/table/table-settings.model';
import { TableComponent } from 'app/shared/table/table.component';

@Component({
  selector: 'jhi-dashboard-videodesigns',
  templateUrl: './videodesigns.component.html',
  styleUrls: ['videodesigns.component.scss'],
})
export class DashboardVideoDesignsComponent implements OnInit, ITableChangeCallback, AfterViewInit, OnChanges  {
  @Input() videoDesigns?: AdminVideoModel[];

  @ViewChild('table', { static: false })
  table: TableComponent;
  @ViewChild('overviewTemplate', { static: false })
  overviewTemplate?: TemplateRef<any>;

  count: Number = 0;
  columnDefinition: ColumnSettingsModel[] = [];
  constructor(
    protected adminVideoService: AdminVideoService,
    protected overviewService: OverviewService) {
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
//     this.adminVideoService.findAll().subscribe((res: AdminVideoModel[]) => {
//       if (res != null) {
//         // this.videoDesigns = res;
//         this.rowData = res;
//         this.rowData.forEach(vd => {
//           vd.created = 'Sep23, 2020';
//           vd.modified = 'Sep23, 2020';
//           vd.views = 500;
//           vd.sales = 250;
//           vd.earnings = 75000;
//         });
//       }
//     });
  }
  editVideoDesign(vd: AdminVideoModel): void {
    window.location.href = '/admin/upload/' + vd.id;
  }
  ngAfterViewInit(): void {
    console.log('ngAfterViewInit');
    this.table.requestData();
  }

  onRowSelect(selectedRows: object[]): void {
    if (selectedRows && selectedRows.length > 0) {
      this.overviewService.updateOverviewTemplate({
        template: this.overviewTemplate,
        data: selectedRows[0],
      });
    }
  }

  search(evt: any): void {
    if(this.table){
      this.table.search(evt.query);
    }
  }
  ngOnChanges(changes: SimpleChanges) {
    console.log('ngOnchanges');
    if (changes.searchText) {
      this.search(changes.searchText.currentValue);
    }
  }

  getData(tableDataModel: TableDataModel): void {
    this.adminVideoService.getAll(tableDataModel).subscribe(resp => {
      const data = {
        rowData: resp.content,
        total: 12,
      };
      this.table.setData(data);
    });
  }
}
