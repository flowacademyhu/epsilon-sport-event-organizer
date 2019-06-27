import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CupResourceService } from 'src/app/api';

@Component({
  selector: 'app-approve-cup-confirm',
  templateUrl: './approve-cup-confirm.component.html',
  styleUrls: ['./approve-cup-confirm.component.css']
})
export class ApproveCupConfirmComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ApproveCupConfirmComponent>,
    private cupService: CupResourceService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  onYesClick() {
    this.cupService.approveTeamUsingPOST(this.data.cup.name, this.data.team.name).subscribe();
    this.dialogRef.close();
  }

}
