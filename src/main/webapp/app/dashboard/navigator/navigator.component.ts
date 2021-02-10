import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-dashboard-navigator',
  templateUrl: './navigator.component.html',
  styleUrls: ['navigator.component.scss'],
})
export class DashboardNavigatorComponent implements OnInit {
  moreView = false;
  constructor(){}
  ngOnInit(): void {
    if(window.location.pathname==='/dashboard/categories'){
      this.moreView = true;
    }
  }

  toggleMore(): void {
    this.moreView = !this.moreView;
  }
}
