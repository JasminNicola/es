import { Component } from '@angular/core';
import { SidebarRightService } from '../sidebar-right/sidebar-right.service';

@Component({
  selector: 'app-sidebar-right',
  standalone: true,
  templateUrl: './sidebar-right.component.html',
  styleUrl: './sidebar-right.component.css'
})
export class SidebarRightComponent {
  constructor(public sidebarRight: SidebarRightService) {}
}
