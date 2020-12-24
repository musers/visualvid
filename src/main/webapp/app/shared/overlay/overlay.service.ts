import { Injectable } from '@angular/core';
import { Overlay, OverlayConfig, OverlayRef } from '@angular/cdk/overlay';
import { TemplatePortalDirective } from '@angular/cdk/portal';

@Injectable({ providedIn: 'root' })
export class OverlayService {
  overlayRef: OverlayRef;
  constructor(private overlay: Overlay) { }

  openTemplateOverlay(overlayTemplate: TemplatePortalDirective): void {
    const positionStrategy = this.overlay
      .position()
      .global()
      .centerHorizontally()
      .centerVertically();
    const overlayConfig = new OverlayConfig({
      positionStrategy,
    });
    overlayConfig.hasBackdrop = true;
    this.overlayRef = this.overlay.create(overlayConfig);
//     this.overlayRef.backdropClick().subscribe(() => {
//       this.overlayRef.dispose();
//     });
    this.overlayRef.attach(overlayTemplate);
  }
  closeOverlay() : void {
    this.overlayRef.dispose();
  }
}
