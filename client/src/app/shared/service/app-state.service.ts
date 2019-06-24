import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/api';

@Injectable({
  providedIn: 'root'
})
export class AppStateService {

  private readonly _user = new BehaviorSubject<User>(null);

  readonly user$ = this._user.asObservable();

  constructor() { }

  get user(): User {
    if (!this._user.value && localStorage.getItem('user')) {
      this.user = JSON.parse(localStorage.getItem('user'));
    }
    return this._user.getValue();
  }

  set user(val: User) {
    this._user.next(val);
  }

}
