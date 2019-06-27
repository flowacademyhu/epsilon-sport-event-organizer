import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-show-cup-modal',
  templateUrl: './show-cup-modal.component.html',
  styleUrls: ['./show-cup-modal.component.css']
})
export class ShowCupModalComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ShowCupModalComponent>) { }

  ngOnInit() {
  }

  exit() {
    this.dialogRef.close(ShowCupModalComponent);
  }

}
