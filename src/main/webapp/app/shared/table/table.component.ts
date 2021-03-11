import { Component, Input, AfterViewInit, ViewChild, OnInit, Output, EventEmitter } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { SelectionModel } from '@angular/cdk/collections';
import { merge } from 'rxjs';
import { TemplatePortalDirective } from '@angular/cdk/portal';
import { OverlayService } from 'app/shared/overlay/overlay.service';

import { TablePaginationSettingsModel, ColumnSettingsModel, ITableChangeCallback, TableDataModel } from './table-settings.model';

@Component({
  selector: 'jhi-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit, AfterViewInit {
  @Input() enableCheckbox: boolean;
  @Input() allowMultiSelect: boolean;
  @Input() sqColumnDefinition: ColumnSettingsModel[];
  @Input() sqPaginationConfig?: TablePaginationSettingsModel = {
    enablePagination: true,
    pageSize: 1,
    pageSizeOptions: [1, 2, 3],
    showFirstLastButtons: true,
  };
  @Input() tableChange: ITableChangeCallback;
  @Input() rowData: object[];

  @Output() getSelectedRows = new EventEmitter();
  @Output() onDoubleClick = new EventEmitter();

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild('loading') loadingTemplate: TemplatePortalDirective;

  total = 0;
  selection = new SelectionModel<{}>();
  selectedRowIndex = -1;
  columnNames: string[] = [];
  searchText = '';

  constructor(private overlayService: OverlayService) {}
  ngOnInit(): void {
    console.log('columnNames');
    for (const column of this.sqColumnDefinition) {
      this.columnNames.push(column.name);
    }
    // Condition to add selection column to the table
    if (this.enableCheckbox && !this.columnNames.includes('select')) {
      this.columnNames.splice(0, 0, 'select');
      this.sqColumnDefinition.splice(0, 0, {
        name: 'select',
        displayName: '#',
      });
    }
    this.selection = new SelectionModel<{}>(this.allowMultiSelect, []);
  }

  ngAfterViewInit(): void {
    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));
    merge(this.sort.sortChange, this.paginator.page).subscribe(() => {
      this.requestData();
    });
  }
  isAllSelected(): boolean {
    const numSelected = this.selection.selected.length;
    return numSelected === this.rowData.length;
  }
  masterToggle(): void {
    this.isAllSelected() ? this.selection.clear() : this.rowData.forEach(row => this.selection.select(row));
    this.getSelectedRows.emit(this.selection.selected);
  }
  rowSelect(): void {
    this.getSelectedRows.emit(this.selection.selected);
  }
  doubleClick(data: any): void {
    this.onDoubleClick.emit(data);
  }
  highlight(row: any): void {
    this.selectedRowIndex = row.position;
  }
  pageChange(event: any): void {}
  search(searchText: string): void {
    this.searchText = searchText;
    this.requestData();
  }
  setData(data: TableDataModel): void {
    this.selection.clear();
    this.rowData = data.rowData;
    this.total = data.total;
    this.overlayService.closeOverlay();
  }
  requestData(): void {
    this.overlayService.openTemplateOverlay(this.loadingTemplate);
    if (this.tableChange) {
      this.tableChange.getData(this.getParamObj());
    }
  }
  getParamObj(): any {
    return {
      sort: this.sort ? this.sort.active : '',
      order: this.sort ? this.sort.direction : '',
      page: this.paginator ? this.paginator.pageIndex + 1 : 1,
      perPage: this.paginator ? this.paginator.pageSize : 10,
      searchText: this.searchText,
    };
  }
}
