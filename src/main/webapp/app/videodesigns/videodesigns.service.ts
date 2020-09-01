import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { of } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VideoDesignsService {
  constructor(protected http: HttpClient) {}
}
