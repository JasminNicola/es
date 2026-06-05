import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {RouterOutlet}  from "@angular/router";
import { CommonModule } from '@angular/common';
import {HttpClient} from "@angular/common/http";
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  username = '';
  password = '';
  errorMessage = '';

  // Wir injizieren den HttpClient direkt in die Komponente
  constructor(private http: HttpClient, private router: Router) {}

  onLogin() {
    const loginDaten = { username: this.username, password: this.password };
    const apiUrl = 'http://localhost:8080/api/login';

    // Sende Benutzernamen und Passwort direkt an Spring Boot
    this.http.post(apiUrl, loginDaten).subscribe({
      next: (userFromBackend: any) => {
        alert('Login erfolgreich!');
        // Speicher den User direkt im Browser, um zu wissen, wer angemeldet ist
        localStorage.setItem('currentUser', JSON.stringify(userFromBackend));

        // Hier leiten wir später zur Event-Übersicht weiter:
        // this.router.navigate(['/events']);
      },
      error: (err) => {
        // Falls Spring Boot ein 401 (Unauthorized) zurückgibt
        this.errorMessage = 'Falscher Username oder Passwort!';
      }
    });
  }
}
