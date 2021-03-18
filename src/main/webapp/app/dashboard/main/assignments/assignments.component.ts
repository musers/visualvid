import { Component,Input, OnInit, AfterViewInit, ViewChild, TemplateRef, SimpleChanges, OnChanges} from '@angular/core';
import { AssignmentsModel } from './assignments.model';
import { AssignmentsService } from './assignments.service';
import { OverviewService } from 'app/dashboard/overview/overview.service';
import { ColumnSettingsModel, ITableChangeCallback, TableDataModel } from 'app/shared/table/table-settings.model';
import { TableComponent } from 'app/shared/table/table.component';

export interface Action {
  id: string;
  name: string;
}

@Component({
  selector: 'jhi-assignments',
  templateUrl: './assignments.component.html',
  styleUrls: ['assignments.scss'],
})
export class DashboardAssignmentsComponent implements OnInit, ITableChangeCallback, AfterViewInit, OnChanges  {
  @Input() assignments?: AssignmentsModel[];

  @ViewChild('table', { static: false })
  table: TableComponent;
  @ViewChild('overviewTemplate', { static: false })
  overviewTemplate?: TemplateRef<any>;
  @ViewChild('actionTemplate', {static: true})
  actionTemplate?: TemplateRef<any>;


  columnDefinition: ColumnSettingsModel[] = [];
  totalCount = 0;
  completedCount = 0;
  inProgressCount = 0;
  notCompletedCount = 0;

  actions: Action[] = [
    { name: 'Marketing Team', id: 'marketingTeam' },
    { name: 'Finance Team', id: 'financeTeam' },
    { name: 'HR Team', id: 'hrTeam' },
  ];

  constructor(
  protected assignmentsService: AssignmentsService,
  protected overviewService: OverviewService) {}

  ngOnInit(): void {
    console.log('ngInit')
    this.columnDefinition = [
      {
        name: 'orderId',
        displayName: 'OrderId',
        disableSorting: false,
      },
      {
        name: 'name',
        displayName: 'Name',
        disableSorting: false,
      },
      {
        name: 'assignTo',
        displayName: 'AssignTo',
        disableSorting: false,
      },{
        name: 'status',
        displayName: 'Status',
        disableSorting: false,
      },{
        name: 'timeLeft',
        displayName: 'TimeLeft',
        disableSorting: false,
      },{
        name: 'action',
        displayName: 'Action',
        cellTemplate: this.actionTemplate
      }
    ]
    this.assignmentsService.getStats().subscribe(data => {
      this.totalCount = data.totalCount;
      this.completedCount = data.completedCount;
      this.inProgressCount = data.inProgressCount;
      this.notCompletedCount = data.notCompletedCount;
    })
  }
  ngAfterViewInit(): void {
    this.table.requestData();
  }
  onRowSelect(selectedRows: object[]): void {
    this.overviewService.updateOverviewTemplate(this.overviewTemplate,selectedRows);
  }
  search(evt: any): void {
    if(this.table){
      this.table.search(evt.query);
    }
  }
  ngOnChanges(changes: SimpleChanges) {
    if (changes.searchText) {
      this.search(changes.searchText.currentValue);
    }
  }
  getData(tableDataModel: TableDataModel): void {
    this.assignmentsService.getAll(tableDataModel).subscribe(resp => {
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
