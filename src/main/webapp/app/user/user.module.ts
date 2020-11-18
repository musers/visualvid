import { NgModule } from '@angular/core';

import { VisualvidSharedModule } from '../../app/shared/shared.module';
import { UserUploadModule } from '../../app/user/user-upload/user-upload.module';
@NgModule({
    imports: [VisualvidSharedModule, UserUploadModule],
    declarations: [],
    exports: []
})

export class UserModule {
    constructor() {}
}
