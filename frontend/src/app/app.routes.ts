import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import {EventRequestComponent} from "./event-request/event-request.component";

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
  ,
  {
    path: 'event-request',
    component: EventRequestComponent
  }
];
