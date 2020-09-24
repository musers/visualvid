import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';

import { AdminUploadComponent } from './admin-upload.component';
import { AdminUploadFormComponent } from './admin-upload-form/admin-upload-form.component';
import { AdminUploadRoute } from './admin-upload.route';
import { SlideComponent } from './admin-upload-form/slide/slide.component';
import { SlideItemComponent } from './admin-upload-form/slide/slideitem/slideitem.component';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([AdminUploadRoute])],
  declarations: [AdminUploadComponent, AdminUploadFormComponent, SlideComponent, SlideItemComponent],
  exports: [AdminUploadComponent, AdminUploadFormComponent],
})
export class AdminUploadModule {
  constructor() {}
}
