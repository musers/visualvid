import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

import { VideoItem } from './videoitem.model';

@Injectable({ providedIn: 'root' })
export class VideoDesignsService {
  constructor(protected http: HttpClient) {}

  public getVideos(id: string): Observable<Array<VideoItem>> {
    console.log('id', id);
    let data;
    if (id === 'familyInvitations') {
      data = [
        {
          id: 'myvideo-1',
          posterUrl: 'https://images.pexels.com/photos/3862372/pexels-photo-3862372.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
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
