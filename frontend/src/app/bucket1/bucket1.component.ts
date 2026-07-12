import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bucket1',
  standalone: true,
  templateUrl: './bucket1.component.html',
  styleUrls: ['./bucket1.component.css']
})
export class Bucket1Component {

  constructor(private router: Router) {}

  logout() {
    this.router.navigate(['/login']);
  }

  // ⭐ Dynamic reasons for Bucket 1
  getSelfServiceReasons(): string[] {
    const reasons: string[] = [];

    const data = history.state?.data;

    if (!data) return ['Low complexity meeting'];

    if (data.participants === 'small' || data.participants === 'medium') {
      reasons.push('Low number of participants');
    }

    if (data.management === 'none') {
      reasons.push('No Executive Board or Merck Family involvement');
    }

    if (data.location === 'meeting_room') {
      reasons.push('Internal meeting room on Merck premises');
    }

    if (!data.services || data.services.length === 0) {
      reasons.push('No additional services required');
    }

    if (data.catering === 'NONE' || data.catering === 'BASIC') {
      reasons.push('Only drinks or basic catering needed');
    }

    return reasons.length > 0 ? reasons : ['Low complexity meeting'];
  }
}


