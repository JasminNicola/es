import {Component, inject} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {RouterOutlet}  from "@angular/router";
import { CommonModule } from '@angular/common';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {HeaderComponent} from  "./layout/header/header.component";
import {SidebarComponent} from "./layout/sidebar/sidebar.component";
import {SidebarRightComponent} from "./layout/sidebar-right/sidebar-right.component";
import {ChangeDetectorRef} from "@angular/core";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterOutlet,HeaderComponent, SidebarComponent, SidebarRightComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  //variablen definieren
  rightButtonLabel=''
  rightButtonAction:()=>void =()=>{}

  title = 'Events & Live Communication Support Service';
  username = '';
  password = '';
  errorMessage = '';
  response:string='';
  authService =  inject(AuthService);

  // Wir injizieren den HttpClient direkt in die Komponente
  constructor(private http: HttpClient, private router: Router, private cdr: ChangeDetectorRef) {}

}
