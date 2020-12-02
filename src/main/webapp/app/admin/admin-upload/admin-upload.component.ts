import { Component, Inject, OnDestroy, OnInit, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { AdminMediaService } from './admin-media.service';
import { AdminMedia } from './admin-upload-form/adminmedia.model';

@Component({
  selector: 'jhi-admin-upload',
  templateUrl: './admin-upload.component.html',
  styleUrls: ['./admin-upload.component.scss'],
})
export class AdminUploadComponent implements OnInit, OnDestroy {
  item: AdminMedia = {
      slides: [],
    };
   readMode = true;
  constructor(@Inject(DOCUMENT) private document: Document,
  private renderer: Renderer2,
  private route: ActivatedRoute,
  private adminMediaService: AdminMediaService
  ) {}

  ngOnInit(): void {
    this.renderer.addClass(this.document.body, 'admin-upload-active');

     const adminMediaId = this.route.snapshot.paramMap.get('adminMediaId');
     if(adminMediaId && adminMediaId!=='new'){
         this.adminMediaService.get(adminMediaId).subscribe((res: AdminMedia) => {
          this.readMode = false;
           if (res) {
             this.item = res;
             console.log('data loaded', this.item);
           }
         });
       }
  }

  ngOnDestroy(): void {
    this.renderer.removeClass(this.document.body, 'admin-upload-active');
  }
}
