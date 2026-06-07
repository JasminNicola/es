import {Component, inject} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {RouterOutlet}  from "@angular/router";
import { CommonModule } from '@angular/common';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterOutlet,],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  username = '';
  password = '';
  errorMessage = '';
  response:string='';
  authService =  inject(AuthService);

  // Wir injizieren den HttpClient direkt in die Komponente
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
          this.authService.login();
        },
        error: (error) => {
          console.error('Login failed:', error);
          this.errorMessage = 'Login failed. Please check your credentials.';

        }
      });
  }

  onlogout() {
    this.http.post(
      'http://localhost:8080/api/logout',{})
      .subscribe({
        next: (response: any) => {
          console.log('Logout successful:', response);
          this.response = response.message;
          this.authService.logout();
        },
        error: (error) => {
          console.error('Login failed:', error);
          this.errorMessage = 'Logout failed. Please try again.';
        }
      });

    this.router.navigate(['/home']);
  }

  protected readonly AuthService = AuthService;
}
