import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { AdminCategory } from 'app/admin/admin-upload/admin-upload-form/admincategory.model';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  public resourceUrl = SERVER_API_URL + '/api/admin/project-upload';
  public categoryUrl = SERVER_API_URL + '/api/categories/';
  constructor(protected httpClient: HttpClient) {}

  public save(adminMedia: AdminMedia): Observable<any> {
    if (adminMedia.id) {
      return this.httpClient.put(this.resourceUrl, adminMedia);
    } else {
      return this.httpClient.post(this.resourceUrl, adminMedia);
    }
  }
  public get(id: String): Observable<any> {
    return this.httpClient.get(this.resourceUrl + '/' + id);
  }
  public getAll(categoryId: String): Observable<any> {
    return this.httpClient.get(this.resourceUrl + '/page/category/' + categoryId);
  }
  public getCategories(): Observable<AdminCategory[]> {
    return this.httpClient.get<AdminCategory[]>(this.categoryUrl);
  }
}
