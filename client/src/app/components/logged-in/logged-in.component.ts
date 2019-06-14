import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-logged-in',
  templateUrl: './logged-in.component.html',
  styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {

  constructor(private auth: AuthService) { }

  alma: any;

  ngOnInit() {
        this.auth.user().subscribe(result => {
      this.alma = result;
        }
    );
    console.log(this.alma);
  }

}
