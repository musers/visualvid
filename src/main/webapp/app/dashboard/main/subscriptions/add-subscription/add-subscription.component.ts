import { Component, Input, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SubscriptionAddModel } from './add-subscription.model';
import { SubscriptionService } from '../subscriptions.service';

@Component({
  selector: 'jhi-add-subscription',
  templateUrl: './add-subscription.component.html',
  styleUrls: ['add-subscription.scss'],
})
export class DashboardAddSubscriptionComponent implements OnInit {
  subscriptionAddModel?: SubscriptionAddModel;
  error = false;
  success = false;

  constructor(private subscriptionsService: SubscriptionService, @Inject(MAT_DIALOG_DATA) data?: SubscriptionAddModel) {
    console.log('subscription plan name:', data?.name);
    this.subscriptionAddModel = data;
  }

  ngOnInit(): void {}

  save(): void {
    this.subscriptionsService.save(this.subscriptionAddModel).subscribe(
      () => {
        this.success = true;
        window.location.href = '/dashboard/subscriptions';
      },
      () => (this.error = true)
    );
  }
}
