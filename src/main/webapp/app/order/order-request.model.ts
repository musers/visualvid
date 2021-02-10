import { ItemCustomization } from 'app/order/item-customization.model';

export interface OrderRequest {
    couponCode?: string;
    currencyCode?: string;
    itemCustomizations: Array<ItemCustomization>
}
