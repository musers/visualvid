import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { TableDataModel } from 'app/shared/table/table-settings.model';
// import { SERVER_API_URL } from 'app/app.constants';
import { delay } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AssignmentsService {

  constructor(protected httpClient: HttpClient) {}

  public getAll(tableDataModel: TableDataModel): Observable<any> {
//     return this.httpClient.get<AdminVideoModel[]>(this.resourceUrl + '/page');
    const data = [{
        saleId: '0001',
        orderId: '0001',
        name: 'RestaurantPromoVideo',
        assignTo: 'Bobba Anil',
        status: 'Assigned',
        timeLeft: 120,
        action: 'select'
      }]
      return of(data).pipe(delay(200));
  }
  public getStats(): Observable<any>{
    const data = {
      totalCount : 550,
      completedCount : 220,
      inProgressCount : 100,
      notCompletedCount: 2400
    }
    return of(data).pipe(delay(200));
  }
}

