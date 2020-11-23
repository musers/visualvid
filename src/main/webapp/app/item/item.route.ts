import { Route } from '@angular/router';
import { ItemComponent } from './item.component';

export const ITEM_ROUTE: Route = {
  path: 'item',
  component: ItemComponent,
  data: {
    authorities: [],
    pageTitle: 'itemForm',
  },
};
