import { Component, OnInit } from '@angular/core';
import { TeamService } from 'src/app/shared/service/team.service';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig } from '@angular/material';

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
  teamNameToCreate: String = '';
  companyNameToCreate: String = '';
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

  onCreate() {
    this.dialog.open(TeamComponent);
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

  create() {
    this.createdTeam = {name: this.teamNameToCreate, company: this.companyNameToCreate, imageUrl: ''};

    this.isLeader = false;

    this.teamService.create(this.createdTeam).subscribe(
      (data: any) => {
        this.data = data;
        this.dataLeader = data.leader;
        this.teamNameToCreate = '';
        this.companyNameToCreate = '';
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
