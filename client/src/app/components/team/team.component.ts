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

  constructor(private teamService: TeamService) { }

  ngOnInit() {
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

  getByTeamName() {
    this.teamService.getByTeamName(this.teamName).subscribe(
      (data: Team) => {
        this.teams = data;
        console.log(data);
      }
    );
  }

}
