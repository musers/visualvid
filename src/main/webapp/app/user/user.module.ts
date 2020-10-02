import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VisualvidSharedModule } from '../../app/shared/shared.module';
import { UserComponent} from './user.component';
import { USER_ROUTE} from './user.route';

@NgModule({
    imports: [VisualvidSharedModule, RouterModule.forChild([USER_ROUTE])],
    declarations: [UserComponent],
    exports: [UserComponent]
})

export class UserModule {
    constructor() {}
}