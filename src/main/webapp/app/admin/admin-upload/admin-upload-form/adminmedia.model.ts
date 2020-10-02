import { Slide } from './slide/slide.model';

export interface AdminMedia {
  id?: string;
  name?: string;
  description?: string;
  categoryId?: string;
  indianPrice?: any;
  usdPrice?: any;
  previewVideoS3Url?: string;
  previewVideoS3Key?: string;
  thumbNailS3Url?: string;
  thumbNailS3Key?: string;
  mediaPlaceholder?: string;
  textPlaceholder?: string;
  turnAroundTime?: string;
  slides: Array<Slide>;
}
