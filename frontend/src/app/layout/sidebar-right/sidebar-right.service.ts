import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SidebarRightService {
  label = '';
  action: () => void = () => {};

  set(label: string, action: () => void) {
    this.label = label;
    this.action = action;
  }

  clear() {
    this.label = '';
    this.action = () => {};
  }
}
