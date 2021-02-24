import { Injectable, Input } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../../../app.constants';
import { SubscriptionAddModel } from './add-subscription/add-subscription.model';

@Injectable({ providedIn: 'root' })
export class SubscriptionService {
  public resourceUrl = SERVER_API_URL + '/api/subscription/';

  subscriptionAddModel?: SubscriptionAddModel;

  constructor(protected httpClient: HttpClient) {}

  public save(subscription: SubscriptionAddModel): Observable<any> {
    console.log('subscription data from popup: ', subscription);
    if (subscription.id) {
      console.log('subscription data from popup: ', subscription);
      return this.httpClient.put(this.resourceUrl, subscription);
    } else {
      console.log('subscription data from popup: ', subscription);
      return this.httpClient.post(this.resourceUrl, subscription);
    }
  }
}
