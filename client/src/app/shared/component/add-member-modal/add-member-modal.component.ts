import { Component, OnInit, Input } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { TeamControllerService } from 'src/app/api';

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
    private teamService: TeamControllerService
    ) { }

  ngOnInit() {
  }

  add() {
    console.log('eteetetete');
    console.log(this.teamName);
    this.teamService.putMemberUsingPUT(this.memberNameToAdd, this.teamName).subscribe();
    this.dialogRef.close(AddMemberModalComponent);
  }

  exit() {
    this.dialogRef.close(AddMemberModalComponent);
  }

}
