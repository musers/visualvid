import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

import { VideoItem } from './videoitem.model';
import { Category } from './category.model';

@Injectable({ providedIn: 'root' })
export class VideoDesignsService {
  constructor(protected http: HttpClient) {}
  public getCategories(): Observable<Array<Category>> {
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
      {
        index: '3',
        name: 'Corporate',
        id: 'corporate',
      },
      {
        index: '4',
        name: 'Restaurant Displays',
        id: 'restaurantDisplays',
      },
      {
        index: '5',
        name: 'Partyevent Invitations',
        id: 'partyEventInvitations',
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
          posterUrl:
            'https://lh3.googleusercontent.com/proxy/WTMn_2hZeGV7HQEk1BV-t9yiUPHOI2iryrjKxyxzpjo9qYo8r5JlwSnfc2rt1U7YZLK8qinHfXNnKiex51G5iQ_LG8vPcavbRRzUx1qhkDqz2g',
          videoUrl: 'https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/360/Big_Buck_Bunny_360_10s_1MB.mp4',
          title: 'Restaurant Menu',
          price: 2300,
          category: 'category',
        },
        {
          id: 'myvideo-2',
          posterUrl:
            'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c0/Big_Buck_Bunny_4K.webm/310px-seek%3D116-Big_Buck_Bunny_4K.webm.jpg',
          videoUrl: 'https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4',
          title: 'Fresh and Unique Look',
          price: 2000,
          category: 'category',
        },
        {
          id: 'myvideo-3',
          posterUrl: 'https://images.pexels.com/photos/3862372/pexels-photo-3862372.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
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
