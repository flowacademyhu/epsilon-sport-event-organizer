import { Component, OnInit, Input } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { TeamControllerService } from 'src/app/api';
import { RecentTeamService } from '../../service/recent-team.service';

@Component({
  selector: 'app-add-member-modal',
  templateUrl: './add-member-modal.component.html',
  styleUrls: ['./add-member-modal.component.css']
})
export class AddMemberModalComponent implements OnInit {

  memberNameToAdd: string = '';
  teamName: string;

  constructor(
    public dialogRef: MatDialogRef<AddMemberModalComponent>,
    private teamService: TeamControllerService,
    private recentTeam: RecentTeamService
    ) { }

  ngOnInit() {
  }

  add() {
    console.log('eteetetete');
    console.log(this.recentTeam.team.name);
    this.teamService.putMemberUsingPUT(this.memberNameToAdd, this.recentTeam.team.name).subscribe();
    this.dialogRef.close(AddMemberModalComponent);
  }

  exit() {
    this.dialogRef.close(AddMemberModalComponent);
  }

}
