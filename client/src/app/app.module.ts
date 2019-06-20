import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';
import { LoggedInComponent } from './components/logged-in/logged-in.component';
import { ProfileComponent } from './components/profile/profile.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { RequestInterceptorService } from './shared/interceptor/request-interceptor.service';
import { TeamComponent } from './components/team/team.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateTeamModalComponent } from './shared/component/create-team-modal/create-team-modal.component';
import { MatFormFieldModule, MatInputModule, MatTableModule } from '@angular/material';
import { AddMemberModalComponent } from './shared/component/add-member-modal/add-member-modal.component';
import { ShowTeamModalComponent } from './shared/component/show-team-modal/show-team-modal.component';
import { ShowCupModalComponent } from './shared/component/show-cup-modal/show-cup-modal.component';
import { CupComponent } from './components/cup/cup.component';
import { KeysPipe } from './shared/pipe/keys.pipe';
import { ApiModule } from './api';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    LoggedInComponent,
    ProfileComponent,
    MainPageComponent,
    FooterComponent,
    TeamComponent,
    CreateTeamModalComponent,
    AddMemberModalComponent,
    ShowTeamModalComponent,
    ShowCupModalComponent,
    CupComponent,
    KeysPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbCollapseModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    BrowserAnimationsModule,
    ApiModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
})
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptorService,
      multi: true
    }
],
  bootstrap: [AppComponent],
  exports: [MatDialogModule, MatFormFieldModule, MatTableModule],
  entryComponents: [
    CreateTeamModalComponent,
    ShowTeamModalComponent,
    AddMemberModalComponent
  ]
})
export class AppModule { }
