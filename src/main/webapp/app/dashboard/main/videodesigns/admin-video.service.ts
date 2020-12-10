import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { createRequestOption, Pagination } from 'app/shared/util/request-util';

import { SERVER_API_URL } from 'app/app.constants';
import { AdminVideoModel } from './admin-video.model';

@Injectable({ providedIn: 'root' })
export class AdminVideoService {
  public resourceUrl = SERVER_API_URL + '/api/admin/project-upload';

  constructor(protected httpClient: HttpClient) {}

  public findAll(): Observable<AdminVideoModel[]> {
    return this.httpClient.get<AdminVideoModel[]>(this.resourceUrl);
  }

  public findAllByPage(req?: Pagination): Observable<HttpResponse<AdminVideoModel[]>> {
    const options = createRequestOption(req);
    return this.httpClient.get<AdminVideoModel[]>(this.resourceUrl + '/page', { params: options, observe: 'response' });
  }
}
