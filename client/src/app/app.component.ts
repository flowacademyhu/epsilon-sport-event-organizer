import { Component, OnInit } from '@angular/core';
import { AppStateService } from './shared/service/app-state.service';
import { ActivatedRoute } from '@angular/router';
import { UserControllerService, TeamControllerService } from './api';
import { TeamStateService } from './shared/service/team-state.service';

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
    private teamService: TeamControllerService,
    private appStateService: AppStateService,
    private teamStateService: TeamStateService
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
        localStorage.setItem('user', JSON.stringify(user));
        }
      );
    }
  });

  this.teamService.getAllTeamsUsingGET().subscribe(
    teams => {
      this.teamStateService.teams.push(...teams);
    }
  );

  }
}
