import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-show-team-modal',
  templateUrl: './show-team-modal.component.html',
  styleUrls: ['./show-team-modal.component.css']
})
export class ShowTeamModalComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ShowTeamModalComponent>) { }

  ngOnInit() {
  }

  exit() {
    this.dialogRef.close(ShowTeamModalComponent);
  }

}
