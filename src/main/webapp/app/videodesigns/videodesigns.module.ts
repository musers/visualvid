import { NgModule } from '@angular/core';
import { VisualvidSharedModule } from 'app/shared/shared.module';

import { VideoDesignsComponent } from './videodesigns.component';
import { VideoListingComponent } from './videolisting/videolisting.component';

@NgModule({
  imports: [VisualvidSharedModule],
  declarations: [VideoDesignsComponent, VideoListingComponent],
  exports: [VideoDesignsComponent],
})
export class VideoDesignsModule {
  constructor() {}
}
