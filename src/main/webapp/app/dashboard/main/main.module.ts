import { NgModule } from '@angular/core';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { DashboardMainComponent } from './main.component';
import { DashboardOrdersComponent } from './orders/orders.component';
import { DashboardVideoDesignsComponent } from './videodesigns/videodesigns.component';
import { DashboardEmployeComponent } from './employees/employe-component';
import { DashboardAssignmentsComponent } from './assignments/assignments.component';

@NgModule({
  imports: [VisualvidSharedModule],
  declarations: [
    DashboardMainComponent,
    DashboardOrdersComponent,
    DashboardVideoDesignsComponent,
    DashboardEmployeComponent,
    DashboardAssignmentsComponent,
  ],
  exports: [DashboardMainComponent],
})
export class DashboardMainModule {}
