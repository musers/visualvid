import { Route } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { DashboardOrdersComponent } from './main/orders/orders.component';
import { DashboardVideoDesignsComponent } from './main/videodesigns/videodesigns.component';
import { DashboardEmployeComponent } from './main/employees/employe-component';
import { DashboardAssignmentsComponent } from './main/assignments/assignments.component';

export const DASHBOARD_ROUTE: Route = {
  path: 'dashboard',
  component: DashboardComponent,
  children: [
    { path: 'orders', component: DashboardOrdersComponent },
    { path: 'videodesigns', component: DashboardVideoDesignsComponent },
    { path: 'employees', component: DashboardEmployeComponent },
    { path: 'assignments', component: DashboardAssignmentsComponent },
  ],
  data: {
    authorities: [],
    pageTitle: 'dashboardForm',
  },
};
