import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CupResourceService } from 'src/app/api';

@Component({
  selector: 'app-disapprove-cup-confirm',
  templateUrl: './disapprove-cup-confirm.component.html',
  styleUrls: ['./disapprove-cup-confirm.component.css']
})
export class DisapproveCupConfirmComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DisapproveCupConfirmComponent>,
    private cupService: CupResourceService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  onYesClick() {
    this.cupService.refuseTeamUsingPOST(this.data.cup.name, this.data.team.name).subscribe();
    this.dialogRef.close();
  }

}
