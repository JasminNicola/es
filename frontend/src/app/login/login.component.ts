import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username = '';
  password = '';
  errorMessage = '';
  response:string='';

  constructor(private http: HttpClient, private router: Router) {}

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
        // Handle successful login, e.g., navigate to a different page
      },
      error: (error) => {
        console.error('Login failed:', error);
        this.errorMessage = 'Login failed. Please check your credentials.';
      }
    });
}
  goToDashboard() {

    this.router.navigate(['/dashboard']);
  }

  logout() {
    this.http.post(
      'http://localhost:8080/api/logout',{})
      .subscribe({
        next: (response: any) => {
          console.log('Logout successful:', response);
          this.response = response.message;
          // Handle successful login, e.g., navigate to a different page
        },
        error: (error) => {
          console.error('Login failed:', error);
          this.errorMessage = 'Logout failed. Please try again.';
        }
      });
  }
}
