import { Component, Input, OnInit } from '@angular/core';
import { EmployeeModel } from './employe.model';
import { MatDialog } from '@angular/material/dialog';
import { DashboardAddEmployeeComponent } from './add-employee/add-employe.component';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'jhi-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['employe.scss'],
})
export class DashboardEmployeComponent implements OnInit {
  @Input() employees?: EmployeeModel[];
  employee?: EmployeeModel;

  constructor(public dialog: MatDialog, protected employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.employeeService.findAll('EMPLOYEE').subscribe((res: EmployeeModel[]) => {
      this.employees = res;
    });
  }
  search(evt: any): void {
    console.log(evt.query);
    this.employeeService.search('EMPLOYEE',evt.query).subscribe((res: EmployeeModel[]) => {
      this.employees = res;
    });
  }
  addNew(): void {
    this.dialog.open(DashboardAddEmployeeComponent, {
      width: '20rem',
      height: '30rem',
      data: {
        name: this.employee?.firstName,
        lastName: this.employee?.lastName,
        mobile: this.employee?.phone,
        role: this.employee?.role,
        address: this.employee?.address,
      },
    });
  }
}
