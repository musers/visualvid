import { NgModule } from '@angular/core';
// import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { DashboardMainComponent } from './main.component';
import { DashboardOrdersComponent } from './orders/orders.component';
// import { DB_MAIN_PANEL_ROUTE } from './db-main-panel.route';

@NgModule({
  imports: [VisualvidSharedModule],
  declarations: [DashboardMainComponent, DashboardOrdersComponent],
  exports: [ DashboardMainComponent ],
})
export class DashboardMainModule {}
