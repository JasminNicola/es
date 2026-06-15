import {Component, inject} from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../auth.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  authService =  inject(AuthService);
  constructor(private router: Router) {}


  goToDashboard() {

    this.router.navigate(['/dashboard']);
  }
}

