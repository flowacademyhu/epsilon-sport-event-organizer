import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig, MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { DeleteCupConfirmComponent } from 'src/app/shared/component/delete-cup-confirm/delete-cup-confirm.component';
import { CreateCupModalComponent } from 'src/app/shared/component/create-cup-modal/create-cup-modal.component';
import { TeamStateService } from 'src/app/shared/service/team-state.service';
import { ApplyCupModalComponent } from 'src/app/shared/component/apply-cup-modal/apply-cup-modal.component';
import { TeamResourceService, CupResourceService, Team, Cup } from 'src/app/api';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { ApproveCupConfirmComponent } from 'src/app/shared/component/approve-cup-confirm/approve-cup-confirm.component';
import { DisapproveCupConfirmComponent } from 'src/app/shared/component/disapprove-cup-confirm/disapprove-cup-confirm.component';

@Component({
  selector: 'app-cup',
  templateUrl: './cup.component.html',
  styleUrls: ['./cup.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class CupComponent implements OnInit {

  constructor(
    private cupService: CupResourceService,
    public state: AppStateService,
    private dialog: MatDialog,
    private teamStateService: TeamStateService,
    private teamService: TeamResourceService
    ) { }

  cupNameToDelete: string = '';

  isLeader: boolean = false;
  isSearchPressed: boolean = false;
  searchKey: string;
  listData: MatTableDataSource<any>;
  displayedColumns: string[] = ['name',  'company', 'place', 'registrationEndDate', 'eventDate', 'actionsa']
  teams: Team;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {

    this.getData();

        this.teamService.getAllTeamsUsingGET().subscribe(
          (teams: any) => {
            this.teamStateService.teams = teams;
            this.teams = teams;
          }
      );


  }

  applyCup(cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.height = '80%';
    dialogConfig.data = {cup: cup};
    this.dialog.open(ApplyCupModalComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getData();
     }
    );
  }

  approveCup(team: Team, cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {team: team, cup: cup};
    this.dialog.open(ApproveCupConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getData();
     }
    );
  }

  noApproveCup(team: Team, cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {team: team, cup: cup};
    this.dialog.open(DisapproveCupConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getData();
     }
    );
  }

  deleteCup(cup: Cup) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {cup: cup};
    this.dialog.open(DeleteCupConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getData();
      }
    );
  }

getData() {
  this.cupService.getAllCupsUsingGET().subscribe(
    cuplist => {
       const array = cuplist.map(
        item => {
          return {
            $key: item.name,
            ...item
          };
        });
      this.listData = new MatTableDataSource(array);
      this.listData.sort = this.sort;
      this.listData.paginator = this.paginator;
    }
  );

}
  applyFilter() {
    this.listData.filter = this.searchKey.trim().toLowerCase();
  }

  searchClear() {
    this.searchKey = '';
    this.applyFilter();
  }

  onCreateCup() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '30%';
    dialogConfig.height = '70%';
    this.dialog.open(CreateCupModalComponent, dialogConfig).afterClosed().subscribe(
      (data: any) => {
        this.getData();
      }
    );

  }

  onDeleteCup() {
    this.cupService.deleteCupUsingDELETE(this.cupNameToDelete).subscribe(
      (data: any) => {
      }
    );
    this.cupNameToDelete = '';
  }

}
