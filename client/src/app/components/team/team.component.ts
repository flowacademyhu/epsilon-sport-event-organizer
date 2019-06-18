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
  deleteMember: String = '';
  teamNametoDelete: String = '';

  isLeader: boolean = false;
  leadersArray: any[];

  constructor(private teamService: TeamService, private state: AppStateService) { }

  ngOnInit() {

  }

  deleteMemberFromTeam() {
    this.teamService.deleteMemberFromTeam(this.deleteMember, this.teamNametoDelete);
    this.deleteMember = '';
    this.teamNametoDelete = '';
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
        console.log(this.isLeader);
        console.log(this.teams);
        console.log(this.state.user.id);
        console.log('asdasd');
        console.log(data.leader[0]);
        for (let i = 0; i < data.leader.length; i++) {
            if (this.data.leader[i].includes(this.state.user.googleName)) {
              console.log('hello');
              this.isLeader = true;
            }
        }
      }
    );
  }

}
