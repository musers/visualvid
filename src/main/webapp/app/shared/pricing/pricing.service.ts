import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { Pricing } from 'app/shared/pricing/pricing.model';

@Injectable({ providedIn: 'root' })
export class PricingService {
  public resourceUrl = SERVER_API_URL + 'api/pricing';
  constructor(protected httpClient: HttpClient) {}

  public computePricing(itemCustomization: any): Observable<Pricing> {
    return this.httpClient.post(this.resourceUrl + '/computeprice', itemCustomization);
  }
}
