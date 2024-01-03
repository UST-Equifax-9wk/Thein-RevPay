import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Credential } from '../interfaces/interfaces';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-admin-sign-in',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './admin-sign-in.component.html',
  styleUrl: './admin-sign-in.component.css',
})
export class AdminSignInComponent {
  constructor(
    private formBuilder: FormBuilder,
    private adminService: AdminService,
    private router: Router
  ) {}

  form = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });

  onSubmit() {
    let that = this;
    if (this.form.valid) {
      this.adminService.signIn(this.form.value as Credential).subscribe({
        next(value) {
          if (value.status === 200 && value.body) {
            sessionStorage.setItem('isLoggedIn', 'true');
            sessionStorage.setItem('username', value.body.username);
            that.router.navigate(['/admin']);
          }
        },
        error(error) {
          alert('An error has occurred');
        },
      });
    }
  }
}
