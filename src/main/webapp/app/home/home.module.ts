import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VisualvidSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { VideoDesignsModule } from 'app/videodesigns/videodesigns.module';

@NgModule({
  imports: [VisualvidSharedModule, VideoDesignsModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class VisualvidHomeModule {}
