import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';

import { AdminUploadComponent } from './admin-upload.component';
import { AdminUploadRoute } from './admin-upload.route';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([AdminUploadRoute])],
  declarations: [AdminUploadComponent],
  exports: [AdminUploadComponent],
})
export class AdminUploadModule {
  constructor() {}
}
