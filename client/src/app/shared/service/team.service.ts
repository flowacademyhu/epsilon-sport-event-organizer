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

  getByTeamName(name: String) {
    return this.httpclient.get('http://localhost:8080/team/team/' + name);
  }

  create(team: Team): Observable<any> {
    return this.httpclient.post('http://localhost:8080/team/create', team);
  }

  update(team: Team): Observable<any> {
    return this.httpclient.put('http://localhost:8080/team/update', team);
  }

  getByMember(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/team/get-by-current-member');
  }

  putMemberInTeam(userName: String, teamName: String, team: Team): Observable<any> {
    return this.httpclient.put('http://localhost:8080/team/put-member/' + userName + '/' + teamName, team);
  }

  deleteMemberFromTeam(userName: String, team: String): Observable<any> {
    return this.httpclient.delete('http://localhost:8080/team/delete-member/' + userName + '/' + team);
  }

  getByLeader(): Observable<any> {
    return this.httpclient.get('http://localhost:8080/team/get-by-current-leader');
  }

  putLeaderInTeam(leaderName: String, teamName: String, team: Team): Observable<any> {
    return this.httpclient.put('http://localhost:8080/team/put-leader/' + leaderName + '/' + teamName, team);
  }

  deleteLeaderFromTeam(leaderName: String, team: String): Observable<any> {
    return this.httpclient.delete('http://localhost:8080/team/delete-leader/' + leaderName + '/' + team);
  }

  deleteTeam(teamName: String){
    this.httpclient.delete('http://localhost:8080/team/delete/' + teamName);
  }
}
