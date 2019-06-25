import { Component, OnInit } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { User, TeamControllerService } from 'src/app/api';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private state: AppStateService,
              private teamService: TeamControllerService) { }

  userDatas: User;

  teamsIAmLeaderIn: any[];
  teamsIAmMemberIn: any[];

  

  ngOnInit() {

    this.userDatas = this.state.user;

    this.teamService.getAllTeamsByLeaderUsingGET().subscribe(
      teams => {
        this.teamsIAmLeaderIn = teams;
        console.log(this.teamsIAmLeaderIn);
      }
    );

    this.teamService.getAllTeamsByMemberUsingGET().subscribe(
      teams => {
        this.teamsIAmMemberIn = teams;
        console.log(this.teamsIAmMemberIn);
      }
    );
  }

}
