import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppStateService {

  private readonly _user = new BehaviorSubject<User>(null);

  readonly user$ = this._user.asObservable();

  constructor() { }

  get user(): User {
    return this._user.getValue();
  }

  set user(val: User) {
    this._user.next(val);
  }

}
