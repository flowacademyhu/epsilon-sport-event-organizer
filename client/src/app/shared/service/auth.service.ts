import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
/* import * as jwtDecode from 'jwt-decode';
import decode from 'jwt-decode'; */
/* import { tokenNotExpired } from 'angular-jwt'; */
//Here are questions pleeez...

@Injectable()
export class AuthService {

  public getToken(): string {
    return localStorage.getItem('token');
  }

  public isAuthenticated(): boolean {
    // get the token
    const token = this.getToken();
    // return a boolean reflecting 
    // whether or not the token is expired
    return isTokenExpired(null, token);
  }

}