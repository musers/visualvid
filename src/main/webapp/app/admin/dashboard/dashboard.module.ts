import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from '../../../app/shared/shared.module';
import { DbMainPanelModule } from './main/db-main-panel.module';

import { DashboardComponent } from './dashboard.component';
import { DashboardLeftComponent } from './left/db-left-panel.component';
import { DashboardRightComponent } from './right/db-right-panel.component';
import { DASHBOARD_ROUTE } from './dashboard.route';

@NgModule({
  imports: [VisualvidSharedModule, DbMainPanelModule, RouterModule.forChild([DASHBOARD_ROUTE])],
  declarations: [DashboardComponent, DashboardLeftComponent, DashboardRightComponent],
  exports: [],
})
export class DashboardModule {}
