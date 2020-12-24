import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { createRequestOption, Pagination } from 'app/shared/util/request-util';

import { SERVER_API_URL } from 'app/app.constants';
import { OrderModel } from './order.model';
import { PaymentOrder } from 'app/shared/payment/paymentorder.model';

@Injectable({ providedIn: 'root' })
export class OrderService {
  public resourceUrl = SERVER_API_URL + '/api';

  constructor(protected httpClient: HttpClient) {}

  public findAll(): Observable<OrderModel[]> {
    return this.httpClient.get<OrderModel[]>(this.resourceUrl+'/orders');
  }

  public findAllByPage(req?: Pagination): Observable<HttpResponse<OrderModel[]>> {
    const options = createRequestOption(req);
    return this.httpClient.get<OrderModel[]>(this.resourceUrl + '/page', { params: options, observe: 'response' });
  }
  public createPaymentOrder(orderRequest: any) : Observable<PaymentOrder> {
    return this.httpClient.post(this.resourceUrl+'/order', orderRequest);
  }
  public updateRazorPayTransaction(razorPayResponse: any) : Observable<PaymentOrder> {
    console.log('updateRazorPayTransaction',razorPayResponse);
    return this.httpClient.post(this.resourceUrl+'/order/updaterazorpaytransaction', razorPayResponse);
  }
  public getForCustomerUpload(id: String): Observable<any> {
    return this.httpClient.get(this.resourceUrl+'/order/'+id+'/customerupload');
  }
  public saveCustomerUpload(order: any): Observable<any>{
      return this.httpClient.put(this.resourceUrl+'/order/'+order.id+'/customerupload', order);
  }
}
