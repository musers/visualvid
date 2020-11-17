import { SlideItem } from './slideitem/slideitem.model';

export interface Slide {
  id?: string;
  slideOrder?: number;
  slideName?: string;
  screenShotS3Url?: string;
  screenShotS3Key?: string;
  slideItems: Array<SlideItem>;
}
