import { Component, OnInit } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { CupControllerService } from 'src/app/api';
import { CreateCupModalComponent } from 'src/app/shared/component/create-cup-modal/create-cup-modal.component';
import { Scroll } from '@angular/router';
import { RepositionScrollStrategy, ScrollStrategyOptions } from '@angular/cdk/overlay';
import { attachEmbeddedView } from '@angular/core/src/view';

@Component({
  selector: 'app-cup',
  templateUrl: './cup.component.html',
  styleUrls: ['./cup.component.css']
})
export class CupComponent implements OnInit {

  constructor(
    private cupService: CupControllerService,
    private state: AppStateService,
    private dialog: MatDialog) { }

  ngOnInit() {
  }

  onCreateCup() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    //const overlaySettings: OverlaySettings = {
    //dialogConfig.scrollStrategy = new AbsoluteScrollStrategy();
    this.dialog.open(CreateCupModalComponent, dialogConfig);
  }

}
