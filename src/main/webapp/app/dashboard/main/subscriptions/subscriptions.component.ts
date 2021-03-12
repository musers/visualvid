import { Component, ChangeDetectorRef } from '@angular/core';
import { SubscriptionAddModel } from './add-subscription/add-subscription.model';
import { SubscriptionService } from './subscriptions.service';
import { MatDialog } from '@angular/material/dialog';
import { DashboardAddSubscriptionComponent } from './add-subscription/add-subscription.component';
import { OverviewService } from 'app/dashboard/overview/overview.service';

@Component({
  selector: 'jhi-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['subscriptions.scss'],
})
export class DashboardSubscriptionComponent {
  status?: string;
  searchText = '';
  showSubscriptionModels = true;
  cardFooterClass?: String;
  subscriptionAddModel?: SubscriptionAddModel;

  constructor(
    public dialog: MatDialog,
    private subscriptionService: SubscriptionService,
    public overviewService: OverviewService,
    public changeDetector: ChangeDetectorRef
  ) {}

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
    this.searchText = evt.query;
  }
}
