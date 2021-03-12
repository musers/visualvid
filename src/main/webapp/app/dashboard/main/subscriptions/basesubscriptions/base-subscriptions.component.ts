import { Component, OnInit, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import { OverviewService } from 'app/dashboard/overview/overview.service';
import { SubscriptionService } from '../subscriptions.service';
import { ColumnSettingsModel, ITableChangeCallback, TableDataModel } from 'app/shared/table/table-settings.model';
import { TableComponent } from 'app/shared/table/table.component';

export interface Action {
  id: string;
  name: string;
}

@Component({
  selector: 'jhi-base-subscriptions-table',
  templateUrl: './base-subscriptions.component.html',
  styleUrls: ['base-subscriptions.scss'],
})
export class BaseSubscriptionsComponent implements OnInit, ITableChangeCallback, AfterViewInit {
  columnDefinition: ColumnSettingsModel[] = [];

  @ViewChild('table', { static: false })
  table: TableComponent;

  @ViewChild('overviewTemplate', { static: false })
  overviewTemplate?: TemplateRef<any>;

  actions: Action[] = [
    { name: 'Active', id: 'active' },
    { name: 'Inactive', id: 'inactive' },
    { name: 'Cancel', id: 'cancelled' },
  ];
  currentAction = 'action1';

  constructor(private overviewService: OverviewService, private subscriptionService: SubscriptionService) {}

  ngOnInit(): void {
    this.columnDefinition = [
      {
        name: 'id',
        displayName: 'Id#',
      },
      {
        name: 'name',
        displayName: 'Plan Name',
      },
      {
        name: 'price',
        displayName: 'Price',
      },
      {
        name: 'downloads',
        displayName: 'Downloads',
      },
      {
        name: 'revisions',
        displayName: 'Revisions',
      },
      {
        name: 'status',
        displayName: 'Status',
      },
      /*   {
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

  search(searchText: string): void {
    this.table.search(searchText);
  }

  getData(tableDataModel: TableDataModel): void {
    console.log('getData for subsciprtions12345');
    this.subscriptionService.getAllSubscriptionPlans(tableDataModel).subscribe(resp => {
      // this.subscriptions = resp;
      const data = {
        rowData: resp,
        total: 12,
      };
      this.table.setData(data);
    });
  }

  onActionSelect(action: string, element: any): void {
    console.log('action', action);
    console.log('element:', element);
  }
}
