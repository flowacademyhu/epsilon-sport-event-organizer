import { Component, OnInit } from '@angular/core';
import { AppStateService } from './shared/service/app-state.service';
import { ActivatedRoute } from '@angular/router';
import { UserControllerService } from './api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  tokenParam: string;

  constructor(
    private activateRoute: ActivatedRoute,
    private userService: UserControllerService,
    private appStateService: AppStateService
  ) {
  }

  ngOnInit() {
  this.activateRoute.queryParams.subscribe(params => {
    this.tokenParam = params['token'];
    if (this.tokenParam != null) {
      localStorage.setItem('token', params['token']);
      this.userService.getCurrentUserUsingGET().subscribe(
        user => {
        this.appStateService.user = user;
        }
      );
    }
  });
  }
}
