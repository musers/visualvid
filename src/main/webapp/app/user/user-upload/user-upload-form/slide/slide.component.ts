import { Component, OnInit, Input } from '@angular/core';

import { Slide } from 'app/admin/admin-upload/admin-upload-form/slide/slide.model';
import { UserSlideItem } from './slideitem/user-slide-item.model';
@Component({
  selector: 'jhi-user-upload-form-slide',
  templateUrl: './slide.component.html',
  styleUrls: ['./slide.component.scss'],
})
export class SlideComponent implements OnInit {
  @Input() adminMediaSlide?: Slide ;
  @Input() userSlideItems?: Array<UserSlideItem>  = [];

  constructor() {
      if(!this.userSlideItems){
        this.userSlideItems = [];
      }
    }
  ngOnInit(): void {}
}
