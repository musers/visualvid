import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VisualvidSharedModule } from 'app/shared/shared.module';
import { UserUploadComponent} from './user-upload.component';
import { UserUploadFormComponent} from './user-upload-form/user-upload-form.component';
import { SlideComponent } from './user-upload-form/slide/slide.component';
import { UserSlideItemComponent } from './user-upload-form/slide/slideitem/user-slide-item.component';
import { USER_UPLOAD_ROUTE} from './user-upload.route';

@NgModule({
    imports: [VisualvidSharedModule, RouterModule.forChild([USER_UPLOAD_ROUTE])],
    declarations: [UserUploadComponent, UserUploadFormComponent, SlideComponent, UserSlideItemComponent],
    exports: [UserUploadComponent, UserUploadFormComponent, SlideComponent, UserSlideItemComponent]
})

export class UserUploadModule {
    constructor() {}
}
