import { Component, Input, OnInit,AfterViewInit,TemplateRef, ViewChild} from '@angular/core';
import { OrderModel } from 'app/order/order.model';
import { OrderService } from 'app/order/order.service';
import { ColumnSettingsModel, TablePaginationSettingsModel } from 'app/shared/table/table-settings.model';

export interface Action {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'jhi-dashboard-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['orders.scss'],
})

export class DashboardOrdersComponent implements OnInit,AfterViewInit  {
  @Input() orders?: OrderModel[];
  @ViewChild('orderStatusTemplate',{static: true}) orderStatusTemplate?: TemplateRef<any>;
  columnDefinition: ColumnSettingsModel[] = [];
  tablePaginationSettings: TablePaginationSettingsModel = {
    enablePagination: true,
    pageSize: 10,
    pageSizeOptions: [10, 20, 30],
    showFirstLastButtons: true,
  };
  rowData: Array<OrderModel> = [];
  actions: Action[] = [
    {viewValue: 'Action 1', value: 'action1'},
    {viewValue: 'Action 2', value: 'action2'},
    {viewValue: 'Action 3', value: 'action3'}
  ];
  currentAction = 'action1';
  constructor(private orderService: OrderService) {
  }

  ngAfterViewInit(): void{
    console.log('this.orderStatusTemplate',this.orderStatusTemplate);
  }
  ngOnInit(): void {
      this.columnDefinition = [
            {
              name: 'name',
              displayName: 'Name'
            },
            {
              name: 'orderId',
              displayName: 'Order ID #'
            },
            {
              name: 'orderStatus',
              displayName: 'Status',
              cellTemplate: this.orderStatusTemplate
            },
          ];
    this.orderService.findAll().subscribe(data => {
      console.log(data);
      this.rowData = data;
    });
  }
  onNotifySelected(selectedRows: object[]): void {
    console.log(selectedRows);
  }
  onDoubleClick(data: any): void {
    window.location.href = '/customer/upload/' + data.adminMediaId + '/' + data.id;
  }
  search(evt: any): void {
    console.log(evt);
    // TODO call a back-end service awith evt.query and map result to this.rowData;
  }
}
