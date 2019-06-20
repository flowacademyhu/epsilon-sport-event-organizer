import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-add-member-modal',
  templateUrl: './add-member-modal.component.html',
  styleUrls: ['./add-member-modal.component.css']
})
export class AddMemberModalComponent implements OnInit {

  memberNameToAdd: String = '';

  constructor(public dialogRef: MatDialogRef<AddMemberModalComponent>) { }

  ngOnInit() {
  }

  add() {
    //swagger
  }

  exit() {
    this.dialogRef.close(AddMemberModalComponent);
  }

}
