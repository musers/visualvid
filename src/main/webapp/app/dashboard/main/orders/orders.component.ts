import { Component, Input, OnInit, AfterViewInit, TemplateRef, ViewChild, SimpleChanges, OnChanges } from '@angular/core';
import { OrderModel } from 'app/order/order.model';
import { OrderService } from 'app/order/order.service';
import { OverviewService } from 'app/dashboard/overview/overview.service';
import { ColumnSettingsModel, ITableChangeCallback, TableDataModel } from 'app/shared/table/table-settings.model';
import { TableComponent } from 'app/shared/table/table.component';

export interface Action {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'jhi-dashboard-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['orders.scss'],
})
export class DashboardOrdersComponent implements OnInit, AfterViewInit, ITableChangeCallback, OnChanges {
  @Input() orders?: OrderModel[];
  @ViewChild('orderStatusTemplate', { static: true }) orderStatusTemplate?: TemplateRef<any>;

  @ViewChild('table', { static: false })
  table: TableComponent;

  @ViewChild('overviewTemplate', { static: false })
  overviewTemplate?: TemplateRef<any>;

  @ViewChild('actionTemplate', { static: true })
  actionTemplate?: TemplateRef<any>;

  columnDefinition: ColumnSettingsModel[] = [];

  rowData: Array<OrderModel> = [];
  actions: Action[] = [
    { viewValue: 'Action 1', value: 'action1' },
    { viewValue: 'Action 2', value: 'action2' },
    { viewValue: 'Action 3', value: 'action3' },
  ];
  currentAction = 'action1';

  constructor(private orderService: OrderService, private overviewService: OverviewService) {}

  ngAfterViewInit(): void {
    console.log('ngAfterViewInit');
    this.table.requestData();
  }

  ngOnInit(): void {
    this.columnDefinition = [
      {
        name: 'salesId',
        displayName: 'Sales Id #',
      },
      {
        name: 'orderId',
        displayName: 'Order ID #',
      },
      {
        name: 'name',
        displayName: 'Name',
      },
      {
        name: 'assignTo',
        displayName: 'Assign to',
      },
      {
        name: 'orderStatus',
        displayName: 'Status',
        cellTemplate: this.orderStatusTemplate,
      },
      {
        name: 'timeLeft',
        displayName: 'Time Left',
      },
      {
        name: 'action',
        displayName: 'Action #',
        cellTemplate: this.actionTemplate,
      },
    ];
    this.orderService.findAll().subscribe(data => {
      console.log(data);
      this.rowData = data;
    });
  }

  onRowSelect(selectedRows: object[]): void {
    this.overviewService.updateOverviewTemplate(this.overviewTemplate, selectedRows);
  }

  search(evt: any): void {
    if (this.table) {
      this.table.search(evt.query);
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log('ngOnchanges');
    if (changes.searchText) {
      this.search(changes.searchText.currentValue);
    }
  }

  getData(tableDataModel: TableDataModel): void {
    // this.orderService.getAll(tableDataModel).subscribe(resp => {
    this.orderService.findAll().subscribe(resp => {
      const data = {
        rowData: resp,
        total: 12,
      };
      this.table.setData(data);
    });
  }

  onActionSelect(action: string, element: any): void {
    console.log('action', action);
    console.log('element:', element);
  }
}
