import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-logged-in',
  templateUrl: './logged-in.component.html',
  styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

  constructor(
    private activateRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activateRoute.queryParams.subscribe(params => {
      localStorage.setItem('token', params['token']);
    });
  }

}
