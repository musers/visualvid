import { NgModule } from '@angular/core';
import { VisualvidSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { RouterModule } from '@angular/router';
import { TableModule } from 'app/shared/table/table.module';
import { SearchComponent } from 'app/shared/search/search.component';
import { LoaderComponent } from 'app/shared/loader/loader.component';

@NgModule({
  imports: [VisualvidSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    SearchComponent,
    HasAnyAuthorityDirective,
    LoaderComponent
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    VisualvidSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    RouterModule,
    TableModule,
    SearchComponent,
    LoaderComponent
  ],
})
export class VisualvidSharedModule {}
