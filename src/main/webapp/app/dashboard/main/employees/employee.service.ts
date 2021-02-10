import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../../../../app/app.constants';
import { EmployeeModel } from './employe.model';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  public resourceUrl = SERVER_API_URL + 'api/users';

  constructor(private http: HttpClient) {}

  findAll(userType: string): Observable<EmployeeModel[]> {
    return this.http.get<EmployeeModel[]>(this.resourceUrl + '/type/' + userType);
  }
}
