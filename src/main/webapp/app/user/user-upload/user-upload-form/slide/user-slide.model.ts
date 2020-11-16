import { UserSlideItem } from './slideitem/user-slide-item.model';
export interface UserSlide {
  id?: string;
  adminId?: string;
  userSlideItems?: Array<UserSlideItem>;
}
