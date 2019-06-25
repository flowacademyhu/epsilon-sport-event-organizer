import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CupControllerService } from 'src/app/api';

@Component({
  selector: 'app-delete-cup-confirm',
  templateUrl: './delete-cup-confirm.component.html',
  styleUrls: ['./delete-cup-confirm.component.css']
})
export class DeleteCupConfirmComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteCupConfirmComponent>,
    private cupService: CupControllerService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  onNoClick() {
    this.dialogRef.close();
  }

  onYesClick() {
    this.cupService.deleteCupUsingDELETE(this.data.cup.name).subscribe();
    this.dialogRef.close();
  }

  ngOnInit() {
  }

}
