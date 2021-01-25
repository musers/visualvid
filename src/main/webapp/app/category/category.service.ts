import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,of } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';

@Injectable({ providedIn: 'root' })
export class CategoryService {
 public categoryUrl = SERVER_API_URL + '/api/categories/';
  constructor(private httpClient: HttpClient) { }

  public getCategories(): Observable<any> {
    return this.httpClient.get<any>(this.categoryUrl);
  }

  getCategoryTree() : Observable<any>{
    const catTree = [
      {
        name: 'Wedding',
        type: 'category',
        children: [
          {
            name: 'Hindu',
            type: 'subCategory'
          },
          {
            name: 'Rajasthani',
            type: 'subCategory'
          },
          {
            name: 'Punjabi',
            type: 'subCategory'
          }
        ]
      },
     {
        name: 'Corporate',
        type: 'category',
        children: [
          {
            name: 'Annual',
            type: 'subCategory'
          },
          {
            name: 'Something',
            type: 'subCategory'
          }
        ]
      }
    ]
    return of(catTree);
  }
}
