import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TeamControllerService } from 'src/app/api';

@Component({
  selector: 'app-delete-leader-confirm',
  templateUrl: './delete-leader-confirm.component.html',
  styleUrls: ['./delete-leader-confirm.component.css']
})
export class DeleteLeaderConfirmComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteLeaderConfirmComponent>,
    private teamService: TeamControllerService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  onNoClick() {
    this.dialogRef.close();
  }

  onYesClick() {
    this.teamService.deleteLeaderUsingDELETE(this.data.leader.googleName, this.data.team.name).subscribe();
    this.dialogRef.close();
  }

  ngOnInit() {
  }

}
