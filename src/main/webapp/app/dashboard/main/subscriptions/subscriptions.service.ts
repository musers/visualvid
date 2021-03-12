import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { SERVER_API_URL } from '../../../app.constants';
import { SubscriptionAddModel } from './add-subscription/add-subscription.model';
import { TableDataModel } from 'app/shared/table/table-settings.model';
import { delay } from 'rxjs/operators';

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

  public getAllSubscriptionPlans(tableDataModel: TableDataModel): Observable<any> {
    console.log('tableDataModel', tableDataModel);
    const data = [
      {
        id: Math.random().toString(),
        name: 'Basic',
        price: '550$|7500$',
        orderCount: '100/Month',
        revisions: 'Revision 1',
        status: 'Active',
        action: 'active',
      },
      {
        id: Math.random().toString(),
        name: 'Professional',
        price: '750$|8500$',
        orderCount: '100/Month',
        revisions: 'Revision 2',
        status: 'Active',
        action: 'inactive',
      },
    ];
    console.log('subscriptions data: ', data);
    return of(data).pipe(delay(1000));
  }

  public getAllUserSubscriptions(tableDataModel: TableDataModel, status: string): Observable<any> {
    console.log('status', status);
    const data = [
      {
        userId: '0001',
        planName: 'Basic',
        userName: 'Anil Kumar',
        startDate: '19 Nov,2020',
        endDate: '19 Nov,2020',
        ordersLeft: '5/10',
        // status: this.status,
        action: '',
      },
      {
        userId: '0002',
        planName: 'Basic',
        userName: 'Anil Kumar',
        startDate: '19 Nov,2020',
        endDate: '19 Nov,2020',
        ordersLeft: '5/10',
        // status: this.status,
        action: '',
      },
      {
        userId: '0003',
        planName: 'Basic',
        userName: 'Anil Kumar',
        startDate: '19 Nov,2020',
        endDate: '19 Nov,2020',
        ordersLeft: '5/10',
        //  status: this.status,
        action: '',
      },
    ];

    console.log('User Subscriptions Data: ', data);
    return of(data).pipe(delay(1000));
  }
}
