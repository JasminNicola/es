import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-bucket3',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './bucket3.component.html',
  styleUrls: ['./bucket3.component.css']
})
export class Bucket3Component {

  type: string = "";
  participants: string = "";
  international: string = "";
  participantsType: string = "";
  management: string = "";
  location: string = "";
  services: string[] = [];
  breakouts: string = "";
  purpose: string = "";

  constructor(private router: Router) {

    const data = this.router.getCurrentNavigation()?.extras.state;

    if (data) {
      this.type = data['type'];
      this.participants = data['participants'];
      this.international = data['international'];
      this.participantsType = data['participantsType'];
      this.management = data['management'];
      this.location = data['location'];
      this.services = data['services'];
      this.breakouts = data['breakouts'];
      this.purpose = data['purpose'];
    }
  }

  logout() {}

  getComplexReasons(): string[] {
    const reasons: string[] = [];

    if (this.management !== "none") {
      reasons.push("Executive Board / Leadership Team / Merck Family is attending");
    }

    if (this.participants === "large" || this.participants === "xlarge") {
      reasons.push("More than 199 participants");
    }

    if (this.location === "external" || this.breakouts === "true") {
      reasons.push("External venue or multiple rooms / breakout sessions");
    }

    if (this.services.includes("av") ||
      this.services.includes("streaming") ||
      this.services.includes("translation")) {
      reasons.push("Technical requirements (AV, streaming, stage, translation)");
    }

    if (this.services.includes("branding") ||
      this.services.includes("communication") ||
      this.services.includes("moderator")) {
      reasons.push("Branding, communication or moderation required");
    }

    if (this.international === "yes") {
      reasons.push("International guests require additional coordination");
    }

    if (this.services.length > 3) {
      reasons.push("Complex logistics or multiple service providers involved");
    }

    return reasons;
  }
}
