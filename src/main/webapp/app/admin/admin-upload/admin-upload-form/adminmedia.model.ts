import { Slide } from './slide/slide.model';

export interface AdminMedia {
  id?: string;
  name?: string;
  description?: string;
  categoryId?: string;
  subCategoryId?: string;
  categoryName?: string;
  subCategoryName?: string;
  indianPrice?: any;
  indianDiscPrice?: any;
  indianAdvCustomizationPrice?: any;
  indianPremumDeliveryPrice?: any;
  usdDiscPrice?: any;
  usdAdvCustomizationPrice?: any;
  usdPremumDeliveryPrice?: any;
  usdPrice?: any;
  previewVideoS3Url?: string;
  previewVideoS3Key?: string;
  thumbNailS3Url?: string;
  thumbNailS3Key?: string;
  mediaPlaceholder?: string;
  textPlaceholder?: string;
  turnAroundTime?: string;
  slides: Array<Slide>;
  tagList?: Array<string>;
  tags?: string;
  divId?: string;
}
