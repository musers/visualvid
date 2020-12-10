import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { AdminVideoComponent } from './video-designs/admin-video.component';
import { AdminOrderComponent } from './orders/admin-order.component';
import { DbMainPanelComponent } from './db-main-panel.component';
import { DB_MAIN_PANEL_ROUTE } from './db-main-panel.route';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([DB_MAIN_PANEL_ROUTE])],
  declarations: [DbMainPanelComponent, AdminVideoComponent, AdminOrderComponent],
  exports: [DbMainPanelComponent],
})
export class DbMainPanelModule {}
