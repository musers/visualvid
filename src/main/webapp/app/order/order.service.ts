import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { createRequestOption, Pagination } from 'app/shared/util/request-util';

import { SERVER_API_URL } from 'app/app.constants';
import { OrderModel } from './order.model';

@Injectable({ providedIn: 'root' })
export class OrderService {
  public resourceUrl = SERVER_API_URL + '/api/admin/project-upload';

  constructor(protected httpClient: HttpClient) {}

  public findAll(): Observable<OrderModel[]> {
    return this.httpClient.get<OrderModel[]>(this.resourceUrl);
  }

  public findAllByPage(req?: Pagination): Observable<HttpResponse<OrderModel[]>> {
    const options = createRequestOption(req);
    return this.httpClient.get<OrderModel[]>(this.resourceUrl + '/page', { params: options, observe: 'response' });
  }
}
