import { Component, Input} from '@angular/core';

@Component({
  selector: 'app-sidebar-right',
  standalone: true,
  imports: [],
  templateUrl: './sidebar-right.component.html',
  styleUrl: './sidebar-right.component.css'
})
export class SidebarRightComponent {
  @Input() actionLabel: string = '';
  @Input() action!:()=>void;
}
