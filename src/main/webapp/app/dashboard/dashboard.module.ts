import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { DashboardMainModule } from './main/main.module';

import { DashboardComponent } from './dashboard.component';
import { DashboardNavigatorComponent } from './navigator/navigator.component';
import { DashboardOverviewComponent } from './overview/overview.component';
import { DASHBOARD_ROUTE } from './dashboard.route';

@NgModule({
  imports: [VisualvidSharedModule, DashboardMainModule, RouterModule.forChild([DASHBOARD_ROUTE])],
  declarations: [DashboardComponent,
    DashboardNavigatorComponent,
    DashboardOverviewComponent],
  exports: [ ],
})
export class DashboardModule {}
