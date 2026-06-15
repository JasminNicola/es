import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {AuthService} from "../auth.service";

export type EventType = 'HALF_DAY' | 'FULL_DAY' | 'MULTI_DAY';

export interface EventRequest {
  name: string;
  description: string;
  date: string;
  participants: number | null;
  location: string;
  internationalGuests: boolean;
  eventType: EventType | '';
  catering: string;
  specialNotes: string;
}

@Component({
  selector: 'app-event-request',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './event-request.component.html',
  styleUrl: './event-request.component.css'
})



export class EventRequestComponent {

  isLoading = false;
  successMessage = '';
  errorMessage = '';

  eventRequest: EventRequest = this.emptyForm();

  cateringOptions = [
    { value: 'NONE',       icon: '🚫', label: 'Kein Catering' },
    { value: 'BEVERAGES',  icon: '☕', label: 'Getränke' },
    { value: 'SNACKS',     icon: '🥐', label: 'Snacks' },
    { value: 'FULL_MEALS', icon: '🍽️', label: 'Vollverpflegung' },
  ];

  constructor(private http: HttpClient, private router:Router) {}

  onSubmit(): void {
    this.isLoading = true;
    this.successMessage = '';
    this.errorMessage = '';

    this.http.post('http://localhost:8080/api/events/create-request', this.eventRequest)
      .subscribe({
        next: (response: any) => {
          this.isLoading = false;
          this.successMessage = response.message ?? 'Eventanfrage erfolgreich übermittelt!';
          this.eventRequest = this.emptyForm();
        },
        error: (error) => {
          this.isLoading = false;
          this.errorMessage = 'Fehler beim Senden der Anfrage. Bitte versuchen Sie es erneut.';
          console.error('EventItem request failed:', error);
        }
      });
  }

  onReset(): void {
    this.eventRequest = this.emptyForm();
    this.successMessage = '';
    this.errorMessage = '';
  }

  private emptyForm(): EventRequest {
    return {
      name: '',
      description: '',
      date: '',
      participants: null,
      location: '',
      internationalGuests: false,
      eventType: '',
      catering: 'NONE',
      specialNotes: ''
    };
  }

  goToLogin() {

    this.router.navigate(['/login']);
  }

    protected readonly AuthService = AuthService;
}
