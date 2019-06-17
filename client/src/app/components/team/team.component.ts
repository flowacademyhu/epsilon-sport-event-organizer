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

  team: any;

  constructor(private teamService: TeamService) { }

  ngOnInit() {
  }

  create() {
    this.team = {name: this.teamName, company: this.companyName, imageUrl: ''};

    this.teamService.create(this.team).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }
/* 
  getByTeamName() {
    this.teamService.getByTeamName(this.teamName).subscribe(
      (data: Team) => {
        this.teams = data;
        console.log(data);
      }
    );
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

}
