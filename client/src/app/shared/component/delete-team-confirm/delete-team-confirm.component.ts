import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TeamControllerService } from 'src/app/api';

@Component({
  selector: 'app-delete-team-confirm',
  templateUrl: './delete-team-confirm.component.html',
  styleUrls: ['./delete-team-confirm.component.css']
})
export class DeleteTeamConfirmComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteTeamConfirmComponent>,
    private teamService: TeamControllerService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  onNoClick() {
    this.dialogRef.close();
  }

  onYesClick() {
    this.teamService.deleteTeamUsingDELETE(this.data.team.name).subscribe();
    this.dialogRef.close();
  }

  ngOnInit() {
  }

}
