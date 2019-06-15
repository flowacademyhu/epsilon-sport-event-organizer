import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private activateRoute: ActivatedRoute, private user: UserService) { }

  userDatas: any;

  ngOnInit() {

    this.user.getUser().subscribe(
      result => {
        console.log('Profile result:', result);
        this.userDatas = result;
      }
    );

  }

}
