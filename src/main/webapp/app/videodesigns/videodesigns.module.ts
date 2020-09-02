import { NgModule } from '@angular/core';
import { VisualvidSharedModule } from 'app/shared/shared.module';

import { VideoDesignsComponent } from './videodesigns.component';
import { VideoListingComponent } from './videolisting/videolisting.component';
import { VideoItemComponent } from './videoitem/videoitem.component';

@NgModule({
  imports: [VisualvidSharedModule],
  declarations: [VideoDesignsComponent, VideoListingComponent, VideoItemComponent],
  exports: [VideoDesignsComponent],
})
export class VideoDesignsModule {
  constructor() {}
}
