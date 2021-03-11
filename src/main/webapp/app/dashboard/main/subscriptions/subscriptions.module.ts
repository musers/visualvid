import { NgModule } from '@angular/core';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { UserSubscriptionsComponent } from './usersubscriptions/user-subscriptions.component';

@NgModule({
  imports: [VisualvidSharedModule],
  declarations: [UserSubscriptionsComponent],
  exports: [UserSubscriptionsComponent],
})
export class DashboardSubscriptionsModule {}
