import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OverviewService {
  public updateOverviewTemplateEvt = new EventEmitter<any>();
  updateOverviewTemplate(templateData : any) : void {
    this.updateOverviewTemplateEvt.emit(templateData);
  }
}
