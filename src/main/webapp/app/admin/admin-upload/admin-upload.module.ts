import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';

import { AdminUploadComponent } from './admin-upload.component';
import { AdminUploadFormComponent } from './admin-upload-form/admin-upload-form.component';
import { AdminUploadRoute } from './admin-upload.route';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([AdminUploadRoute])],
  declarations: [AdminUploadComponent, AdminUploadFormComponent],
  exports: [AdminUploadComponent, AdminUploadFormComponent],
})
export class AdminUploadModule {
  constructor() {}
}
