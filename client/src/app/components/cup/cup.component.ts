import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { MatDialog, MatDialogConfig, MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { CreateCupModalComponent } from 'src/app/shared/component/create-cup-modal/create-cup-modal.component';
import { DeleteCupConfirmComponent } from 'src/app/shared/component/delete-cup-confirm/delete-cup-confirm.component';
import { TeamStateService } from 'src/app/shared/service/team-state.service';
import { TeamResourceService, CupResourceService, Team, Cup } from 'src/app/api';


@Component({
  selector: 'app-cup',
  templateUrl: './cup.component.html',
  styleUrls: ['./cup.component.css']
})
export class CupComponent implements OnInit {

  constructor(
    private cupService: CupResourceService,
    public state: AppStateService,
    private dialog: MatDialog,
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

    this.cupService.getAllCupsUsingGET().subscribe(
      cuplist => {
         const array = cuplist.map(
          item => {
            return {
              $key: item.name,
              ...item
            };
          });
        console.log(cuplist);
        this.listData = new MatTableDataSource(array);
        this.listData.sort = this.sort;
        this.listData.paginator = this.paginator;
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
    dialogConfig.width = '40%';
    dialogConfig.height = '80%';
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
