import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EventItem } from '../models/eventItem';



@Injectable({
  providedIn: 'root'
})
export class EventService {
 /* private readonly base = 'http://localhost:8080/api/events';
  http: any;

  constructor() { http: HttpClient;}


  getAll(): Observable<EventItem[]> {
    return this.http.get<EventItem[]>(this.base);
  }

  create(event: EventItem): Observable<EventItem> {
    return this.http.post<EventItem>(this.base, event);
  }

  update(id: number, event: EventItem): Observable<EventItem> {
    return this.http.put<EventItem>(`${this.base}/${id}`, event);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/${id}`);
  }*/

}
