import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog, MatDialogConfig, } from '@angular/material';
import { User, TeamResourceService, CupResourceService } from 'src/app/api';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private state: AppStateService,
              private teamService: TeamResourceService,
              private cupService: CupResourceService) { }

  userDatas: User;

  teamsIAmLeaderIn: MatTableDataSource<any>;
  teamsIAmLeaderInSize: number;
  teamsIAmMemberIn: MatTableDataSource<any>;
  teamsIAmMemberInSize: number;
  teamsDefinition: string[] = ['name', 'company'];
  listCupData: MatTableDataSource<any>;
  listCupDataSize: number;
  listCupDataMembership: MatTableDataSource<any>;
  listCupDataMembershipSize: number;
  displayedColumns: string[] = ['name',  'company', 'place', 'registrationEndDate', 'eventDate'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {

    this.userDatas = this.state.user;

    /*
    this.teamService.getAllTeamsByLeaderUsingGET().subscribe(
      teams => {
        this.teamsIAmLeaderIn = teams;
        // console.log(this.teamsIAmLeaderIn);
      }
    );

    this.teamService.getAllTeamsByMemberUsingGET().subscribe(
      teams => {
        this.teamsIAmMemberIn = teams;
        // console.log(this.teamsIAmMemberIn);
      }
    );
    */
    this.getTeamsAsLeader();
    this.getTeamsAsMember();
    this.getCupsOrganizer();
    this.getCupsMembers();
  }

  getTeamsAsLeader() {
    this.teamService.getAllTeamsByLeaderUsingGET().subscribe(
      teams => {
        const array = teams.map(
          item => {
            return {
              $key: item.name,
              ...item
            };
          }
        );
        this.teamsIAmLeaderIn = new MatTableDataSource(array);
        this.teamsIAmLeaderIn.sort = this.sort;
        this.teamsIAmLeaderIn.paginator = this.paginator;
        this.teamsIAmLeaderInSize = this.teamsIAmLeaderIn.data.length;
      }
    );
  }

  getTeamsAsMember() {
    this.teamService.getAllTeamsByMemberUsingGET().subscribe(
      teams => {
        const array = teams.map(
          item => {
            return {
              $key: item.name,
              ...item
            };
          }
        );
        console.log(teams);
        this.teamsIAmMemberIn = new MatTableDataSource(array);
        this.teamsIAmMemberIn.sort = this.sort;
        this.teamsIAmMemberIn.paginator = this.paginator;
        this.teamsIAmMemberInSize = this.teamsIAmMemberIn.data.length;
      }
    );
  }

  getCupsMembers() {
    this.cupService.getCupsByParticipationUsingGET().subscribe(
      cuplist => {
        const array = cuplist.map(
          item => {
            return {
              $key: item.name,
              ...item
            };
          }
        );
        this.listCupDataMembership = new MatTableDataSource(array);
        this.listCupDataMembership.sort = this.sort;
        this.listCupDataMembership.paginator = this.paginator;
        this.listCupDataMembershipSize = this.listCupDataMembership.data.length;
      }
    );
  }

  getCupsOrganizer() {
    this.cupService.getCupByOrganizerUsingGET().subscribe(
      cuplist => {
        const array = cuplist.map(
          item => {
            return {
              $key: item.name,
              ...item
            };
          }
        );
        this.listCupData = new MatTableDataSource(array);
        this.listCupData.sort = this.sort;
        this.listCupData.paginator = this.paginator;
        this.listCupDataSize = this.listCupData.data.length;
      }
    );
  }
}
