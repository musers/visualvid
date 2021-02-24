import { Component, Input, OnInit } from '@angular/core';
import { SubscriptionModel } from './subscriptions.model';
import { UserSubscriptionModel } from './user-subscription.model';
import { SubscriptionAddModel } from './add-subscription/add-subscription.model';
import { MatDialog } from '@angular/material/dialog';
import { DashboardAddSubscriptionComponent } from './add-subscription/add-subscription.component';

@Component({
  selector: 'jhi-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['subscriptions.scss'],
})
export class DashboardSubscriptionComponent implements OnInit {
  @Input() subscriptions?: SubscriptionModel[];
  @Input() userSubscriptions?: UserSubscriptionModel[];
  @Input() status?: String;
  @Input() showSubscriptionModels?: Boolean = true;
  @Input() cardFooterClass?: String;
  subscriptionAddModel?: SubscriptionAddModel;

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
    this.getSubscriptionModels();
  }

  getUserSubscriptions(status: String) {
    this.showSubscriptionModels = false;
    this.status = status;
    this.subscriptions = [];
    this.userSubscriptions = [
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
    ];
  }

  getSubscriptionModels() {
    this.showSubscriptionModels = true;
    this.status = '';
    this.subscriptions = [
      {
        id: '0001',
        name: 'Basic',
        price: '550$|7500$',
        orderCount: '100/Month',
        revisions: 'Revision 1',
        status: 'Active',
        action: '',
      },
      {
        id: '0002',
        name: 'Professional',
        price: '750$|8500$',
        orderCount: '100/Month',
        revisions: 'Revision 2',
        status: 'Active',
        action: '',
      },
      {
        id: '0003',
        name: 'Life Time',
        price: '950$|10500$',
        orderCount: 'Unlimited',
        revisions: 'Revision 2',
        status: 'Active',
        action: '',
      },
    ];
  }

  addNew(): void {
    this.dialog.open(DashboardAddSubscriptionComponent, {
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
}
