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
<<<<<<< HEAD
=======
    private cupStateService: CupStateService
>>>>>>> 2cfb29e8d13269c9b2dde30f2fba3e20747493c9
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

<<<<<<< HEAD
  this.teamStateService.getTeams();

  /* this.teamService.getAllTeamsUsingGET().subscribe(
=======
  this.cupStateService.getCups();

  this.teamService.getAllTeamsUsingGET().subscribe(
>>>>>>> 2cfb29e8d13269c9b2dde30f2fba3e20747493c9
    teams => {
      this.teamStateService.teams.push(...teams);
    }
  ); */
  }
}
