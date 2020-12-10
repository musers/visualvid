import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { createRequestOption, Pagination } from '../../../../../app/shared/util/request-util';

import { SERVER_API_URL } from '../../../../../app/app.constants';
import { AdminOrderModel } from './admin-order.model';

@Injectable({ providedIn: 'root' })
export class AdminOrderService {
  public resourceUrl = SERVER_API_URL + '/api/admin/project-upload';

  constructor(protected httpClient: HttpClient) {}

  public findAll(): Observable<AdminOrderModel[]> {
    return this.httpClient.get<AdminOrderModel[]>(this.resourceUrl);
  }

  public findAllByPage(req?: Pagination): Observable<HttpResponse<AdminOrderModel[]>> {
    const options = createRequestOption(req);
    return this.httpClient.get<AdminOrderModel[]>(this.resourceUrl + '/page', { params: options, observe: 'response' });
  }
}
