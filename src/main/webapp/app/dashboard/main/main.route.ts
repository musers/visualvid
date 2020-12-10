import { Route } from '@angular/router';
import { DbMainPanelComponent } from './db-main-panel.component';
// import { AdminVideoComponent } from './video-designs/admin-video.component';
// import { AdminOrderComponent } from './orders/admin-order.component';

export const DB_MAIN_PANEL_ROUTE: Route = {
  path: '',
  component: DbMainPanelComponent,
  data: {
    authorities: [],
    pageTitle: '',
  },
//   children: [
//     { path: 'videodesigns', component: AdminVideoComponent, outlet: 'videodesigns' },
//     { path: '/admin/dashboard/orders', component: AdminOrderComponent, outlet: 'orders' },
//   ],
};
