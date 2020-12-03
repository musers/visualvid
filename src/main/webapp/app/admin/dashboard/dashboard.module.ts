import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from '../../../app/shared/shared.module';

import { DashboardComponent } from './dashboard.component';
import { DashboardLeftComponent } from './left/db-left-panel.component';
import { DashboardRightComponent } from './right/db-right-panel.component';
import { AdminVideoComponent } from './main/video-designs/admin-video.component';
import { EmployeComponent } from './main/employees/employe-component';
import { DASHBOARD_ROUTE } from './dashboard.route';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([DASHBOARD_ROUTE])],
  declarations: [DashboardComponent, DashboardLeftComponent, DashboardRightComponent, AdminVideoComponent, EmployeComponent],
  exports: [],
})
export class DashboardModule {}
