import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { Team, TeamResourceService } from 'src/app/api';

@Component({
  selector: 'app-create-team-modal',
  templateUrl: './create-team-modal.component.html',
  styleUrls: ['./create-team-modal.component.css']
})
export class CreateTeamModalComponent implements OnInit {

  teamNameToCreate: string = '';
  companyNameToCreate: string = '';
  team: Team;

  constructor(
    public dialogRef: MatDialogRef<CreateTeamModalComponent>,
    private teamService: TeamResourceService
    ) { }

  ngOnInit() {
  }

  create() {
    this.team = {name: this.teamNameToCreate, company: this.companyNameToCreate};
    this.teamService.createTeamUsingPOST(this.team).subscribe(
      data => {
        this.dialogRef.close();
      }
    );
  }

  exit() {
    this.dialogRef.close();
  }

}
