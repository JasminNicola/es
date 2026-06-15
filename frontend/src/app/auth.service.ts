import { Injectable ,signal} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn: any = signal(false);

  login() {this.isLoggedIn.set(true);}
  logout() {this.isLoggedIn.set(false);}
  constructor() { }
}
