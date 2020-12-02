import { Injectable } from '@angular/core';
import { WindowRefService } from 'app/shared/window-ref.service';

import { RazorpayOption } from './razorpay.model';

@Injectable({
  providedIn: 'root'
})
export class RazorpayService {

  constructor(private winRef: WindowRefService) { }

  payWithRazor(options: RazorpayOption): void {
    console.log('payWithRazor')
    options.handler = ((response: any) => {
//       options.response = response;
      console.log(response);
      console.log(options);
    });
    const rzp = new this.winRef.nativeWindow.Razorpay(options);
    rzp.open()
  }
}
