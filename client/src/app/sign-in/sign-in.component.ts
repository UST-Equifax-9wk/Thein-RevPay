import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Credential } from '../interfaces/interfaces';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css',
})
export class SignInComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) {}
  model: Credential = {
    username: '',
    password: '',
  };
  authorized = -1;

  message: string = '';
  onSubmit(form: NgForm) {
    let that = this;
    if (form.valid) {
      this.authService.signIn(this.model).subscribe({
        next(value) {
          if (value.status === 200 && value.body) {
            sessionStorage.setItem('isLoggedIn', 'true');
            sessionStorage.setItem('username', value.body.username);
            sessionStorage.setItem('id', value.body.id);
            sessionStorage.setItem('email', value.body.email);
            that.authService.principal.username = value.body.username;
            that.authService.principal.id = value.body.id;
            that.authService.principal.email = value.body.email;
            that.router.navigate(['/dashboard']);
          }
        },
        error(error) {
          that.message = "User doen't exist.";
          throw error;
        },
      });
    } else {
      return;
    }
  }

  ngOnInit(): void {
    let authenticated = this.authService.authenticate();
    if (authenticated) {
      this.router.navigate(['/dashboard']);
    } else {
      return;
    }
  }

  closeBanner() {
    this.authorized = 1;
  }
}
