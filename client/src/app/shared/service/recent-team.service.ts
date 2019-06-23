import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Team } from 'src/app/api';

@Injectable({
  providedIn: 'root'
})
export class RecentTeamService {

  constructor() { }

  private readonly _team = new BehaviorSubject<Team>(null);

  readonly team$ = this._team.asObservable();

  get team(): Team {
    return this._team.getValue();
  }

  set team(val: Team) {
    this._team.next(val);
  }
}
