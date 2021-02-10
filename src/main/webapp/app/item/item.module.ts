import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VisualvidSharedModule } from '../../app/shared/shared.module';
import { ItemComponent } from './item.component';
import { ITEM_ROUTE } from './item.route';

@NgModule({
  imports: [VisualvidSharedModule, RouterModule.forChild([ITEM_ROUTE])],
  declarations: [ItemComponent],
  exports: [ItemComponent],
})
export class ItemModule {}
