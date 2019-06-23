import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig, MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { CreateTeamModalComponent } from 'src/app/shared/component/create-team-modal/create-team-modal.component';
import { AddMemberModalComponent } from 'src/app/shared/component/add-member-modal/add-member-modal.component';
import { TeamControllerService, Team } from 'src/app/api';
import { TeamStateService } from 'src/app/shared/service/team-state.service';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { Observable, of } from 'rxjs';
import { RecentTeamService } from 'src/app/shared/service/recent-team.service';

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
  team: any = '';
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
    private teamService: TeamControllerService,
    private state: AppStateService,
    private teamState: TeamStateService,
    private recentTeam: RecentTeamService,
    private dialog: MatDialog) { }

  ngOnInit() {

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

  }


  applyFilter() {
    this.listData.filter = this.searchKey.trim().toLowerCase();
  }

  searchClear() {
    this.searchKey = '';
    this.applyFilter();
  }

  onAdd(team: Team) {
    this.recentTeam.team = team;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    this.dialog.open(AddMemberModalComponent, dialogConfig);
  }

  onCreate() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    this.dialog.open(CreateTeamModalComponent, dialogConfig);
  }

  deleteTeam(teamName: string) {
    this.teamService.deleteTeamUsingDELETE(teamName).subscribe(
      (data: any) => {
      }
    );
  }

  putMemberInTeam() {
    this.teamtoAddMember = {name: this.teamNametoAdd, company: '', imageUrl: ''};
    this.teamService.putMemberUsingPUT(this.memberToAdd, this.teamNametoAdd).subscribe(
      (data: any) => {
        this.memberToAdd = '';
        this.teamNametoAdd = '';
      }
    );
  }

  getByTeamName() {
    this.teamService.getTeamByNameUsingGET(this.teamName).subscribe(
      (data: any) => {
        console.log(data);
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
      }
    );
  }

  deleteMember(googleName: string, teamName: string) {
    this.teamService.deleteMemberUsingDELETE(googleName, teamName).subscribe(
      (data: any) => {
        this.team = data;
      }
    );
  }

  deleteLeader(name: string, teamName: string) {
    this.teamService.deleteLeaderUsingDELETE(name, teamName).subscribe(
      (data: any) => {
        this.team = data;
      }
    );
  }

}
