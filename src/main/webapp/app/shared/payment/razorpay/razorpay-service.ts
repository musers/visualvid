import { Injectable, EventEmitter } from '@angular/core';
import { WindowRefService } from 'app/shared/window-ref.service';

import { RazorpayOption } from './razorpay.model';
import { OrderService } from 'app/order/order.service.ts';

@Injectable({
  providedIn: 'root'
})
export class RazorpayService {
  public onPaymentSuccess: EventEmitter<any> = new EventEmitter<any>();
  constructor(private winRef: WindowRefService, private orderService: OrderService) { }

  payWithRazor(options: RazorpayOption): void {
    console.log('payWithRazor')
    options.handler = ((response: any) => {
      const razorpayResponse = {
        razorpayPaymentId: response['razorpay_payment_id'],
        razorpayOrderId: response['razorpay_order_id'],
        razorpaySignature: response['razorpay_signature']
      };
        console.log(response);
        console.log(options);
        this.orderService.updateRazorPayTransaction(razorpayResponse).subscribe( resp => {
          console.log('payment Succeeded', resp);
          this.onPaymentSuccess.emit(resp);
        });
    });
    const rzp = new this.winRef.nativeWindow.Razorpay(options);
    rzp.open()
  }
}
