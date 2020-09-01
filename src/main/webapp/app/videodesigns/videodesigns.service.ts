import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

import { VideoItem } from './videoitem.model';

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
  public getVideos(categoryId: string): Observable<Array<VideoItem>> {
    console.log('categoryId', categoryId);
    const data = [
      {
        posterUrl: 'posterUrl',
        videoUrl: 'videoUrl',
        title: 'Restaurant Menu',
        price: 2300,
        category: 'category',
      },
      {
        posterUrl: 'posterUrl',
        videoUrl: 'videoUrl',
        title: 'Fresh and Unique Look',
        price: 2000,
        category: 'category',
      },
    ];
    return of(data);
  }
}
