import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-create-team-modal',
  templateUrl: './create-team-modal.component.html',
  styleUrls: ['./create-team-modal.component.css']
})
export class CreateTeamModalComponent implements OnInit {

  teamNameToCreate: String = '';
  companyNameToCreate: String = '';

  constructor(public dialogRef: MatDialogRef<CreateTeamModalComponent>) { }

  ngOnInit() {
  }

  create() {
    //swagger
  }

  exit() {
    this.dialogRef.close(CreateTeamModalComponent);
  }

}
