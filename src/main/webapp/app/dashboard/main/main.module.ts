import { NgModule } from '@angular/core';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { DashboardSubscriptionsModule } from 'app/dashboard/main/subscriptions/subscriptions.module';
import { DashboardMainComponent } from './main.component';
import { DashboardOrdersComponent } from './orders/orders.component';
import { DashboardVideoDesignsComponent } from './videodesigns/videodesigns.component';
import { DashboardEmployeComponent } from './employees/employe-component';
import { DashboardAddEmployeeComponent } from './employees/add-employee/add-employe.component';
import { DashboardAssignmentsComponent } from './assignments/assignments.component';
import { DashboardCategoriesComponent } from './categories/categories.component';
import { CatTreeActionComponent } from './categories/dialogs/cattreeaction/cattreeaction.component';
import { DashboardSubscriptionComponent } from './subscriptions/subscriptions.component';
import { DashboardAddSubscriptionComponent } from './subscriptions/add-subscription/add-subscription.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [VisualvidSharedModule, DashboardSubscriptionsModule, FormsModule, ReactiveFormsModule],
  declarations: [
    DashboardMainComponent,
    DashboardOrdersComponent,
    DashboardVideoDesignsComponent,
    DashboardEmployeComponent,
    DashboardAssignmentsComponent,
    DashboardAddEmployeeComponent,
    DashboardCategoriesComponent,
    CatTreeActionComponent,
    DashboardSubscriptionComponent,
    DashboardAddSubscriptionComponent,
  ],
  exports: [DashboardMainComponent],
})
export class DashboardMainModule {}
