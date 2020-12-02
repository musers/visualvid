import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { PaymentOrder } from 'app/shared/payment/paymentorder-model';

@Injectable({ providedIn: 'root' })
export class PaymentService {
  public resourceUrl = SERVER_API_URL + 'api/payment';
  constructor(protected httpClient: HttpClient) {}

  public createPaymentOrder(orderId: String): Observable<PaymentOrder> {
    return this.httpClient.get(this.resourceUrl+'/'+orderId+'?action=createpaymentorder');
  }
}
