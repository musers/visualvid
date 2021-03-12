import { NgModule } from '@angular/core';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { UserSubscriptionsComponent } from './usersubscriptions/user-subscriptions.component';
import { BaseSubscriptionsComponent } from './basesubscriptions/base-subscriptions.component';

@NgModule({
  imports: [VisualvidSharedModule],
  declarations: [UserSubscriptionsComponent, BaseSubscriptionsComponent],
  exports: [UserSubscriptionsComponent, BaseSubscriptionsComponent],
})
export class DashboardSubscriptionsModule {}
