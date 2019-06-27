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
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
    this.dialogRef.close(AddMemberModalComponent);
  }

  addGuest() {
    this.teamService.putGuestMemberUsingPOST(
      this.guestEmailToAdd,
      this.guestNameToAdd,
      this.data.leader.googleName,
      this.data.team.name).subscribe();
      // Get the snackbar DIV
      var x = document.getElementById("snackbar");

      // Add the "show" class to DIV
      x.className = "show";

      // After 3 seconds, remove the show class from DIV
      setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
      this.dialogRef.close(AddMemberModalComponent);
  }

  exit() {
    this.dialogRef.close(AddMemberModalComponent);
  }

}
