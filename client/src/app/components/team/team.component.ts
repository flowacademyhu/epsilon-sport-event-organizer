import { Component, OnInit } from '@angular/core';
import { TeamService } from 'src/app/shared/service/team.service';
import { AppStateService } from 'src/app/shared/service/app-state.service';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  teamName: String = '';
  companyName: String = '';
  team: any = '';
  creatTeam: Team;
  creatTeamName: String = '';
  creatCompanyName: String = '';
  data: any;
  dataLeader: any;
  addMember: String = '';
  teamNametoAdd: String = '';
  teamtoAddMember: Team;

  isLeader: boolean = false;
  isSearchPressed: boolean = false;

  constructor(private teamService: TeamService, private state: AppStateService) { }

  ngOnInit() {

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
    this.teamService.putMemberInTeam(this.addMember, this.teamNametoAdd, this.teamtoAddMember).subscribe(
      (data: any) => {
        this.addMember = '';
        this.teamNametoAdd = '';
      }
    );
  }

  create() {
    this.creatTeam = {name: this.creatTeamName, company: this.creatCompanyName, imageUrl: ''};

    this.isLeader = false;

    this.teamService.create(this.creatTeam).subscribe(
      (data: any) => {
        this.data = data;
        this.dataLeader = data.leader;
        this.creatTeamName = '';
        this.creatCompanyName = '';
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
        this.team.members = data.members;
      }
    );
  }

  deleteLeader(name: string, teamName: string) {
    this.isLeader = false;
    this.teamService.deleteLeaderFromTeam(name, teamName).subscribe(
      (data: any) => {
      }
    );
  }

}