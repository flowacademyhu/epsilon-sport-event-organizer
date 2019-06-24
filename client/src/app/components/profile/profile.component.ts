import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { User, TeamControllerService, CupControllerService } from 'src/app/api';
import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private state: AppStateService,
              private teamService: TeamControllerService,
              private cupService: CupControllerService) { }

  userDatas: User;

  teamsIAmLeaderIn: any[];
  teamsIAmMemberIn: any[];
  cupsIAmOrganizerIn: any[];
  cupsIAmParticipatedIn: any[];
  listCupData: MatTableDataSource<any>;
  displayedCupColumns: string[] = ['name',  'company', 'endDateTime', 'place', 'registrationEndTime', 'startDateTime'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

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

   //here comes cupService
  }

}
