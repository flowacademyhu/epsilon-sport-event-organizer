import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TeamControllerService } from 'src/app/api';

@Component({
  selector: 'app-delete-member-confirm',
  templateUrl: './delete-member-confirm.component.html',
  styleUrls: ['./delete-member-confirm.component.css']
})
export class DeleteMemberConfirmComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteMemberConfirmComponent>,
    private teamService: TeamControllerService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  onNoClick() {
    this.dialogRef.close();
  }

  onYesClick() {
    this.teamService.deleteMemberUsingDELETE(this.data.member.googleName, this.data.team.name).subscribe();
    this.dialogRef.close();
  }

  ngOnInit() {
  }

}
