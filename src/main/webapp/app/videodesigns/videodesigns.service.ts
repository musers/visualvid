import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VideoDesignsService {
  constructor(protected http: HttpClient) {}
  public getCategories(): Observable<Array<any>> {
    const data = [
      {
        name: 'Family Invitations',
        id: 'familyInvitations',
      },
      {
        name: 'Logo invitations',
        id: 'logoinvitations',
      },
    ];
    return of(data);
  }
}
