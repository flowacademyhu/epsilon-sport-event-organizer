import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Team } from 'src/app/api';

@Injectable({
  providedIn: 'root'
})
export class TeamStateService {

  constructor() { }

  private readonly _teams = new BehaviorSubject<Team[]>([]);

  readonly teams$ = this._teams.asObservable();

  get teams(): Team[] {
    return this._teams.getValue();
  }

  set teams(val: Team[]) {
    this._teams.next(val);
  }

  addTeam(name: string, company: string, imageUrl: string) {
    this.teams = [
      ...this.teams,
      {name, company, imageUrl}
    ];
  }

  removeTodo(name: string) {
    this.teams = this.teams.filter(team => team.name !== name);
  }

}


