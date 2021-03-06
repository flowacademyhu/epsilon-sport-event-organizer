import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './components/profile/profile.component';

import { MainPageComponent } from './components/main-page/main-page.component';
import { LoggedinGuard } from './shared/guard/loggedin.guard';
import { TeamComponent } from './components/team/team.component';
import { CupComponent } from './components/cup/cup.component';

const routes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'oauth2/redirect', component: MainPageComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [LoggedinGuard]},
  {path: 'team', component: TeamComponent},
  {path: 'cup', component:CupComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
