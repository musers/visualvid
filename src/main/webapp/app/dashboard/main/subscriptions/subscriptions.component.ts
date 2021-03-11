import { Component, OnInit, AfterViewInit, TemplateRef, ViewChild, ChangeDetectorRef } from '@angular/core';
import { SubscriptionModel } from './subscriptions.model';
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
export class DashboardSubscriptionComponent implements OnInit, ITableChangeCallback, AfterViewInit {
  subscriptions?: SubscriptionModel[];
  status?: string;
  showSubscriptionModels = true;
  cardFooterClass?: String;
  subscriptionAddModel?: SubscriptionAddModel;

  @ViewChild('subscriptionsTemplate', { static: false })
  subscriptionsTemplate?: TemplateRef<any>;

  @ViewChild('subscriptionsOverviewTemplate', { static: false })
  subscriptionsOverviewTemplate?: TemplateRef<any>;

  @ViewChild('subscriptionTable', { static: false })
  subscriptionTable: TableComponent;

  @ViewChild('userSubscriptionsTable', { static: false })
  userSubscriptionsTable: TableComponent;

  columnDefinition: ColumnSettingsModel[] = [];

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

  constructor(
    public dialog: MatDialog,
    private subscriptionService: SubscriptionService,
    public overviewService: OverviewService,
    public changeDetector: ChangeDetectorRef
  ) {}

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
      {
        name: 'action',
        displayName: 'Action',
        cellTemplate: this.subscriptionsTemplate,
      },
    ];
  }
  ngAfterViewInit(): void {
    this.getActiveTable().requestData();
  }
  enableSubscription(enableSubscription: boolean, status: string): void {
    this.showSubscriptionModels = enableSubscription;
    this.status = status;
    this.changeDetector.detectChanges();
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
  search(evt: any): void {
    return this.showSubscriptionModels ? this.subscriptionTable.search(evt.query) : this.userSubscriptionsTable.search(evt.query);
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
  onActionSelect(action: string, element: any): void {
    console.log('action', action);
    console.log('element:', element);
  }
  getActiveTable(): TableComponent {
    return this.showSubscriptionModels ? this.subscriptionTable : this.userSubscriptionsTable;
  }
  getData(tableDataModel: TableDataModel): void {
    if (this.showSubscriptionModels) {
      this.subscriptionService.getAllSubscriptionPlans(tableDataModel).subscribe(resp => {
        this.subscriptions = resp;
        const data = {
          rowData: resp,
          total: 12,
        };
        this.getActiveTable().setData(data);
      });
    }
  }
}
