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

  addNew(): void {
    const dialogRef = this.dialog.open(DashboardAddEmployeeComponent, {
      width: '20rem',
      height: '30rem',
      data: {
        name: this.employee?.name,
        lastName: this.employee?.name,
        mobile: this.employee?.mobile,
        role: this.employee?.role,
        address: this.employee?.address,
      },
    });
  }
}
