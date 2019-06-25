import { Component, OnInit } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { TeamControllerService, Team } from 'src/app/api';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  teamName: string = '';
  companyName: string = '';
  team: any = '';
  createdTeam: Team;
  teamNameToCreate: string = '';
  companyNameToCreate: string = '';
  data: any;
  dataLeader: any;
  memberToAdd: string = '';
  teamNametoAdd: string = '';
  teamtoAddMember: Team;

  isLeader: boolean = false;
  isSearchPressed: boolean = false;

  constructor(private teamService: TeamControllerService, private state: AppStateService) { }

  ngOnInit() {

  }

  deleteTeam(teamName: string) {
    this.isLeader = false;
    this.teamService.deleteTeamUsingDELETE(teamName).subscribe(
      (data: any) => {
      }
    );
  }

  putMemberInTeam() {
    this.teamtoAddMember = {name: this.teamNametoAdd, company: '', imageUrl: ''};
    this.teamService.putMemberUsingPUT(this.memberToAdd, this.teamNametoAdd).subscribe(
      (data: any) => {
        this.memberToAdd = '';
        this.teamNametoAdd = '';
      }
    );
  }

  create() {
    this.createdTeam = {name: this.teamNameToCreate, company: this.companyNameToCreate, imageUrl: ''};

    this.isLeader = false;

    this.teamService.createTeamUsingPOST(this.createdTeam).subscribe(
      (data: any) => {
        this.data = data;
        this.dataLeader = data.leader;
        this.teamNameToCreate = '';
        this.companyNameToCreate = '';
      }
    );
  }

  getByTeamName() {
    this.teamService.getTeamByNameUsingGET(this.teamName).subscribe(
      (data: any) => {
        this.team = data;
        this.teamName = '';
        this.isSearchPressed = true;
        this.isLeader = false;
        for (let i = 0; i < data.leaders.length; i++) {
            if (data.leaders[i].googleName == this.state.user.googleName) {
              this.isLeader = true;
            }
        }
      }
    );
  }

  promoteMember(googleName: string, teamName: string) {
    this.teamService.putLeaderUsingPUT(googleName, teamName).subscribe(
      (data: any) => {
        this.team.leaders = data.leaders;
      }
    );
  }

  deleteMember(googleName: string, teamName: string) {
    this.teamService.deleteMemberUsingDELETE(googleName, teamName).subscribe(
      (data: any) => {
        this.team.members = data.members;
      }
    );
  }

  deleteLeader(name: string, teamName: string) {
    this.isLeader = false;
    this.teamService.deleteLeaderUsingDELETE(name, teamName).subscribe(
      (data: any) => {
      }
    );
  }

}
