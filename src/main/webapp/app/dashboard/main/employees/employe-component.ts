import { Component, Input, OnInit } from '@angular/core';
import { EmployeeModel } from './employe.model';
import { MatDialog } from '@angular/material/dialog';
import { DashboardAddEmployeeComponent } from './add-employee/add-employe.component';

@Component({
  selector: 'jhi-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['employe.scss'],
})
export class DashboardEmployeComponent implements OnInit {
  @Input() employees?: EmployeeModel[];
  employee?: EmployeeModel;

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
    this.employees = [
      {
        name: 'Anil Bobba',
        lastName: 'Bobba',
        email: 'anil@gmail.com',
        mobile: '9703730427',
        role: 'CREATIVE_DIRECTOR',
        address: 'Hyderbad',
        login: 'anil@gmail.com',
      },
      {
        name: 'Bala Gangadhar',
        lastName: 'Gangadhar',
        email: 'bala@gmail.com',
        mobile: '9703730427',
        role: 'DESIGNER',
        address: 'Hyderbad',
        login: 'bala@gmail.com',
      },
      {
        name: 'Srikanth Boddupally',
        lastName: 'Boddupally',
        email: 'srikanth@gmail.com',
        mobile: '9703730427',
        role: 'DESIGNER',
        address: 'Hyderbad',
        login: 'srikanth@gmail.com',
      },
      {
        name: 'Sai Thota',
        lastName: 'Thota',
        email: 'sai@gmail.com',
        mobile: '9703730427',
        role: 'DESIGNER',
        address: 'Hyderbad',
        login: 'sai@gmail.com',
      },
    ];
  }

  addNew(): void {
   this.dialog.open(DashboardAddEmployeeComponent, {
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
