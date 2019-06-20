import { Component, OnInit } from '@angular/core';
import { TeamService } from 'src/app/shared/service/team.service';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { CreateTeamModalComponent } from 'src/app/shared/component/create-team-modal/create-team-modal.component';
import { AddMemberModalComponent } from 'src/app/shared/component/add-member-modal/add-member-modal.component';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  teamName: String = '';
  companyName: String = '';
  team: any = '';
  createdTeam: Team;
  data: any;
  dataLeader: any;
  memberToAdd: String = '';
  teamNametoAdd: String = '';
  teamtoAddMember: Team;

  isLeader: boolean = false;
  isSearchPressed: boolean = false;

  constructor(
    private teamService: TeamService,
    private state: AppStateService,
    private dialog: MatDialog
    ) { }

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

  deleteTeam(teamName: String) {
    this.isLeader = false;
    this.teamService.deleteTeam(teamName).subscribe(
      (data: any) => {
      }
    );
  }

  putMemberInTeam() {
    this.teamtoAddMember = {name: this.teamNametoAdd, company: '', imageUrl: ''};
    this.teamService.putMemberInTeam(this.memberToAdd, this.teamNametoAdd, this.teamtoAddMember).subscribe(
      (data: any) => {
        this.memberToAdd = '';
        this.teamNametoAdd = '';
      }
    );
  }

  getByTeamName() {
    this.teamService.getByTeamName(this.teamName).subscribe(
      (data: any) => {
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

  promoteMember(name: String, teamName: String, team: Team) {
    this.teamService.putLeaderInTeam(name, teamName, team).subscribe(
      (data: any) => {
      }
    );
  }

  deleteMember(name: string, teamName: String) {
    this.teamService.deleteMemberFromTeam(name, teamName).subscribe(
      (data: any) => {
        this.team = data;
      }
    );
  }

  deleteLeader(name: string, teamName: string) {
    this.teamService.deleteLeaderFromTeam(name, teamName).subscribe(
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
