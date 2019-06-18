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
  teams: any = '';
  creatTeam: Team;
  creatTeamName: String = '';
  creatCompanyName: String = '';
  data: any;
  dataLeader: any;
  addMember: String = '';
  teamNametoAdd: String = '';
  teamtoAddMember: Team;
  //deleteMember: String = '';
  teamNametoDelete: String = '';
  addLeader: String = '';
  teamNametoLeader: String = '';
  teamtoAddLeader: Team;
  deleteLeader: String = '';
  teamNametoDeleteLeader: String = '';
  teamNametoDeleteTeam: String = ''; 

  isLeader: boolean = false;

  constructor(private teamService: TeamService, private state: AppStateService) { }

  ngOnInit() {

  }

  deleteTeam() {
    this.teamService.deleteTeam(this.teamNametoDeleteTeam).subscribe(
      (data: any) => {
        this.teamNametoDeleteTeam = '';
      }
    );
  }

  deleteLeaderFromTeam() {
    this.teamService.deleteLeaderFromTeam(this.deleteLeader, this.teamNametoDeleteLeader).subscribe(
      (data: any) => {
    console.log('Alma');
    console.log(this.deleteLeader, this.teamNametoDeleteLeader);
    this.deleteLeader = '';
    this.teamNametoDeleteLeader = '';
      }
    );
  }

 /*  deleteMemberFromTeam() {
    this.teamService.deleteMemberFromTeam(this.deleteMember, this.teamNametoDelete);
    this.deleteMember = '';
    this.teamNametoDelete = '';
  } */

  putLeaderInTeam() {
    this.teamtoAddLeader = {name: this.teamNametoLeader, company: '', imageUrl: ''};
    this.teamService.putLeaderInTeam(this.addLeader, this.teamNametoLeader, this.teamtoAddLeader).subscribe(
      (data: any) => {
        console.log(data);
        this.addLeader = '';
        this.teamNametoLeader = '';
      }
    )
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
        this.teams = data;
        this.teamName = '';
        for (let i = 0; i < data.leaders.length; i++) {
            if (data.leaders[i].googleName == this.state.user.googleName) {
              this.isLeader = true;
            }
        }
      }
    );
  }

  deleteMember(name: string, team: any) {
    console.log('ITT');
    console.log(name);
    console.log(team);
    this.teamService.deleteMemberFromTeam(name, team).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }

}
