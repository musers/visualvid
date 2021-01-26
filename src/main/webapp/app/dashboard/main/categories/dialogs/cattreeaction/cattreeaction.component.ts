import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';

export interface CatTreeActionConfig {
    name?: string,
    newName?: string,
    type?: string,
    id?: string,
    actionId?: string
}

@Component({
  selector: 'jhi-cattree-action-popup',
  templateUrl: './cattreeaction.component.html'
})
export class CatTreeActionComponent {
  get dialog(): CatTreeActionConfig {
      return this.data;
  }

  constructor(
      @Inject(MAT_DIALOG_DATA) public data: CatTreeActionConfig,
      public dialogRef: MatDialogRef<CatTreeActionComponent>
  ) {}

}
