import { Component } from '@angular/core';
import { UserService } from './shared/service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private userService: UserService) {
   // userService.findAll().subscribe(console.log);
   //write the auth here
  }

}
