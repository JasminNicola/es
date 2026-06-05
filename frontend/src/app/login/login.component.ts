import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

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

  constructor(private http: HttpClient) {}

/**  onLogin(username: string, password: string) {
    this.http.post(
      'http://localhost:8080/api/login?username=',
      {username: username, password: password})
      .subscribe({
        next: (response: any) => {
          console.log('Login successful:', response);
          this.response=response.message;
          // Handle successful login, e.g., navigate to a different page
        },
        error: (error) => {
          console.error('Login failed:', error);
          this.errorMessage = 'Login failed. Please check your credentials.';
        }
    });

*/

onLogin(username: string, password: string) {
  this.http.post(
    'http://localhost:8080/api/login',  // ← saubere URL, kein Query-Parameter
    { username: username, password: password }
  ).subscribe({
    next: (response: any) => {
      console.log('Login successful:', response);
      this.response = response.message;
    },
    error: (error) => {
      console.error('Login failed:', error);
      this.errorMessage = 'Login failed. Please check your credentials.';
    }
  });
}





}
