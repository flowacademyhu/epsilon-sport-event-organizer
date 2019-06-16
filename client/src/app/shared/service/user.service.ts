import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private httpclient: HttpClient
  ) { }

  findAll(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/auth/list-users');
  }

  getUser(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/auth/get-user');
  }
}
