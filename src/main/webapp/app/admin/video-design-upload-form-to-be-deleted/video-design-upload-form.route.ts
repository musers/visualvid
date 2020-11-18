import { Route } from '@angular/router';

import { VideoDesignUploadFormComponent } from './video-design-upload-form.component';

export const VideoDesignUploadFormRoute: Route = {
  path: '',
  component: VideoDesignUploadFormComponent,
  data: {
    pageTitle: 'uploadFormTitle',
  },
};
