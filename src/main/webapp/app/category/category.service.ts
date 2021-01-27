/* eslint-disable no-unused-vars */
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
  public rename(id: string, type: string, name: string): Observable<any>{
    const resp = {
      success : true
    }
     return of(resp);
  }
  public createCategory(name: string): Observable<any>{
    const resp = {
      success : true,
      id: btoa(Math.random().toString()).substr(0, 20)
    }
     return of(resp);
  }
  public createSubCategory(categoryId: string,name: string): Observable<any>{
    const resp = {
      success : true,
      id: btoa(Math.random().toString()).substr(0, 20)
    }
     return of(resp);
  }
  public deleteCategory(categoryId: string): Observable<any>{
    const resp = {
      success : true
    }
    return of(resp);
  }
  public deleteSubCategory(subCategoryId: string): Observable<any>{
    const resp = {
      success : true
    }
    return of(resp);
  }
  getAllSubCategories(): Observable<any>{
    const subCategories = [{
      name: 'Hindu',
      id: btoa(Math.random().toString()).substr(0, 20),
      categoryId: btoa(Math.random().toString()).substr(0, 20),
      categoryName: 'Wedding'
      },{
      name: 'Rajasthani',
      id: btoa(Math.random().toString()).substr(0, 20),
      categoryId: btoa(Math.random().toString()).substr(0, 20),
      categoryName: 'Wedding'
      },{
      name: 'Something',
      id: btoa(Math.random().toString()).substr(0, 20),
      categoryId: btoa(Math.random().toString()).substr(0, 20),
      categoryName: 'Corporate'
      }
    ];
    return of(subCategories);
  }
  getCategoryTree() : Observable<any>{
    const catTree = [
      {
        name: 'Wedding',
        type: 'category',
        id: btoa(Math.random().toString()).substr(0, 20),
        children: [
          {
            name: 'Hindu',
            type: 'subCategory',
            id: btoa(Math.random().toString()).substr(0, 20)
          },
          {
            name: 'Rajasthani',
            type: 'subCategory',
            id: btoa(Math.random().toString()).substr(0, 20)
          },
          {
            name: 'Punjabi',
            type: 'subCategory',
            id: btoa(Math.random().toString()).substr(0, 20)
          }
        ]
      },
     {
        name: 'Corporate',
        type: 'category',
        id: btoa(Math.random().toString()).substr(0, 20),
        children: [
          {
            name: 'Annual',
            type: 'subCategory',
            id: btoa(Math.random().toString()).substr(0, 20)
          },
          {
            name: 'Something',
            type: 'subCategory',
            id: btoa(Math.random().toString()).substr(0, 20)
          }
        ]
      }
    ]
    return of(catTree);
  }
}
