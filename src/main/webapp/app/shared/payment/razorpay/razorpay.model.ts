/* eslint-disable @typescript-eslint/camelcase */
export interface RazorpayOption {
    name: string;
    description?: string;
    key: string;
    order_id?: string;
    amount: number;
    prefill: RazorpayPrefill;
    modal?: any;
    notes?: any;
    theme?: any;
    external?: RazorpayExternalOption;
    handler?: (response: any) => void;
}

export interface RazorpayExternalOption {
    wallets: Array<string>;
    handler: (response: any) => void;
}

export interface RazorpayPrefill {
    email?: string;
    contact?: string;
    name?: string;
//     method?: 'card' | 'netbanking' | 'wallet' | 'emi' | 'upi';
}

export interface RazorpayResponse {
    razorpay_payment_id: string;
    razorpay_order_id: string;
    razorpay_signature: string;
}

export interface RazorpayError {
    code: number;
    description: string;
}
