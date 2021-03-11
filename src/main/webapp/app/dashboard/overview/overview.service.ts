import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class OverviewService {
  public updateOverviewTemplateEvt = new EventEmitter<any>();
  public showOverviewEmitter = new EventEmitter<string>();
  updateOverviewTemplate(templateData: any): void {
    this.showOverviewEmitter.emit('true');
    setTimeout(() => {
      this.updateOverviewTemplateEvt.emit(templateData);
    });
  }
  closeOverview(): void {
    this.showOverviewEmitter.emit('false');
  }
}
