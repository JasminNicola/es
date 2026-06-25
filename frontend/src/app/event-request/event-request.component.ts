import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

// -----------------------------
// ⭐ Bucket Typen DEFINIEREN
// -----------------------------
type BucketKey = 'bucket1' | 'bucket2' | 'bucket3' | 'bucket4';

@Component({
  selector: 'app-event-request',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './event-request.component.html',
  styleUrl: './event-request.component.css'
})
export class EventRequestComponent {

  // -----------------------------
  // ⭐ Antworten aus deinem Fragen-Set
  // -----------------------------
  type: string = "";
  participants: string = "";
  participantsType: string = "";
  management: string = "";
  location: string = "";
  services: string[] = [];
  breakouts: string = "";
  purpose: string = "";

  eventRequest = {
    name: '',
    date: '',
    location: ''
  };

  // -----------------------------
  // ⭐ Bucket-Routen
  // -----------------------------
  buckets: Record<BucketKey, string> = {
    bucket1: '/bucket1',
    bucket2: '/bucket2',
    bucket3: '/bucket3',
    bucket4: '/bucket4'
  };

  constructor(private router: Router) {}

  // -----------------------------
  // ⭐ Navigation
  // -----------------------------
  goTo(bucket: BucketKey) {
    this.router.navigate([this.buckets[bucket]]);
  }

  // -----------------------------
  // ⭐ Multi-Select Handler
  // -----------------------------
  toggleService(value: string, checked: boolean) {
    if (checked) {
      if (!this.services.includes(value)) {
        this.services.push(value);
      }
    } else {
      this.services = this.services.filter(v => v !== value);
    }
  }
  onServiceChange(event: Event, value: string) {
    const input = event.target as HTMLInputElement;
    this.toggleService(value, input.checked);
  }

  // -----------------------------
  // ⭐ KOMPLETTE BUCKET-LOGIK
  // -----------------------------
  onSubmit() {

    // C4 – Celebration → Bucket 4
    if (this.type === "celebration" || this.purpose === "celebration") {
      this.goTo("bucket4");
      return;
    }

    // C3 – HR Training → Bucket 3
    if (this.type === "training" || this.purpose === "training") {
      this.goTo("bucket3");
      return;
    }

    // C1 – Flagship / komplexe Events → Bucket 3
    const flagshipServices = [
      "venue",
      "av",
      "registration",
      "branding",
      "streaming",
      "moderator",
      "communication",
      "translation"
    ];

    const isFlagshipOrComplex =
      this.management !== "none" ||
      this.participants === "large" ||
      this.participants === "xlarge" ||
      this.location === "external" ||
      this.breakouts === "true" ||
      this.services.some(s => flagshipServices.includes(s));

    if (isFlagshipOrComplex) {
      this.goTo("bucket3");
      return;
    }

    // C2 – Functional / Cross-Functional → Bucket 2
    const isFunctional =
      this.type === "event" ||
      this.type === "meeting" ||
      this.participants === "medium" ||
      this.participants === "small";

    if (isFunctional) {
      this.goTo("bucket2");
      return;
    }

    // Default → Bucket 1
    this.goTo("bucket1");
  }
}
