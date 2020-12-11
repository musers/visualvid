import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { VisualvidSharedModule } from 'app/shared/shared.module';
import { EditorModule } from 'primeng/editor';
import { ItemModule } from 'app/item/item.module';
import { UserUploadModule } from 'app/user/user-upload/user-upload.module';

import { AdminUploadComponent } from './admin-upload.component';
import { AdminUploadFormComponent } from './admin-upload-form/admin-upload-form.component';
import { AdminUploadRoute } from './admin-upload.route';
import { SlideComponent } from './admin-upload-form/slide/slide.component';
import { SlideItemComponent } from './admin-upload-form/slide/slideitem/slideitem.component';
import { PreviewComponent } from 'app/admin/admin-upload/preview/preview.component';

@NgModule({
  imports: [VisualvidSharedModule, EditorModule, ItemModule, UserUploadModule, RouterModule.forChild([AdminUploadRoute])],
  declarations: [AdminUploadComponent, AdminUploadFormComponent, SlideComponent, SlideItemComponent, PreviewComponent],
  exports: [AdminUploadComponent, AdminUploadFormComponent],
})
export class AdminUploadModule {
  constructor() {}
}
