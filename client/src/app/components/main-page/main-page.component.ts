import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(public http: HttpClient) {}

  public ping() {
    this.http.get('https://example.com/api/things')
      .subscribe(
        data => console.log(data),
        err => console.log(err)
      );
  }

  ngOnInit() {
    this.ping();
  }

}
