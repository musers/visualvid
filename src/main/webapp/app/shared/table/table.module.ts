import { NgModule } from '@angular/core';
import { VisualvidSharedLibsModule } from 'app/shared/shared-libs.module';
import { TableComponent } from './table.component';
// import { TablePaginationSettingsModel, ColumnSettingsModel } from './table-settings.model';

export { TableComponent } from './table.component';
// export { TablePaginationSettingsModel, ColumnSettingsModel } from './table-settings.model';
@NgModule({
    imports: [VisualvidSharedLibsModule],
    declarations: [TableComponent],
    exports: [TableComponent]
})

export class TableModule { }
