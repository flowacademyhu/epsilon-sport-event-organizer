import { Injectable } from '@angular/core';
import * as jwtDecode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  public user() {
    return this.httpClient.get('//localhost:8080/auth/get-user');
  }
}
