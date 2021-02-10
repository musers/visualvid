import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable} from 'rxjs';

import { SERVER_API_URL } from '../../app/app.constants';
import { AdminMediaService } from '../../app/admin/admin-upload/admin-media.service';

@Injectable({ providedIn: 'root' })
export class ItemService {
  public categoryUrl = SERVER_API_URL + '/api/categories/';
  constructor(protected httpClient: HttpClient, protected adminMediaService: AdminMediaService) {}

  public get(adminMediaId: String): Observable<any> {
    return this.adminMediaService.get(adminMediaId);
  }
}
