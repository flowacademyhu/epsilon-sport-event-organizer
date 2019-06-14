import { Component, OnInit } from '@angular/core';
import { AppStateService } from './shared/service/app-state.service';
import { AuthService } from './shared/service/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  tokenParam: String;

  constructor(
    private activateRoute: ActivatedRoute,
    private authService: AuthService,
    private appStateService: AppStateService
  ) {
  }

  ngOnInit() {
  this.activateRoute.queryParams.subscribe(params => {
    this.tokenParam = params['token'];
    if (this.tokenParam != null) {
    localStorage.setItem('token', params['token']);
    this.authService.getLoggedInUser().subscribe(
      user => {
       this.appStateService.user = user;
      }
    );
    }
  });
}
}
