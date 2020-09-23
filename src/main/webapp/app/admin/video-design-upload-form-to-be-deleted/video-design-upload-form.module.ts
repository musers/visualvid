import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';

import { VideoDesignUploadFormComponent } from './video-design-upload-form.component';

import { VideoDesignUploadFormRoute } from './video-design-upload-form.route';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([VideoDesignUploadFormRoute])],
  declarations: [VideoDesignUploadFormComponent],
  exports: [VideoDesignUploadFormComponent],
})
export class VideoDesignUploadFormModule {
  constructor() {}
}
