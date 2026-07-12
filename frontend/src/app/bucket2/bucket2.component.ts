import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bucket2',
  standalone:true,
  templateUrl: './bucket2.component.html',
  styleUrls: ['./bucket2.component.css']
})
export class Bucket2Component {

  constructor(private router: Router) {}

  logout() {
    this.router.navigate(['/login']);
  }

  // ⭐ Dynamic reasons for Bucket 2
  getFunctionalReasons(): string[] {
    const reasons: string[] = [];
    const data = history.state?.data;

    if (!data) return ['Medium complexity functional meeting'];

    if (data.participants === 'small' || data.participants === 'medium') {
      reasons.push('Medium number of participants (20–99)');
    }

    if (data.management === 'none') {
      reasons.push('No Executive Board or Merck Family involvement');
    }

    if (data.type === 'meeting' || data.type === 'event') {
      reasons.push('Functional or cross‑functional meeting');
    }

    if (data.location === 'meeting_room' || data.location === 'merck_site') {
      reasons.push('Internal location on Merck premises');
    }

    if (data.catering === 'BASIC' || data.catering === 'FULL') {
      reasons.push('Basic or full catering required');
    }

    if (data.services && data.services.length > 0) {
      reasons.push('Some support services requested');
    }

    return reasons.length > 0 ? reasons : ['Functional meeting'];
  }
}
