import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {CommonModule} from "@angular/common";
import {EventItem} from "./models/eventItem";
import {HttpClient} from "@angular/common/http";
import { SidebarRightService } from "../layout/sidebar-right/sidebar-right.service";





@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [ CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})

export class DashboardComponent{

  constructor(private router: Router, private http: HttpClient,private sidebarRight: SidebarRightService
  ) {
    this.sidebarRight.set("Logout", () => this.logout());
  }


  goToEventRequest() {
    this.router.navigate(['/event-request']);
  }


//allEvents: EventItem[] = this.getUsersEvents();
  // Ihre vollständige Original-Liste
allEvents:EventItem []= [];//this.getUsersEvents()
  /*[
    { id: 1, name: 'Angular Workshop', date: '2026-07-15' ,location: 'Darmstadt',participants:500,internationalGuests: true, eventType: 'FULL_DAY', catering: 'FULL_MEALS', status: 'confirmed', description: 'Ein ganztägiger Workshop zu Angular.', specialNotes: 'Bringen Sie Ihren Laptop mit.'  },
    { id: 2, name: 'TypeScript Meetup', date: '2026-08-22' ,location: 'Darmstadt / Online', participants:85 , internationalGuests: false, eventType: 'HALF_DAY', catering: 'BEVERAGES', status: 'pending', description: 'Ein halbtägiges Meetup zu TypeScript.', specialNotes: 'Kostenlose Getränke.'},
  ];*/



// Die Liste, die im Template gerendert wird
  gefilterteEvents = [... this.allEvents];/* [... this.allEvents];*/

  // Methode für Filter (später einfach erweiterbar für z.B. Datum oder Kategorien)
  filtereEvents(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    const suchbegriff = inputElement.value.toLowerCase();

    this.gefilterteEvents = this.allEvents.filter(evt =>
      evt.name.toLowerCase().includes(suchbegriff)
    );
  }

  // Methode für den Bearbeiten-Button
  eventsItems: any;

  bearbeiteEvent(eventId: number) {
    console.log('Bearbeite Event mit ID:', eventId);
    // Hier öffnen Sie z. B. ein Modal oder leiten den Nutzer weiter
  }

  getUsersEvents() {
    this.http.get<any>('http://localhost:8080/api/events')
      .subscribe({
        next: (response:any) => {
          this.eventsItems = response.response;
          this.allEvents = this.eventsItems; // Alle Events in allEvents speichern

          },
      error: (error) => {
        console.error('Fehler beim Laden der Events:', error);
    }
      });
    return this.allEvents;
  }
  logout() {
    this.http.post('http://localhost:8080/api/logout', {})
      .subscribe({
        next: () => {
          this.sidebarRight.clear();
          this.router.navigate(['/login']);
        }
      });
  }


}
