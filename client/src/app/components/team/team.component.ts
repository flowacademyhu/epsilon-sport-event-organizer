import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig, MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { CreateTeamModalComponent } from 'src/app/shared/component/create-team-modal/create-team-modal.component';
import { AddMemberModalComponent } from 'src/app/shared/component/add-member-modal/add-member-modal.component';
import { TeamResourceService, Team, User } from 'src/app/api';
import { TeamStateService } from 'src/app/shared/service/team-state.service';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { DeleteTeamConfirmComponent } from 'src/app/shared/component/delete-team-confirm/delete-team-confirm.component';
import { DeleteMemberConfirmComponent } from 'src/app/shared/component/delete-member-confirm/delete-member-confirm.component';
import { DeleteLeaderConfirmComponent } from 'src/app/shared/component/delete-leader-confirm/delete-leader-confirm.component';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class TeamComponent implements OnInit {

  teamName: string = '';
  companyName: string = '';
  team: any = {};
  createdTeam: Team;
  data: any;
  dataLeader: any;
  memberToAdd: string = '';
  teamNametoAdd: string = '';
  teamtoAddMember: Team;

  isSearchPressed: boolean = false;
  teamList: Team[];
  searchKey: string;
  listData: MatTableDataSource<any>;
  displayedColumns: string[] = ['name', 'company', 'actions'];

  expandedElement: any | null;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private teamService: TeamResourceService,
    private teamState: TeamStateService,
    private dialog: MatDialog,
    public state: AppStateService
    ) { }

  ngOnInit() {
    this.getData();
    this.teamState.teams$.subscribe(
      teamlist => {
         const array = teamlist.map(
          item => {
            return {
              $key: item.name,
              ...item
            };
          });
        this.listData = new MatTableDataSource(array);
        this.listData.sort = this.sort;
        this.listData.paginator = this.paginator;
      }
   );
  }

  /* getData() {
    const array = this.teamState.getTeams().map(
      item => {
        return {
          $key: item.name,
          ...item
        };
      });
    this.listData = new MatTableDataSource(array);
    this.listData.sort = this.sort;
    this.listData.paginator = this.paginator;
  } */

  getData() {
    this.teamState.getTeams();
  }

/*   getData() {
    this.teamService.getAllTeamsUsingGET().subscribe(
      teamlist => {
        this.teamList = teamlist;
         const array = teamlist.map(
          item => {
            return {
              $key: item.name,
              ...item
            };
          });
        this.listData = new MatTableDataSource(array);
        this.listData.sort = this.sort;
        this.listData.paginator = this.paginator;
      }
    );
  } */

  applyFilter() {
    this.listData.filter = this.searchKey.trim().toLowerCase();
  }

  searchClear() {
    this.searchKey = '';
    this.applyFilter();
  }

  onAdd(leader: User, team: Team) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    dialogConfig.data = {leader: leader, team: team};
    this.dialog.open(AddMemberModalComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getData();
      }
    );
  }

  onCreate() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    this.dialog.open(CreateTeamModalComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getData();
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
        this.getData();
      }
    );
  }

  putMemberInTeam() {
    this.teamtoAddMember = {name: this.teamNametoAdd, company: '', imageUrl: ''};
    this.teamService.putMemberUsingPUT(this.memberToAdd, this.teamNametoAdd).subscribe(
      (data: any) => {
        this.memberToAdd = '';
        this.teamNametoAdd = '';
        this.getData();

      }
    );
  }

  getByTeamName() {
    this.teamService.getTeamByNameUsingGET(this.teamName).subscribe(
      (data: any) => {
        this.team = data;
        this.teamName = '';
        this.isSearchPressed = true;
      }
    );
  }

  promoteMember(googleName: string, teamName: string) {
    this.teamService.putLeaderUsingPUT(googleName, teamName).subscribe(
      (data: any) => {
        this.team.leaders = data.leaders;
        this.getData();
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
        this.getData();
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
        this.getData();
      }
    );
  }

}
