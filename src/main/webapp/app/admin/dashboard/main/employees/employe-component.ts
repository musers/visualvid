import { Component, Input, OnInit } from '@angular/core';
import { EmployeeModel } from './employe.model';

@Component({
  selector: 'jhi-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['employe.scss'],
})
export class EmployeComponent implements OnInit {
  @Input() employees?: EmployeeModel[];

  constructor() {}

  ngOnInit(): void {
    this.employees = [
      {
        name: 'Anil Bobba',
        email: 'anil@gmail.com',
        mobile: '9703730427',
        role: 'CREATIVE_DIRECTOR',
      },
      {
        name: 'Bala Gangadhar',
        email: 'bala@gmail.com',
        mobile: '9703730427',
        role: 'DESIGNER',
      },
      {
        name: 'Srikanth Boddupally',
        email: 'srikanth@gmail.com',
        mobile: '9703730427',
        role: 'DESIGNER',
      },
      {
        name: 'Srikanth Boddupally',
        email: 'srikanth@gmail.com',
        mobile: '9703730427',
        role: 'DESIGNER',
      },
    ];
  }
}
