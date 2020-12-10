import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';

import { DashboardComponent } from './dashboard.component';
import { DashboardNavigatorComponent } from './navigator/navigator.component';
import { DashboardMainComponent } from './main/main.component';
import { DashboardOverviewComponent } from './overview/overview.component';
import { DASHBOARD_ROUTE } from './dashboard.route';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([DASHBOARD_ROUTE])],
  declarations: [DashboardComponent,
    DashboardNavigatorComponent,
    DashboardMainComponent,
    DashboardOverviewComponent],
  exports: [],
})
export class DashboardModule {}
