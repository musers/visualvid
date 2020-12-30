import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmployeeModel } from '../employe.model';
import { UserService } from 'app/core/user/user.service';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-add-employe',
  templateUrl: 'add-employe.component.html',
  styleUrls: ['add-employe.scss'],
})
export class DashboardAddEmployeeComponent {
  employee?: EmployeeModel;
  error = false;
  success = false;

  constructor(private userService: UserService, private alertService: JhiAlertService, @Inject(MAT_DIALOG_DATA) data?: EmployeeModel) {
    console.log('employee name:', data?.firstName);
    this.employee = data;
  }

  saveEmployee(): void {
    console.log('employee data from popup: ', this.employee);
    if (this.employee) {
      this.employee.login = this.employee.email;
      this.employee.userType = 'EMPLOYEE';
      this.userService.create(this.employee).subscribe(
        () => {
          this.success = true;
          window.location.href = '/dashboard/employees';
        },
        () => (this.error = true)
      );
    }
  }
}
