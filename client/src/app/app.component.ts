import { Component, OnInit } from '@angular/core';
import { AppStateService } from './shared/service/app-state.service';
import { ActivatedRoute } from '@angular/router';
import { TeamStateService } from './shared/service/team-state.service';
import { UserResourceService, TeamResourceService } from './api';
import { CupStateService } from './shared/service/cup-state.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  tokenParam: string;

  constructor(
    private activateRoute: ActivatedRoute,
    private userService: UserResourceService,
    private appStateService: AppStateService,
    private teamService: TeamResourceService,
    private teamStateService: TeamStateService,
    private cupStateService: CupStateService
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

  this.teamStateService.getTeams();
  this.cupStateService.getCups();

  }
}
