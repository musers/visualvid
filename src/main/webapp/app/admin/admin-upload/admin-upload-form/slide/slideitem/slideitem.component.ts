import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from '@angular/core';

import { SlideItem } from './slideitem.model';
@Component({
  selector: 'jhi-admin-upload-form-slide-item',
  templateUrl: './slideitem.component.html',
  styleUrls: ['./slideitem.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class SlideItemComponent implements OnInit {
  @Input() item?: SlideItem;
  @Input() index?: number;
  @Input() isLast = true;
  @Output() addSlideItemHandler = new EventEmitter();
  @Output() removeSlideItemHandler = new EventEmitter();

  constructor() {}
  ngOnInit(): void {}
  addSlide(type: String): void {
    this.addSlideItemHandler.emit(type);
  }
  removeSlideItem(): void {
    this.removeSlideItemHandler.emit(this.index);
  }
}
