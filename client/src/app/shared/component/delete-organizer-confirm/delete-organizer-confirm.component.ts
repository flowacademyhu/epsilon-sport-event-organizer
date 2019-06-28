import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CupResourceService } from 'src/app/api';

@Component({
  selector: 'app-delete-organizer-confirm',
  templateUrl: './delete-organizer-confirm.component.html',
  styleUrls: ['./delete-organizer-confirm.component.css']
})
export class DeleteOrganizerConfirmComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DeleteOrganizerConfirmComponent>,
    private cupService: CupResourceService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  onYesClick() {
    this.cupService.deleteOrganizerUsingPOST(this.data.cup.name, this.data.member).subscribe();
    this.dialogRef.close();
  }

}
