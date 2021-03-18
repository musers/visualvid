import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class OverviewService {
  public updateOverviewTemplateEvt = new EventEmitter<any>();
  public showOverviewEmitter = new EventEmitter<string>();
//   updateOverviewTemplate(templateData: any): void {
//     this.showOverviewEmitter.emit('true');
//     setTimeout(() => {
//       this.updateOverviewTemplateEvt.emit(templateData);
//     });
//   }
  updateOverviewTemplate(overviewTemplate: any, selectedRows: object[]): void {
    if (selectedRows && selectedRows.length > 0) {
      this.showOverviewEmitter.emit('true');
      setTimeout(() => {
        this.updateOverviewTemplateEvt.emit({
          template: overviewTemplate,
          data: selectedRows[0],
        });
      });
    } else {
      this.closeOverview();
    }
  }
  closeOverview(): void {
    this.showOverviewEmitter.emit('false');
  }
}
