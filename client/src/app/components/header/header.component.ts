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
    private appState: AppStateService
    ) {
    translate.setDefaultLang('en');
  }

  state: any = this.appState;
  toggleNavbar = true;

  switchLanguage(language: string) {
    this.translate.use(language);
  }

  logOut() {
    this.appState.user = null;
    localStorage.removeItem('token');
  }

 /*  klikk() {
    if (this.appState.user) {
    this.appState.user = null;
    } else {
    this.appState.user = { id: 1, name: 'Jozsi'};
    }
  } */

  ngOnInit() {
  }

}
