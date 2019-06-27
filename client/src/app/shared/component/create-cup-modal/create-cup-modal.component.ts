import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { CupResourceService, Cup } from 'src/app/api';

@Component({
  selector: 'app-create-cup-modal',
  templateUrl: './create-cup-modal.component.html',
  styleUrls: ['./create-cup-modal.component.css']
})
export class CreateCupModalComponent implements OnInit {

  constructor( 
    public dialogRef: MatDialogRef<CreateCupModalComponent>,
    private cupService: CupResourceService) { }

    companyNameToCreate: string = '';
    courtCounter: number = 1;
    deleted: boolean;
    description: string = '';
    endDateTime: Date;
    imageUrl: string;
    cupNametoCreate: string = '';
    place: string = '';
    registrationEndDate: string;
    eventDate: any;
    cup: Cup;
    datum: any;
    sportType: string = '';


  ngOnInit() {
  }

  createCup() {
    this.cup = {company: this.companyNameToCreate, courtCounter: this.courtCounter,
                description: this.description, name: this.cupNametoCreate,
                place: this.place, registrationEndDate: this.registrationEndDate, eventDate: this.eventDate,
                sportType: this.sportType};
    this.cupService.createCupUsingPOST(this.cup).subscribe(
      (data: any) => {
        // Get the snackbar DIV
        var x = document.getElementById("snackbar");

        // Add the "show" class to DIV
        x.className = "show";

        // After 3 seconds, remove the show class from DIV
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);

        this.dialogRef.close(CreateCupModalComponent);
      }
    );
  }
  exit() {
    this.dialogRef.close(CreateCupModalComponent);
  }

}
