import { Route } from '@angular/router';
import {UserUploadComponent} from './user-upload.component';

export const USER_UPLOAD_ROUTE : Route = {
    path: 'user/upload',
    component: UserUploadComponent,
    data: {
        authorities: [],
        pageTitle: 'customerUploadForm',
    }
};
