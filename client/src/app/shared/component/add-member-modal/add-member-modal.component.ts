import { Component, OnInit, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TeamResourceService } from 'src/app/api';

@Component({
  selector: 'app-add-member-modal',
  templateUrl: './add-member-modal.component.html',
  styleUrls: ['./add-member-modal.component.css']
})
export class AddMemberModalComponent implements OnInit {

  memberNameToAdd: string = '';
  guestNameToAdd: string = '';
  guestEmailToAdd: string = '';

  constructor(
    public dialogRef: MatDialogRef<AddMemberModalComponent>,
    private teamService: TeamResourceService,
    @Inject(MAT_DIALOG_DATA) public data: any
    ) { }

  ngOnInit() {
  }

  add() {
    this.teamService.putMemberUsingPUT(this.memberNameToAdd, this.data.team.name).subscribe();
    this.dialogRef.close(AddMemberModalComponent);
  }

  addGuest() {
    this.teamService.putGuestMemberUsingPOST(
      this.guestEmailToAdd,
      this.guestNameToAdd,
      this.data.leader.googleName,
      this.data.team.name).subscribe();
      this.dialogRef.close(AddMemberModalComponent);
  }

  exit() {
    this.dialogRef.close(AddMemberModalComponent);
  }

}
