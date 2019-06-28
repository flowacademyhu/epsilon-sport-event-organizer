import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { TeamResourceService, Team, CupResourceService, Cup, User } from 'src/app/api';
import { MatTableDataSource, MatSort, MatPaginator, MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig, MatDialog } from '@angular/material';
import { AppStateService } from '../../service/app-state.service';
import { DeleteOrganizerConfirmComponent } from '../delete-organizer-confirm/delete-organizer-confirm.component';
import { TeamStateService } from '../../service/team-state.service';

@Component({
  selector: 'app-apply-cup-modal',
  templateUrl: './apply-cup-modal.component.html',
  styleUrls: ['./apply-cup-modal.component.css']
})
export class ApplyCupModalComponent implements OnInit {

  constructor(private teamService: TeamResourceService,
    public dialogRef: MatDialogRef<ApplyCupModalComponent>,
    private cupService: CupResourceService,
    public state: AppStateService,
    private dialog: MatDialog,
    private teamState: TeamStateService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  isSearchPressed: boolean = false;
  teamList: Team[];
  searchKey: string;
  listData: MatTableDataSource<any>;
  displayedColumns: string[] = ['name', 'company', 'actions'];

  expandedElement: any | null;

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  organiserNameToAdd: string = '';
  organiserNameToDelete: string = '';
  cupData: any = this.data.cup;


  ngOnInit() {
    this.getData();
  }

  getData() {
    this.teamService.getAllTeamsByLeaderUsingGET().subscribe(
      teamlist => {
        this.teamList = teamlist;
         const array = teamlist.map(
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

  applyTeam(team: Team) {
    this.cupService.applyTeamUsingPOST(this.data.cup.name, team.name).subscribe(
      (data: any) => {

      }
    );
    this.dialogRef.close();
  }

  addOrganizer() {
    this.cupService.addOrganizerUsingPOST(this.data.cup.name, this.organiserNameToAdd).subscribe(
      (data: any) => {

      }
    );
    this.dialogRef.close();
  }

  deleteOrganizer(member: any, cup: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '40%';
    dialogConfig.data = {member: this.organiserNameToDelete, cup: this.cupData};
    this.dialog.open(DeleteOrganizerConfirmComponent, dialogConfig).afterClosed().subscribe(
      result => {
        this.getData();
      }
    );
  }

  exit() {
    this.dialogRef.close(ApplyCupModalComponent);
  }

}
