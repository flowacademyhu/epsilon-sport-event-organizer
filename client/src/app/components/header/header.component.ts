import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AppStateService } from 'src/app/shared/service/app-state.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  title = 'sport-event-organizer';
  constructor(
    private translate: TranslateService,
    private state: AppStateService
    ) {
    translate.setDefaultLang('en');
  }

  toggleNavbar = true;

  switchLanguage(language: string) {
    this.translate.use(language);
  }

  logOut() {
    this.state.user = null;
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  ngOnInit() {
  }

}
