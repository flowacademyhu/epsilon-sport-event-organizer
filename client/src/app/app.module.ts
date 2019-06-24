import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { HeaderComponent } from './components/header/header.component';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';
import { ProfileComponent } from './components/profile/profile.component';
import { FooterComponent } from './components/footer/footer.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { RequestInterceptorService } from './shared/interceptor/request-interceptor.service';
import { TeamComponent } from './components/team/team.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateTeamModalComponent } from './shared/component/create-team-modal/create-team-modal.component';
import { MatFormFieldModule, MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule, MatIconModule, MatButtonModule } from '@angular/material';
import { AddMemberModalComponent } from './shared/component/add-member-modal/add-member-modal.component';
import { ShowTeamModalComponent } from './shared/component/show-team-modal/show-team-modal.component';
import { ShowCupModalComponent } from './shared/component/show-cup-modal/show-cup-modal.component';
import { CupComponent } from './components/cup/cup.component';
import { KeysPipe } from './shared/pipe/keys.pipe';
import { ApiModule, Configuration, ConfigurationParameters } from './api';
<<<<<<< HEAD
import { CreateCupModalComponent } from './shared/component/create-cup-modal/create-cup-modal.component';
=======
import { DeleteTeamConfirmComponent } from './shared/component/delete-team-confirm/delete-team-confirm.component';
import { DeleteMemberConfirmComponent } from './shared/component/delete-member-confirm/delete-member-confirm.component';
import { DeleteLeaderConfirmComponent } from './shared/component/delete-leader-confirm/delete-leader-confirm.component';
>>>>>>> feature/T40-teamUiRefreshBug

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

export function getConfig(): Configuration {
  return new Configuration(<ConfigurationParameters>{
    apiKeys: {},
    withCredentials: true,
    basePath: 'http://localhost:8080'}
    );
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ProfileComponent,
    MainPageComponent,
    FooterComponent,
    TeamComponent,
    CreateTeamModalComponent,
    AddMemberModalComponent,
    ShowTeamModalComponent,
    ShowCupModalComponent,
    CupComponent,
    KeysPipe,
<<<<<<< HEAD
    CreateCupModalComponent
=======
    DeleteTeamConfirmComponent,
    DeleteMemberConfirmComponent,
    DeleteLeaderConfirmComponent
>>>>>>> feature/T40-teamUiRefreshBug
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
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatButtonModule,
    BrowserAnimationsModule,
    ApiModule,
    ApiModule.forRoot(getConfig),
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
  exports: [
    MatDialogModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule
  ],
  entryComponents: [
    CreateTeamModalComponent,
    ShowTeamModalComponent,
    AddMemberModalComponent,
<<<<<<< HEAD
    CreateCupModalComponent,
    ShowCupModalComponent
=======
    DeleteTeamConfirmComponent,
    DeleteLeaderConfirmComponent,
    DeleteMemberConfirmComponent
>>>>>>> feature/T40-teamUiRefreshBug
  ]
})
export class AppModule { }
