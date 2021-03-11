import { Component, OnInit, AfterViewInit, ViewChild, TemplateRef, Input } from '@angular/core';
import { OverviewService } from 'app/dashboard/overview/overview.service';
import { SubscriptionService } from '../subscriptions.service';
import { ColumnSettingsModel, ITableChangeCallback, TableDataModel } from 'app/shared/table/table-settings.model';
import { TableComponent } from 'app/shared/table/table.component';

@Component({
  selector: 'jhi-user-subscriptions-table',
  templateUrl: './user-subscriptions.component.html',
  styleUrls: ['user-subscriptions.scss'],
})
export class UserSubscriptionsComponent implements OnInit, ITableChangeCallback, AfterViewInit {
  columnDefinition: ColumnSettingsModel[] = [];

  @Input() status = '';
  @ViewChild('table', { static: false })
  table: TableComponent;

  @ViewChild('overviewTemplate', { static: false })
  overviewTemplate?: TemplateRef<any>;

  constructor(private overviewService: OverviewService, private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.columnDefinition = [
      {
        name: 'userId',
        displayName: 'Subscriber Id#',
      },
      {
        name: 'planName',
        displayName: 'Plan',
      },
      {
        name: 'userName',
        displayName: 'Name',
      },
      {
        name: 'startDate',
        displayName: 'Start Date',
      },
      {
        name: 'endDate',
        displayName: 'End Date',
      },
      {
        name: 'ordersLeft',
        displayName: 'Downloads Left(month)',
      },
      {
        name: 'status',
        displayName: 'Status',
      },
      /* {
            name: 'action',
            displayName: 'Action',
            cellTemplate: this.subscriptionsTemplate,
          }, */
    ];
  }

  ngAfterViewInit(): void {
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

  getData(tableDataModel: TableDataModel): void {
    console.log('getData for usersubsciprtions123');
    this.subscriptionService.getAllUserSubscriptions(tableDataModel, this.status).subscribe(resp => {
      // this.userSubscriptions = resp;
      const data = {
        rowData: resp,
        total: 12,
      };
      this.table.setData(data);
    });
  }
}
