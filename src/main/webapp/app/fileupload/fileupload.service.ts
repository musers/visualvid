import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { S3Info } from './s3info.model';

@Injectable({ providedIn: 'root' })
export class FileUploadService {
  public resourceUrl = SERVER_API_URL + 'api/adminupload';
  constructor(protected httpClient: HttpClient) {}

  public uploadFile(file: any): Observable<S3Info> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.httpClient.post(this.resourceUrl, formData);
  }
}
