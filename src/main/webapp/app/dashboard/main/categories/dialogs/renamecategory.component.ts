import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogRef } from '@angular/material/dialog';

export interface DialogConfig {
    title?: string,
    categoryName?: string,
    content?: string
}

@Component({
  template: `
  <h1 mat-dialog-title>Hi {{data.name}}</h1>
  <div mat-dialog-content>
    <p>{{data.content}}</p>
    <mat-form-field>
      <input matInput [(ngModel)]="data.categoryName">
    </mat-form-field>
  </div>
  <div mat-dialog-actions>
    <button mat-button (click)="dialogRef.close(false)">Discard</button>
    <button mat-button [mat-dialog-close]="data.categoryName" cdkFocusInitial>Ok</button>
  </div>
  `
})
export class RenameCategoryComponent {

  get dialog(): DialogConfig {
      return this.data;
  }

  constructor(
      @Inject(MAT_DIALOG_DATA) public data: DialogConfig,
      public dialogRef: MatDialogRef<RenameCategoryComponent>
  ) {}

}
