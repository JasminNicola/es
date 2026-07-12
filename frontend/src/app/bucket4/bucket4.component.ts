import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bucket4',
  standalone: true,
  templateUrl: './bucket4.component.html',
  styleUrls: ['./bucket4.component.css']
})
export class Bucket4Component {

  constructor(private router: Router) {}

  logout() {
    this.router.navigate(['/login']);
  }

  // ⭐ Dynamic reasons for Bucket 4
  getCelebrationReasons(): string[] {
    const reasons: string[] = [];
    const data = history.state?.data;

    if (!data) return ['Celebration or informal team event'];

    if (data.type === 'celebration' || data.purpose === 'celebration') {
      reasons.push('Event purpose is celebration or social gathering');
    }

    if (data.participants === 'small' || data.participants === 'medium' || data.participants === 'large') {
      reasons.push('Suitable participant size for celebration events');
    }

    if (data.management === 'none') {
      reasons.push('No Executive Board or Merck Family involvement');
    }

    if (data.location === 'meeting_room' || data.location === 'merck_site') {
      reasons.push('Internal location suitable for celebrations');
    }

    if (data.catering === 'BASIC' || data.catering === 'FULL') {
      reasons.push('Catering required for celebration');
    }

    return reasons.length > 0 ? reasons : ['Celebration or informal team event'];
  }
}

