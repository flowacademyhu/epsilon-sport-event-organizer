import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { CupControllerService, Cup } from 'src/app/api';

@Component({
  selector: 'app-create-cup-modal',
  templateUrl: './create-cup-modal.component.html',
  styleUrls: ['./create-cup-modal.component.css']
})
export class CreateCupModalComponent implements OnInit {

  constructor( 
    public dialogRef: MatDialogRef<CreateCupModalComponent>,
    private cupService: CupControllerService) { }

    companyNameToCreate: string = '';
    courtCounter: number = 0;
    deleted: boolean;
    description: string = '';
    endDateTime: Date;
    imageUrl: string;
    cupNametoCreate: string = '';
    place: string = '';
    registrationEndTime: Date;
    startDateTime: Date;
    cup: Cup;

  ngOnInit() {
  }

  createCup() {
    this.cup = {company: this.companyNameToCreate, courtCounter: this.courtCounter, 
                description: this.description, endDateTime: this.endDateTime, name: this.cupNametoCreate,
                place: this.place, registrationEndTime: this.registrationEndTime, startDateTime: this.startDateTime};
    this.cupService.createCupUsingPOST(this.cup).subscribe(
      (data: any) => {
        this.dialogRef.close(CreateCupModalComponent);
      }
    )
        
  }

}
