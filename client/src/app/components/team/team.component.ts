import { Component, OnInit } from '@angular/core';
import { TeamService } from 'src/app/shared/service/team.service';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  teamName: String = '';
  companyName: String = '';
  teams: any;
  creatTeam: Team;
  creatTeamName: String = '';
  creatCompanyName: String = '';
  data: any;
  dataLeader: any;
  addMember: String = '';
  teamNametoAdd: String = '';
  teamtoAddMember: Team;

<<<<<<< HEAD
  team: any;

=======
>>>>>>> 3e796cb182a11adad9b823b2a14bb12d0030ed19
  constructor(private teamService: TeamService) { }

  ngOnInit() {
  }

<<<<<<< HEAD
  create() {
    this.team = {name: this.teamName, company: this.companyName, imageUrl: ''};

    this.teamService.create(this.team).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }
/* 
=======
  putMemberInTeam() {
    this.teamtoAddMember = {name: this.teamNametoAdd, company: '', imageUrl: ''};
    this.teamService.putMemberInTeam(this.addMember, this.teamNametoAdd, this.teamtoAddMember).subscribe(
      (data: any) => {
        console.log('a', data);
      }
    )
  }

  create() {
    this.creatTeam = {name: this.creatTeamName, company: this.creatCompanyName, imageUrl: ''};

    this.teamService.create(this.creatTeam).subscribe(
      (data: any) => {
        console.log(data);
        this.data = data;
        this.dataLeader = data.leader;
      }
    );
  }

>>>>>>> 3e796cb182a11adad9b823b2a14bb12d0030ed19
  getByTeamName() {
    this.teamService.getByTeamName(this.teamName).subscribe(
      (data: Team) => {
        this.teams = data;
        console.log(data);
      }
    );
<<<<<<< HEAD
  } */

  /* feri() {
    this.team.name = this.teamName;
    this.team.company = this.companyName;
    this.teamService.update(this.team).subscribe(
      (team: Team) => {
        console.log(team);
      }
    );
  } */
=======
  }
>>>>>>> 3e796cb182a11adad9b823b2a14bb12d0030ed19

}
