import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { SlideItem } from './slideitem.model';
@Component({
  selector: 'jhi-admin-upload-form-slide-item',
  templateUrl: './slideitem.component.html',
  styleUrls: ['./slideitem.component.scss'],
})
export class SlideItemComponent implements OnInit {
  @Input() item?: SlideItem;
  @Input() index?: number;
  @Input() isLast = true;
  @Output() addSlideItemHandler = new EventEmitter();

  constructor() {}
  ngOnInit(): void {}
  addSlide(type: String): void {
    this.addSlideItemHandler.emit(type);
  }
}
