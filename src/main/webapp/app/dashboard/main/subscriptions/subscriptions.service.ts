import { Injectable, Input } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
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

  public getAllSubscriptionPlans(): Observable<any> {
    const data = [
      {
        id: '0001',
        name: 'Basic',
        price: '550$|7500$',
        orderCount: '100/Month',
        revisions: 'Revision 1',
        status: 'Active',
        action: 'active',
      },
      {
        id: '0002',
        name: 'Professional',
        price: '750$|8500$',
        orderCount: '100/Month',
        revisions: 'Revision 2',
        status: 'Active',
        action: 'inactive',
      },
      {
        id: '0003',
        name: 'Life Time',
        price: '950$|10500$',
        orderCount: 'Unlimited',
        revisions: 'Revision 2',
        status: 'Active',
        action: 'cancelled',
      },
    ];
    console.log('subscriptions data: ', data);
    return of(data);
  }
}
