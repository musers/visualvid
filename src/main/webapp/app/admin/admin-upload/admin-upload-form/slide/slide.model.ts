import { SlideItem } from './slideitem/slideitem.model';

export interface Slide {
  id?: string;
  order?: Number;
  name?: string;
  previewImageUrl?: string;
  previewImageKey?: string;
  previewImageName?: string;
  slideItems: Array<SlideItem>;
}
