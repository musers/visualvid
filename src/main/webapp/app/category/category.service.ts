/* eslint-disable no-unused-vars */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';

@Injectable({ providedIn: 'root' })
export class CategoryService {
 public resourceUrl = SERVER_API_URL + '/api/category/';
  constructor(private httpClient: HttpClient) { }

  public getCategories(): Observable<any> {
    return this.httpClient.get<any>(this.resourceUrl+'/list');
  }
  public createCategory(name: string): Observable<any>{
    return this.httpClient.post(this.resourceUrl, { name});
  }
  public createSubCategory(categoryId: string,name: string): Observable<any>{
    return this.httpClient.post(this.resourceUrl+'/'+categoryId+'/subcategory', { name,categoryId});
  }
  getCategoryTree() : Observable<any> {
   return this.httpClient.get(this.resourceUrl+'tree');
  }
  getAllSubCategories(): Observable<any>{
    return this.httpClient.get(this.resourceUrl+'subcategory/list');
  }
  public rename(id: string, type: string, name: string): Observable<any>{
    return this.httpClient.get(this.resourceUrl+'rename/'+type+'/'+id+'/'+name);
  }
  public deleteCategory(categoryId: string): Observable<any>{
    return this.httpClient.delete<any>(this.resourceUrl+'/'+categoryId);
  }
  public deleteSubCategory(subCategoryId: string): Observable<any>{
    return this.httpClient.delete<any>(this.resourceUrl+'subcategory/'+subCategoryId);
  }

}
