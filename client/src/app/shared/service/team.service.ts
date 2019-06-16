import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(
    private httpclient: HttpClient,
    private team: Team //not this, we have to save a team in app-state.service
  ) { }

  findAll(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/auth/team-list');
  }

  getTeam(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/auth/team/' + this.team.name);
  }

  deleteTeam(): Observable<any> {
    return this.httpclient.delete('http://localhost:8080/auth/team-delete/' + this.team.name);
  }

  putTeam(team: Team): Observable<any> {
    return this.httpclient.put('http://localhost:8080/auth/team-modify', team);
  }

  postTeam(team: Team): Observable<any> {
    return this.httpclient.post('http://localhost:8080/auth/team-create', team);
  }
}
