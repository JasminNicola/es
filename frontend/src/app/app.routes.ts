import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import {EventRequestComponent} from "./event-request/event-request.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {Bucket1Component} from "./bucket1/bucket1.component";
import {Bucket2Component} from "./bucket2/bucket2.component";
import {Bucket3Component} from "./bucket3/bucket3.component";
import {Bucket4Component} from "./bucket4/bucket4.component";

export const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent
  }
  /**,
  {
    path: 'login',
    component: LoginComponent
  }*/
  ,
  {
    path: 'event-request',
    component: EventRequestComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  { path: 'bucket1', component: Bucket1Component },
  { path: 'bucket2', component: Bucket2Component },
  { path: 'bucket3', component: Bucket3Component },
  { path: 'bucket4', component: Bucket4Component },
];
