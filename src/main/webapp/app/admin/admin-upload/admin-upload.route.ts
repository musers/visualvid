import { Route } from '@angular/router';

import { AdminUploadComponent } from './admin-upload.component';

export const AdminUploadRoute: Route = {
  path: '',
  component: AdminUploadComponent,
  data: {
    pageTitle: 'uploadFormTitle',
  },
};
