import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig, MatSort, MatTableDataSource, MatPaginator } from '@angular/material';
import { CreateTeamModalComponent } from 'src/app/shared/component/create-team-modal/create-team-modal.component';
import { AddMemberModalComponent } from 'src/app/shared/component/add-member-modal/add-member-modal.component';
import { TeamControllerService, Team } from 'src/app/api';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
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

  isLeader: boolean = false;
  isSearchPressed: boolean = false;
  searchKey: string;
  listData: MatTableDataSource<any>;
  displayedColumns: string[] = ['name', 'company', 'actions'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private teamService: TeamControllerService,
    private state: AppStateService,
    private dialog: MatDialog) { }

  ngOnInit() {

    this.teamService.getAllTeamsUsingGET().subscribe(
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

  applyFilter() {
    this.listData.filter = this.searchKey.trim().toLowerCase();
  }

  searchClear() {
    this.searchKey = '';
    this.applyFilter();
  }

  onAdd() {
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
    this.isLeader = false;
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
        this.isLeader = false;
        for (let i = 0; i < data.leaders.length; i++) {
            if (data.leaders[i].googleName == this.state.user.googleName) {
              this.isLeader = true;
            }
        }
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
    this.isLeader = false;
    this.teamService.deleteLeaderUsingDELETE(name, teamName).subscribe(
      (data: any) => {
        for (let i = 0; i < data.leaders.length; i++) {
          if (data.leaders[i].googleName == this.state.user.googleName) {
            console.log('leader true');
            this.isLeader = true;
          } else {
            console.log('leader false');
            this.isLeader = false;
          }
        }
        this.team = data;
        console.log(this.team);
      }
    );
  }

}
