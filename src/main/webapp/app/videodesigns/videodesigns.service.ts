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
        index: '1',
        name: 'Family Invitations',
        id: 'familyInvitations',
      },
      {
        index: '2',
        name: 'Logo Reveals',
        id: 'logoReveals',
      },
    ];
    return of(data);
  }
  public getVideos(categoryId: string): Observable<Array<VideoItem>> {
    console.log('categoryId', categoryId);
    let data;
    if (categoryId === 'familyInvitations') {
      data = [
        {
          id: 'myvideo-1',
          posterUrl: 'https://test-videos.co.uk/user/pages/images/big_buck_bunny.jpg',
          videoUrl: 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4',
          title: 'Restaurant Menu',
          price: 2300,
          category: 'category',
        },
        {
          id: 'myvideo-2',
          posterUrl: 'https://test-videos.co.uk/user/pages/images/jellyfish.jpg',
          videoUrl: 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4',
          title: 'Fresh and Unique Look',
          price: 2000,
          category: 'category',
        },
        {
          id: 'myvideo-3',
          posterUrl: 'https://test-videos.co.uk/user/pages/images/big_buck_bunny.jpg',
          videoUrl: 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4',
          title: 'Corporate Presentation',
          price: 1600,
          category: 'category',
        },
        {
          id: 'myvideo-4',
          posterUrl: 'https://test-videos.co.uk/user/pages/images/jellyfish.jpg',
          videoUrl: 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4',
          title: 'Envato',
          price: 1200,
          category: 'category',
        },
      ];
    } else {
      data = [
        {
          id: 'myvideo-5',
          posterUrl: 'https://test-videos.co.uk/user/pages/images/big_buck_bunny.jpg',
          videoUrl: 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4',
          title: 'Format Menu',
          price: 2300,
          category: 'category',
        },
        {
          id: 'myvideo-6',
          posterUrl: 'https://test-videos.co.uk/user/pages/images/jellyfish.jpg',
          videoUrl: 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4',
          title: 'Progress Look',
          price: 2000,
          category: 'category',
        },
      ];
    }
    return of(data);
  }
}
