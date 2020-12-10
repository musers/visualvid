import { Route } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { DashboardOrdersComponent } from './main/orders/orders.component';
import { DashboardVideoDesignsComponent } from './main/videodesigns/videodesigns.component';

export const DASHBOARD_ROUTE: Route = {
  path: 'dashboard',
  component: DashboardComponent,
    children: [
      { path: 'orders', component: DashboardOrdersComponent},
      { path: 'videodesigns', component: DashboardVideoDesignsComponent }
    ],
  data: {
    authorities: [],
    pageTitle: 'dashboardForm',
  },
};
