import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog, MatDialogConfig, } from '@angular/material';
import { User, TeamResourceService, CupResourceService, Team, Cup } from 'src/app/api';
import { AddMemberModalComponent } from 'src/app/shared/component/add-member-modal/add-member-modal.component';
import { DeleteTeamConfirmComponent } from 'src/app/shared/component/delete-team-confirm/delete-team-confirm.component';
import { ApplyCupModalComponent } from 'src/app/shared/component/apply-cup-modal/apply-cup-modal.component';
import { DeleteCupConfirmComponent } from 'src/app/shared/component/delete-cup-confirm/delete-cup-confirm.component';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { DeleteLeaderConfirmComponent } from 'src/app/shared/component/delete-leader-confirm/delete-leader-confirm.component';
import { DeleteMemberConfirmComponent } from 'src/app/shared/component/delete-member-confirm/delete-member-confirm.component';
import { ApproveCupConfirmComponent } from 'src/app/shared/component/approve-cup-confirm/approve-cup-confirm.component';
import { DisapproveCupConfirmComponent } from 'src/app/shared/component/disapprove-cup-confirm/disapprove-cup-confirm.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ProfileComponent implements OnInit {

  constructor(private state: AppStateService,
              private teamService: TeamResourceService,
              private cupService: CupResourceService,
              private dialog: MatDialog) { }

  userDatas: User;

  teamsIAmLeaderIn: MatTableDataSource<any>;
  teamsIAmLeaderInSize: number;
  teamsIAmMemberIn: MatTableDataSource<any>;
  teamsIAmMemberInSize: number;
  myTeamsDefinition: string[] = ['name', 'company', 'actions'];
  teamsDefinition: string[] = ['name', 'company'];
  listCupData: MatTableDataSource<any>;
  listCupDataSize: number;
  listCupDataMembership: MatTableDataSource<any>;
  listCupDataMembershipSize: number;
  myDisplayedColumns: string[] = ['name',  'company', 'place', 'registrationEndDate', 'eventDate', 'actions'];
  displayedColumns: string[] = ['name',  'company', 'place', 'registrationEndDate', 'eventDate'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {

    this.userDatas = this.state.user;
    this.getTeamsAsLeader();
    this.getTeamsAsMember();
    this.getCupsOrganizer();
    this.getCupsMembers();
  }

  applyCup(cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.height = '80%';
    dialogConfig.data = {cup: cup};
    this.dialog.open(ApplyCupModalComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getCupsOrganizer();
     }
    );
  }

  deleteCup(cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {cup: cup};
    this.dialog.open(DeleteCupConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getCupsOrganizer();
      }
    );
  }

  approveCup(team: Team, cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {team: team, cup: cup};
    this.dialog.open(ApproveCupConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getCupsOrganizer();
     }
    );
  }

  noApproveCup(team: Team, cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {team: team, cup: cup};
    this.dialog.open(DisapproveCupConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getCupsOrganizer();
     }
    );
  }

  onAdd(leader: User, team: Team) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    dialogConfig.data = {leader: leader, team: team};
    this.dialog.open(AddMemberModalComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getTeamsAsLeader();
      }
    );
  }

  promoteMember(googleName: string, teamName: string) {
    this.teamService.putLeaderUsingPUT(googleName, teamName).subscribe(
      (data: any) => {
        this.getTeamsAsLeader();
      }
    );

  }

  deleteMember(member: User, team: Team) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {member: member, team: team};
    this.dialog.open(DeleteMemberConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getTeamsAsLeader();
        this.getTeamsAsMember();
      }
    );
  }

  deleteLeader(leader: User, team: Team) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {leader: leader, team: team};
    this.dialog.open(DeleteLeaderConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getTeamsAsLeader();
      }
    );
  }

  deleteTeam(team: Team) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {team: team};
    this.dialog.open(DeleteTeamConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getTeamsAsLeader();
      }
    );
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
