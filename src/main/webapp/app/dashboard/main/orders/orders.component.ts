import { Component, Input, OnInit } from '@angular/core';
import { OrderModel } from 'app/order/order.model';
import { OrderService } from 'app/order/order.service';
import { ColumnSettingsModel, TablePaginationSettingsModel } from 'app/shared/table/table-settings.model';

@Component({
  selector: 'jhi-dashboard-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['orders.scss'],
})
export class DashboardOrdersComponent implements OnInit {
  @Input() orders?: OrderModel[];
  columnDefinition: ColumnSettingsModel[] = [];
  tablePaginationSettings: TablePaginationSettingsModel = {
    enablePagination: true,
    pageSize: 10,
    pageSizeOptions: [10, 20, 30],
    showFirstLastButtons: true,
  };
  rowData: Array<OrderModel> = [];
  constructor(private orderService: OrderService) {
    this.columnDefinition = [
      {
        name: 'name',
        displayName: 'Name',
        disableSorting: false,
      },
      {
        name: 'orderId',
        displayName: 'Order ID #',
        disableSorting: false,
      },
      {
        name: 'orderStatus',
        displayName: 'Status',
        disableSorting: false,
      },
    ];
  }

  ngOnInit(): void {
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
