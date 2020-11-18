import { UserSlide } from './slide/user-slide.model';

export interface UserTemplate {
  id?: string;
  adminMediaId?: string;
  userSlides: Array<UserSlide>;
}
