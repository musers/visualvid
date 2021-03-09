import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgJhipsterModule } from 'ng-jhipster';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClientModule } from '@angular/common/http';
import { AngularSvgIconModule } from 'angular-svg-icon';
import { TranslateModule } from '@ngx-translate/core';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatTabsModule } from '@angular/material/tabs';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { PortalModule } from '@angular/cdk/portal';
import { OverlayModule } from '@angular/cdk/overlay';
import {MatSelectModule} from '@angular/material/select';
import { MatTreeModule } from '@angular/material/tree';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { LottieModule } from 'ngx-lottie';
import player from 'lottie-web';
import { LoaderComponent } from 'app/shared/loader/loader.component';

export function playerFactory() {
  return player;
}

@NgModule({
  imports: [HttpClientModule, DragDropModule,
  MatTabsModule,MatTreeModule,
  LottieModule.forRoot({ player: playerFactory }),
  AngularSvgIconModule.forRoot()],
  declarations: [
    LoaderComponent
  ],
  exports: [
    FormsModule,
    CommonModule,
    NgbModule,
    NgJhipsterModule,
    InfiniteScrollModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    TranslateModule,
    HttpClientModule,
    AngularSvgIconModule,
    DragDropModule,
    MatTabsModule,
    MatChipsModule,
    MatIconModule,
    MatDialogModule,
    MatCheckboxModule,
    MatSortModule,
    MatPaginatorModule,
    MatTableModule,
    PortalModule,
    OverlayModule,
    MatSelectModule,
    MatTreeModule,
    MatMenuModule,
    MatButtonModule,
    MatToolbarModule,
    MatInputModule,
    LottieModule,
    LoaderComponent
  ],
})
export class VisualvidSharedLibsModule {}
