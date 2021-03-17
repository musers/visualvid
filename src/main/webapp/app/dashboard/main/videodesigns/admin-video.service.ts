import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { createRequestOption, Pagination } from 'app/shared/util/request-util';

import { TableDataModel } from 'app/shared/table/table-settings.model';
import { SERVER_API_URL } from 'app/app.constants';
import { AdminVideoModel } from './admin-video.model';
import { delay } from 'rxjs/operators';

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
  public getAll(tableDataModel: TableDataModel): Observable<any> {
    return this.httpClient.get<AdminVideoModel[]>(this.resourceUrl + '/page');
  }
  public getStats(): Observable<any>{
    const data = {
      totalVideosCount : 550,
      salesCount : 220,
      viewsCount : 100,
      earningsCount: 2400
    }
    return of(data).pipe(delay(1000));
  }
}

