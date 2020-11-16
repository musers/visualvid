import { UserSlideItem } from './slideitem/userSlideItem.model';
export interface UserSlide {
  id?: string;
  adminId?: string;
  userSlideItems?: Array<UserSlideItem>;
}
