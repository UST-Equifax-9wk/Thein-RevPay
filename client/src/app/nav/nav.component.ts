import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent {
  constructor(private authService: AuthService, private router: Router) {}
  currentUser = {
    username: this.authService.principal.username,
  };
  openedMenu = false;
  toggleMenu() {
    this.openedMenu = !this.openedMenu;
  }
  openedMobileMenu = false;
  toggleMobileMenu() {
    this.openedMobileMenu = !this.openedMobileMenu;
  }
  signOut() {
    this.authService.signOut();
    this.router.navigate(['/sign-in']);
  }
}
