import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TableService {
    public fetchLatest(sort = '', order = '', page = 1, perPage = 5): Observable<any> {
      console.log('TableService')
        const data = [{
          id : '001'
        },{
          id : '002'
        }, {
          id : '003'
        }];
        return of(data);
    }
}
