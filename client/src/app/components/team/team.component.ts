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

  team: Team;

  constructor(private teamService: TeamService) { }

  ngOnInit() {
  }

  feri() {
    this.team.name = this.teamName;
    this.team.company = this.companyName;
    this.teamService.update(this.team).subscribe(
      (team: Team) => {
        console.log(team);
      }
    );
  }

}
