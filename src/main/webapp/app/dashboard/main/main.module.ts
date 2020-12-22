import { NgModule } from '@angular/core';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { DashboardMainComponent } from './main.component';
import { DashboardOrdersComponent } from './orders/orders.component';
import { DashboardVideoDesignsComponent } from './videodesigns/videodesigns.component';
import { DashboardEmployeComponent } from './employees/employe-component';
import { DashboardAddEmployeeComponent } from './employees/add-employee/add-employe.component';
import { DashboardAssignmentsComponent } from './assignments/assignments.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [VisualvidSharedModule, FormsModule, ReactiveFormsModule],
  declarations: [
    DashboardMainComponent,
    DashboardOrdersComponent,
    DashboardVideoDesignsComponent,
    DashboardEmployeComponent,
    DashboardAssignmentsComponent,
    DashboardAddEmployeeComponent,
  ],
  exports: [DashboardMainComponent],
})
export class DashboardMainModule {}
