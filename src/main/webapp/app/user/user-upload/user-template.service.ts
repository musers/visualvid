import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { UserTemplate } from 'app/user/user-upload/user-upload-form/user-template.model';

@Injectable({ providedIn: 'root' })
export class UserTemplateService {
  public resourceUrl = SERVER_API_URL + '/api/template';
  constructor(protected httpClient: HttpClient) {}

  public save(userTemplate: UserTemplate): Observable<any> {
    return this.httpClient.post(this.resourceUrl, userTemplate);
  }
}
