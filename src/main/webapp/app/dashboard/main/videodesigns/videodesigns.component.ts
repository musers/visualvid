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

  columnDefinition: ColumnSettingsModel[] = [];
  count: Number = 0;
  totalVideosCount = 0;
  salesCount = 0;
  viewsCount = 0;
  earningsCount = 0;

  constructor(
    protected adminVideoService: AdminVideoService,
    protected overviewService: OverviewService) {
    this.columnDefinition = [
      {
        name: 'id',
        displayName: 'Video ID #',
        disableSorting: false,
      },
      {
        name: 'name',
        displayName: 'Name',
        disableSorting: false,
      },
      {
        name: 'createdDate',
        displayName: 'Created',
        disableSorting: false,
      },
      {
        name: 'modifiedDate',
        displayName: 'Modified',
        disableSorting: false,
      },
      {
        name: 'views',
        displayName: 'Views',
        disableSorting: false,
      },
      {
        name: 'sales',
        displayName: 'Sales',
        disableSorting: false,
      },
      {
        name: 'earnings',
        displayName: 'Earnings',
        disableSorting: false,
      },
    ];
  }

  ngOnInit(): void {
    this.adminVideoService.getStats().subscribe(data => {
      this.totalVideosCount = data.totalVideosCount;
      this.salesCount = data.salesCount;
      this.viewsCount = data.viewsCount;
      this.earningsCount = data.earningsCount;
    })
  }
  editVideoDesign(vd: AdminVideoModel): void {
    window.location.href = '/admin/upload/' + vd.id;
  }
  ngAfterViewInit(): void {
    console.log('ngAfterViewInit');
    this.table.requestData();
  }

  onRowSelect(selectedRows: object[]): void {
    this.overviewService.updateOverviewTemplate(this.overviewTemplate,selectedRows);
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
