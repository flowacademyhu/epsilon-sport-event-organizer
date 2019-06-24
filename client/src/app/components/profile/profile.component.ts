import { Component, OnInit, ViewChild } from '@angular/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';
import { User, TeamControllerService, CupControllerService } from 'src/app/api';
import { MatTableDataSource, MatSort, MatPaginator, MatDialog, MatDialogConfig, } from '@angular/material';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private state: AppStateService,
              private teamService: TeamControllerService,
              private cupService: CupControllerService) { }

  userDatas: User;

  teamsIAmLeaderIn: any[];
  teamsIAmMemberIn: any[];
  listCupData: MatTableDataSource<any>;
  displayedColumns: string[] = ['name',  'company', 'place', 'registrationEndDate', 'eventDate'];

  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {

    this.userDatas = this.state.user;
    console.log(this.userDatas);

    this.teamService.getAllTeamsByLeaderUsingGET().subscribe(
      teams => {
        this.teamsIAmLeaderIn = teams;
        console.log(this.teamsIAmLeaderIn);
      }
    );

    this.teamService.getAllTeamsByMemberUsingGET().subscribe(
      teams => {
        this.teamsIAmMemberIn = teams;
        console.log(this.teamsIAmMemberIn);
      }
    );

   this.cupService.getCupByOrganizerUsingGET().subscribe(
     cuplist => {
       const array = cuplist.map(
         item => {
           return {
             $key: item.name,
             ...item
           };
         }
       );
       console.log(cuplist);
       this.listCupData = new MatTableDataSource(array);
       this.listCupData.sort = this.sort;
       this.listCupData.paginator = this.paginator;
     }
   );
  }

}
