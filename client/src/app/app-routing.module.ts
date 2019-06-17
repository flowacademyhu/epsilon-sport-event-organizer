import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';

import { MainPageComponent } from './components/main-page/main-page.component';
import { RoleGuard } from './shared/guard/role.guard';
import { TeamComponent } from './components/team/team.component';


const routes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'login', component: LoginComponent},
<<<<<<< HEAD
  {path: 'oauth2/redirect', component: MainPageComponent},
=======
  {path: 'oauth2/redirect', component: ProfileComponent},
>>>>>>> 3e796cb182a11adad9b823b2a14bb12d0030ed19
  {path: 'profile', component: ProfileComponent, canActivate: [RoleGuard]},
  {path: 'team', component: TeamComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
