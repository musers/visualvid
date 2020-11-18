import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { AdminCategory } from 'app/admin/admin-upload/admin-upload-form/admincategory.model';
import { AdminMedia } from 'app/admin/admin-upload/admin-upload-form/adminmedia.model';

@Injectable({ providedIn: 'root' })
export class AdminMediaService {
  public resourceUrl = SERVER_API_URL + '/api/admin/project-upload';
  public categoryUrl = SERVER_API_URL + '/api/categories/';
  constructor(protected httpClient: HttpClient) {}

  public save(adminMedia: AdminMedia): Observable<any> {
    return this.httpClient.post(this.resourceUrl, adminMedia);
  }
  public get(id: String): Observable<any> {
    return this.httpClient.get(this.resourceUrl+'/'+id);
  }
  public getAll(categoryId: String): Observable<any> {
    console.log('Fetching videos under categoryId: ', categoryId);
    // TODO need to get by cateogiry ids
    return this.httpClient.get(this.resourceUrl);
  }
  public getCategories(): Observable<AdminCategory[]> {
    return this.httpClient.get<AdminCategory[]>(this.categoryUrl);
  }
}
