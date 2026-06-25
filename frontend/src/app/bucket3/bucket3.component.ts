import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {SidebarRightService} from "../layout/sidebar-right/sidebar-right.service";

@Component({
  selector: 'app-bucket3',
  standalone: true,
  imports: [],
  templateUrl: './bucket3.component.html',
  styleUrl: './bucket3.component.css'
})
export class Bucket3Component {
  constructor(private router: Router, private http: HttpClient) {}

  logout() {
    this.http.post('http://localhost:8080/api/logout', {})
      .subscribe({
        next: () => {
          this.router.navigate(['/login']);
        }
      });
  }
}
