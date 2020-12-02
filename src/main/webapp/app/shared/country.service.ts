import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  COUNTRY_CODES = {
    INDIA : 'IN',
    US : 'US'
    }
  constructor() { }

  isIpIndian(): boolean {
    console.log('isIpIndian');
    return this.getCountryByIp() === this.COUNTRY_CODES.INDIA;
  }
  getCountryByIp(): string {
    let countryCode = this.COUNTRY_CODES.INDIA;
     const countryObjString = localStorage.getItem('countryObj')
     if(countryObjString){
        try {
          const countryObj = JSON.parse(countryObjString);
          countryCode = countryObj.country_code;
         } catch(e) {
            countryCode = this.COUNTRY_CODES.INDIA;
         }
     }
     return countryCode;
  }
}
