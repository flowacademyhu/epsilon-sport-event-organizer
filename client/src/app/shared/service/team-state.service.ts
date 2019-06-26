import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Team, TeamResourceService } from 'src/app/api';

@Injectable({
  providedIn: 'root'
})
export class TeamStateService {

  constructor(
    private teamService: TeamResourceService
  ) { }

  private readonly _teams = new BehaviorSubject<Team[]>([]);

  readonly teams$ = this._teams.asObservable();

  get teams(): Team[] {
    return this._teams.getValue();
  }

  set teams(val: Team[]) {
    this._teams.next(val);
  }

  //not used
  addTeam(name: string, company: string, imageUrl: string) {
    this.teams = [
      ...this.teams,
      {name, company, imageUrl}
    ];
  }

  //not used
  removeTodo(name: string) {
    this.teams = this.teams.filter(team => team.name !== name);
  }

  getTeams() {
    this.teamService.getAllTeamsUsingGET().subscribe(
      teams => {
        this.teams = teams;
      }
    );
  }

}


