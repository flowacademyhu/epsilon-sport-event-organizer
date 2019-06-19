import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/service/user.service';
import { AppStateService } from 'src/app/shared/service/app-state.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private state: AppStateService, private user: UserService) { }

  userDatas: User;

  ngOnInit() {

    this.userDatas = this.state.user;
  }

}
