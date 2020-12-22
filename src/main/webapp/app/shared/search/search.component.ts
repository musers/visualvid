import { Component, Output, EventEmitter, ViewChild, ElementRef, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'jhi-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class SearchComponent {
  @ViewChild('searchInput', { read: ElementRef })
  private searchInput: ElementRef;

  @Output()
  searchEvent = new EventEmitter<{ query?: string }>();

  constructor() {}
  search(): void {
    const searchTxt = this.searchInput.nativeElement.value;
    this.searchEvent.emit({ query: searchTxt });
  }
}
