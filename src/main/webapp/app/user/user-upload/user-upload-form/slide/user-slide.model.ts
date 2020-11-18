import { UserSlideItem } from './slideitem/user-slide-item.model';
export interface UserSlide {
  adminSlideId?: string;
  id?: string;
  screenShotS3Key?: string;
  screenShotS3Url?: string;
  slideName?: string;
  slideOrder?: number;
  userSlideItems?: Array<UserSlideItem>;
}
