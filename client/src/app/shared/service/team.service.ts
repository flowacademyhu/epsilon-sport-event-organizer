import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(
    private httpclient: HttpClient,
  ) { }

<<<<<<< HEAD
  getByTeamName(name: String) {
=======
  getByTeamName(name: String): Observable<any> {
>>>>>>> 76497b0138c5a927ac63e9ab6a33f46d72157e53
    return this.httpclient.get('http://localhost:8080/auth/team/' + name);
  }

  create(team: Team): Observable<any> {
    return this.httpclient.post('http://localhost:8080/auth/create', team);
  }

  update(team: Team): Observable<any> {
    return this.httpclient.put('http://localhost:8080/auth/update', team);
  }

  getByMember(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/auth/get-by-member');
  }

  putMemberInTeam(userName: String, teamName: String, team: Team): Observable<any> {
    return this.httpclient.put('http://localhost:8080/auth/put-member/' + userName + '/' + teamName, team);
  }

  deleteMemberFromTeam(userName: String, team: String): Observable<any> {
    return this.httpclient.delete('http://localhost:8080/auth/delete-member/' + userName + '/' + team);
  }

  getByLeader(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/auth/get-by-leader');
  }

  putLeaderInTeam(leaderName: String, teamName: String, team: Team): Observable<any> {
    return this.httpclient.put('http://localhost:8080/auth/put-leader/' + leaderName + '/' + teamName, team);
  }

  deleteLeaderFromTeam(leaderName: String, team: String): Observable<any> {
    return this.httpclient.delete('http://localhost:8080/auth/delete-leader/' + leaderName + '/' + team);
  }
}
