import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { UserForm } from '../interfaces/interfaces';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css',
})
export class SignUpComponent {
  constructor(private authService: AuthService, private router: Router) {}
  model: UserForm = {
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  };

  onSubmit(form: NgForm) {
    if (this.model.password != this.model.confirmPassword) {
      alert('Passwords do not match');
      return;
    }
    if (form.valid) {
      this.authService.signUp(this.model);
    } else {
      return;
    }
  }
}
