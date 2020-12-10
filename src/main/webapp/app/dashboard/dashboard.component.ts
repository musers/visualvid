import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.scss'],
})
export class DashboardComponent implements OnInit {
  showOverview = true;
  constructor() {}
  ngOnInit(): void {
  }
  toggleOverview(evt: any) : void {
    console.log('toggle',evt);
    this.showOverview = !this.showOverview;
  }
}
