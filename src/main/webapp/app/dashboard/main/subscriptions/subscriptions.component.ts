import { Component, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
// import { Observable } from 'rxjs';
import { SubscriptionModel } from './subscriptions.model';
import { UserSubscriptionModel } from './user-subscription.model';
import { SubscriptionAddModel } from './add-subscription/add-subscription.model';
import { SubscriptionService } from './subscriptions.service';
import { MatDialog } from '@angular/material/dialog';
import { DashboardAddSubscriptionComponent } from './add-subscription/add-subscription.component';
import {
  ColumnSettingsModel,
  TablePaginationSettingsModel,
  ITableChangeCallback,
  TableDataModel,
} from 'app/shared/table/table-settings.model';
import { TableComponent } from 'app/shared/table/table.component';
import { OverviewService } from 'app/dashboard/overview/overview.service';

export interface Action {
  id: string;
  name: string;
}

@Component({
  selector: 'jhi-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['subscriptions.scss'],
})
export class DashboardSubscriptionComponent implements OnInit, ITableChangeCallback {
  @Input() subscriptions?: SubscriptionModel[];
  @Input() userSubscriptions?: UserSubscriptionModel[];
  @Input() status?: String;
  @Input() showSubscriptionModels?: Boolean = true;
  @Input() cardFooterClass?: String;
  subscriptionAddModel?: SubscriptionAddModel;

  @ViewChild('subscriptionsTemplate', { static: true })
  subscriptionsTemplate?: TemplateRef<any>;

  @ViewChild('subscriptionsOverviewTemplate', { static: true })
  subscriptionsOverviewTemplate?: TemplateRef<any>;

  @ViewChild('userSubscriptionsOverviewTemplate', { static: true })
  userSubscriptionsOverviewTemplate?: TemplateRef<any>;

  @ViewChild('userSubscriptionsTemplate', { static: true })
  userSubscriptionsTemplate?: TemplateRef<any>;

  @ViewChild('subscriptionTable', { static: true })
  subscriptionTable: TableComponent;

  @ViewChild('userSubscriptionsTable', { static: true })
  userSubscriptionsTable: TableComponent;

  columnDefinition: ColumnSettingsModel[] = [];
  userSubscriptionsColumnDefinition: ColumnSettingsModel[] = [];

  tablePaginationSettings: TablePaginationSettingsModel = {
    enablePagination: true,
    pageSize: 1,
    pageSizeOptions: [1, 2, 3],
    showFirstLastButtons: true,
  };

  rowData: Array<SubscriptionModel> = [];
  actions: Action[] = [
    { name: 'Active', id: 'active' },
    { name: 'Inactive', id: 'inactive' },
    { name: 'Cancel', id: 'cancelled' },
  ];
  currentAction = 'action1';

  constructor(public dialog: MatDialog, private subscriptionService: SubscriptionService, public overviewService: OverviewService) {}

  ngOnInit(): void {
    this.getSubscriptionModels();
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
      {
        name: 'action',
        displayName: 'Action',
        cellTemplate: this.subscriptionsTemplate,
      },
    ];

    this.userSubscriptionsColumnDefinition = [
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

  getUserSubscriptions(status: String) {
    this.showSubscriptionModels = false;
    this.status = status;
    this.subscriptions = [];
    console.log('user subscriptions:: ');
    /* this.userSubscriptions = [
      {
        userId: '0001',
        planName: 'Basic',
        userName: 'Anil Kumar',
        startDate: '19 Nov,2020',
        endDate: '19 Nov,2020',
        ordersLeft: '5/10',
        status: this.status,
        action: '',
      },
      {
        userId: '0002',
        planName: 'Basic',
        userName: 'Anil Kumar',
        startDate: '19 Nov,2020',
        endDate: '19 Nov,2020',
        ordersLeft: '5/10',
        status: this.status,
        action: '',
      },
      {
        userId: '0003',
        planName: 'Basic',
        userName: 'Anil Kumar',
        startDate: '19 Nov,2020',
        endDate: '19 Nov,2020',
        ordersLeft: '5/10',
        status: this.status,
        action: '',
      },
    ];*/
  }

  getSubscriptionModels() {
    //     this.subscriptionService.getAllSubscriptionPlans().subscribe(res => {
    //       this.subscriptions = res;
    //     });
    this.showSubscriptionModels = true;
    this.status = '';
  }

  addNew(): void {
    this.dialog.open(DashboardAddSubscriptionComponent, {
      disableClose: true,
      width: '20rem',
      height: '40rem',
      data: {
        name: this.subscriptionAddModel?.name,
        indianPriceForMonth: this.subscriptionAddModel?.indianPriceForMonth,
        indianPriceForYear: this.subscriptionAddModel?.indianPriceForYear,
        usPriceForMonth: this.subscriptionAddModel?.usPriceForMonth,
        usPriceForYear: this.subscriptionAddModel?.usPriceForYear,
        downloads: this.subscriptionAddModel?.downloads,
        downloadLimitPerDay: this.subscriptionAddModel?.downloadLimitPerDay,
        unLimitedDownloadsEnable: this.subscriptionAddModel?.unLimitedDownloadsEnable,
        revisions: this.subscriptionAddModel?.revisions,
        categories: this.subscriptionAddModel?.categories,
        textLine1: this.subscriptionAddModel?.textLine1,
        textLine2: this.subscriptionAddModel?.textLine2,
        textLine3: this.subscriptionAddModel?.textLine3,
        textLine4: this.subscriptionAddModel?.textLine4,
      },
    });
  }

  onNotifySelected(selectedRows: object[]): void {
    if (selectedRows && selectedRows.length === 1) {
      const templateData = {
        template: this.subscriptionsOverviewTemplate,
        data: selectedRows[0],
      };
      this.overviewService.updateOverviewTemplate(templateData);
    } else {
      this.overviewService.closeOverview();
    }
  }

  onDoubleClick(data: any): void {
    //     window.location.href = '/customer/upload/';
  }

  search(evt: any): void {
    this.subscriptionTable.search(evt.query);
  }
  onActionSelect(action: string, element: any): void {
    console.log('action', action);
    console.log('element:', element);
  }

  onUserSubscriptionsNotifySelected(selectedRows: object[]): void {
    if (selectedRows && selectedRows.length > 0) {
      this.overviewService.updateOverviewTemplate({
        template: this.userSubscriptionsOverviewTemplate,
        data: selectedRows[0],
      });
    }
  }

  getData(tableDataModel: TableDataModel): void {
    if (this.showSubscriptionModels) {
      this.subscriptionService.getAllSubscriptionPlans(tableDataModel).subscribe(resp => {
        this.subscriptions = resp;
        const data = {
          rowData: resp,
          total: 12,
        };
        this.subscriptionTable.setData(data);
      });
    } else {
      this.subscriptionService.getAllUserSubscriptions(tableDataModel).subscribe(resp => {
        this.userSubscriptions = resp;
        const data = {
          rowData: resp,
          total: 12,
        };
        this.userSubscriptionsTable.setData(data);
      });
    }
  }
}
