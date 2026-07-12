import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from "@angular/common/http";

// ⭐ Correct EventType Enum (matches backend)
export enum EventType {
  HALF_DAY = 'HALF_DAY',
  FULL_DAY = 'FULL_DAY',
  MULTIPLE_DAYS = 'MULTIPLE_DAYS'
}

// ⭐ Catering Enum
export enum CateringType {
  NONE = 'NONE',
  BASIC = 'BASIC',
  FULL = 'FULL',
  PREMIUM = 'PREMIUM'
}

// ⭐ Bucket Typen
type BucketKey = 'bucket1' | 'bucket2' | 'bucket3' | 'bucket4';

export interface EventRequest {
  name: string;
  description: string;
  date: string;
  participants: number | null;
  location: string;
  internationalGuests: boolean;
  eventType: EventType;
  catering: CateringType;
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

  // ⭐ Antworten
  type = "";
  participants = "";
  international = "";
  participantsType = "";
  management = "";
  location = "";
  services: string[] = [];
  breakouts = "";
  purpose = "";

  // ⭐ Backend Request
  eventRequest: EventRequest = this.emptyForm();

  CateringType = CateringType;
  EventType = EventType;

  isLoading = false;
  successMessage = "";
  errorMessage = "";
  showFinalSubmit = false;

  // ⭐ Bucket-Routen
  buckets: Record<BucketKey, string> = {
    bucket1: '/bucket1',
    bucket2: '/bucket2',
    bucket3: '/bucket3',
    bucket4: '/bucket4'
  };

  constructor(private http: HttpClient, private router: Router) {}

  // ⭐ Participants Mapping
  private mapParticipants(): number {
    switch (this.participants) {
      case "small": return 20;
      case "medium": return 99;
      case "large": return 199;
      case "xlarge": return 200;
      default: return 0;
    }
  }

  // ⭐ Multi-Select
  toggleService(value: string, checked: boolean) {
    if (checked) {
      if (!this.services.includes(value)) this.services.push(value);
    } else {
      this.services = this.services.filter(v => v !== value);
    }
  }

  onServiceChange(event: Event, value: string) {
    const input = event.target as HTMLInputElement;
    this.toggleService(value, input.checked);
  }
  showPopup = false;
  popupMessage = "";

  // ⭐ Step 1: Backend Submit
  sendToBackend(): void {
    this.isLoading = true;
    this.successMessage = '';
    this.errorMessage = '';

    this.eventRequest.participants = this.mapParticipants();
    this.eventRequest.internationalGuests = this.international === "yes";
    this.eventRequest.location = this.location;
    this.eventRequest.eventType = this.eventRequest.eventType;
    this.eventRequest.description = this.purpose;
    this.eventRequest.specialNotes = this.services.join(', ');

    this.http.post('http://localhost:8080/api/events/create-request', this.eventRequest)
      .subscribe({
        next: (response: any) => {
          this.isLoading = false;

          this.popupMessage = response.message ?? 'Event request submitted successfully!';
          this.showPopup = true;   // ⭐ Popup öffnen
        },
        error: (error) => {
          this.isLoading = false;

          this.popupMessage = 'Error submitting the request. Please try again.';
          this.showPopup = true;   // ⭐ Popup trotzdem öffnen
        }
      });
  }
  onPopupContinue(): void {
    this.showPopup = false;   // Popup schließen
    this.onSubmit();          // ⭐ Bucket‑Routing starten
  }


  // ⭐ Step 2: Bucket Routing
  onFinalSubmit(): void {
    this.showFinalSubmit = false;
    this.onSubmit();
  }

  // ⭐ Bucket Logik
  onSubmit(): void {

    // Bucket 4
    if (this.type === "celebration" || this.purpose === "celebration") {
      this.router.navigate(['/bucket4'], { state: this.collectState() })
      return;
    }

    // Bucket 3 – HR Training
    if (this.type === "training" || this.purpose === "training") {
      this.router.navigate(['/bucket3'], { state: this.collectState() });
      return;
    }

    // Bucket 3 – Complex
    const flagshipServices = [
      "venue", "av", "registration", "branding",
      "streaming", "moderator", "communication", "translation"
    ];

    const isFlagshipOrComplex =
      this.management !== "none" ||
      this.participants === "large" ||
      this.participants === "xlarge" ||
      this.location === "external" ||
      this.breakouts === "true" ||
      this.services.some(s => flagshipServices.includes(s));

    if (isFlagshipOrComplex) {
      this.router.navigate(['/bucket3'], { state: this.collectState() });
      return;
    }

    // Bucket 2
    const isFunctional =
      this.type === "event" ||
      this.type === "meeting" ||
      this.participants === "medium" ||
      this.participants === "small";

    if (isFunctional) {
      this.router.navigate(['/bucket2'], { state: this.collectState() });
      return;
    }

    // Bucket 1
    this.router.navigate(['/bucket1'], { state: this.collectState() });

  }

  // ⭐ Router-State Collector (vollständig)
  private collectState() {
    return {
      type: this.type,
      participants: this.participants,
      international: this.international,
      participantsType: this.participantsType,
      management: this.management,
      location: this.location,
      services: this.services,
      breakouts: this.breakouts,
      purpose: this.purpose,
      eventType: this.eventRequest.eventType,
      catering: this.eventRequest.catering
    };
  }

  // ⭐ Navigation
  goTo(bucket: BucketKey) {
    this.router.navigate([this.buckets[bucket]]);
  }

  // ⭐ Reset
  onReset(): void {
    this.eventRequest = this.emptyForm();
    this.successMessage = '';
    this.errorMessage = '';
  }

  // ⭐ Leeres Formular
  private emptyForm(): EventRequest {
    return {
      name: '',
      description: '',
      date: '',
      participants: null,
      location: '',
      internationalGuests: false,
      eventType: EventType.HALF_DAY,
      catering: CateringType.NONE,
      specialNotes: ''
    };
  }
}
