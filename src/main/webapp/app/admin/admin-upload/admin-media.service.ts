import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AdminMedia } from './admin-upload-form/adminmedia.model';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable({ providedIn: 'root' })
export class AdminMediaService {
  public resourceUrl = SERVER_API_URL + '/api/admin/project-upload';
  constructor(protected httpClient: HttpClient) {}

  public save(adminMedia: AdminMedia): Observable<any> {
    return this.httpClient.post(this.resourceUrl, adminMedia);
  }
}
