import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-logged-in',
  templateUrl: './logged-in.component.html',
  styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

  constructor(
    private activateRoute: ActivatedRoute,
    private user: UserService
  ) { }

  ngOnInit() {
    this.activateRoute.queryParams.subscribe(params => {
      localStorage.setItem('token', params['token']);
    });

    this.user.getUser().subscribe(
      result => {
        console.log(result);
      }
    );
  }

}
