import { Component, OnInit } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
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
  list = [{
    'name': 'kkk',
    'company': 'asd'
  },
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
},
{
  'name': 'kuka',
  'company': 'lol'
}];
displayedColumns: string[] = ['name'];

  constructor(
    private teamService: TeamControllerService,
    private state: AppStateService,
    private dialog: MatDialog) { }

  ngOnInit() {

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
    this.teamService.deleteCupUsingDELETE1(teamName).subscribe(
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
    this.teamService.getTeamUsingGET(this.teamName).subscribe(
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

  promoteMember(name: string, teamName: string) {
    this.teamService.putMemberUsingPUT(name, teamName).subscribe(
      (data: any) => {
      }
    );
  }

  deleteMember(name: string, teamName: string) {
    this.teamService.deleteMemberUsingDELETE(name, teamName).subscribe(
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
