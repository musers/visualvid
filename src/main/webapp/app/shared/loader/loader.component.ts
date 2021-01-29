import { Component } from '@angular/core';

// import { AnimationItem } from 'lottie-web';
import { AnimationOptions } from 'ngx-lottie';
@Component({
  selector: 'jhi-loader',
  template: `
    <ng-lottie [options]="options" (animationCreated)="animationCreated($event)"></ng-lottie>
  `,
})
export class LoaderComponent {
    options: AnimationOptions = {
         path: 'content/loader.json'
      };

      animationCreated(animationItem: any): void {
        console.log(animationItem);
      }
}
