import { Injectable } from '@angular/core';
import { WindowRefService } from 'app/shared/window-ref.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentsService {

RAZORPAY_OPTIONS = {
    "key": "rzp_test_wTzvK2HN5T7KjZ",
    "amount": "",
    "name": "Visualvid",
    "order_id": "",
    "description": "",
    "image": "https://livestatic.novopay.in/resources/img/nodeapp/img/Logo_NP.jpg",
    "prefill": {
      "name": "",
      "email": "test@test.com",
      "contact": "+918087930476",
      "method": ""
    },
    "modal": {},
    "theme": {
      "color": "#43497B"
    },
    "currency": "INR",
    "notes": {},
    "handler": {},
    "response": {}
  };

  constructor(private winRef: WindowRefService) { }

  payWithRazor(): void {
//   https://stackblitz.com/edit/razorpayintegration-zllagv?file=src%2Fapp%2Fapp.component.ts
// https://gist.github.com/sagrawal31/3471820b4f58024990cf888ae4c87377
// https://razorpay.com/docs/payment-gateway/quick-integration/
// https://razorpay.com/docs/payment-gateway/web-integration/standard/
    console.log('payWithRazor')
    this.RAZORPAY_OPTIONS.amount = 100 + '00';
    this.RAZORPAY_OPTIONS.currency = 'INR';
    this.RAZORPAY_OPTIONS.modal = {
            // We should prevent closing of the form when esc key is pressed.
            escape: false,
          }
    this.RAZORPAY_OPTIONS.notes = {
            // include notes if any
          }
    this.RAZORPAY_OPTIONS.handler = ((response: any) => {
      this.RAZORPAY_OPTIONS.response = response;
      console.log(response);
      console.log(this.RAZORPAY_OPTIONS);
      // call your backend api to verify payment signature & capture transaction
    });
//     this.RAZORPAY_OPTIONS.modal.ondismiss = (() => {
//       // handle the case when user closes the form while transaction is in progress
//       console.log('Transaction cancelled.');
//     });
    const rzp = new this.winRef.nativeWindow.Razorpay(this.RAZORPAY_OPTIONS);
    rzp.open()
  }
}
