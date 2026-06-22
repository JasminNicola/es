import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import {Router} from "@angular/router";
import {AppComponent} from "../app.component";
import {ChangeDetectorRef} from "@angular/core";
import {SidebarRightService} from "../layout/sidebar-right/sidebar-right.service";
import {SidebarRightComponent} from "../layout/sidebar-right/sidebar-right.component";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, SidebarRightComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username = '';
  password = '';
  errorMessage = '';
  response:string='';
  loggedIn = false;

  constructor(private http: HttpClient, private router: Router, private app: AppComponent, private cdr: ChangeDetectorRef, private SidebarRight: SidebarRightService) {}

onLogin(username: string, password: string) {
    this.errorMessage='';
    this.response='';
  this.http.post(
    'http://localhost:8080/api/login?username=',
    {username: username, password: password})
    .subscribe({
      next: (response: any) => {
        console.log('Login successful:', response);
        this.response = response.message;
        this.loggedIn = true;
        this.SidebarRight.set('Logout', () => this.logout());
        this.cdr.detectChanges();
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Login failed:', error);
        this.errorMessage = 'Login failed. Please check your credentials.';

      }
    });

  }

  logout() {
    this.http.post(
      'http://localhost:8080/api/logout',{})
      .subscribe({
        next: (response: any) => {
          console.log('Logout successful:', response);
          this.response = response.message;
          // Handle successful login, e.g., navigate to a different page
          this.loggedIn = false;
        },
        error: (error) => {
          console.error('Login failed:', error);
          this.errorMessage = 'Logout failed. Please try again.';
        }
      });
    this.SidebarRight.clear();

  }

  goToDashboard() {
    this.router.navigate(['/dashboard']);
  }

}
