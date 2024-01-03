import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  AdminResponse,
  Credential,
  Principal,
  Registration,
  User,
  UserReponse,
} from '../interfaces/interfaces';
import { UserForm } from '../interfaces/interfaces';
import { RemoteService } from './remote.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private remoteService: RemoteService, private router: Router) {}

  private _principal: Principal = {
    username: sessionStorage.getItem('username') || '',
    id: sessionStorage.getItem('id') || '',
    email: sessionStorage.getItem('email') || '',
  };

  get principal(): Principal {
    return this._principal;
  }

  set principal(principal: Principal) {
    this._principal = this.principal;
  }

  signUp(userForm: UserForm): void {
    let credential: Credential = {
      username: userForm.username,
      password: userForm.password,
    };
    let user: User = {
      username: userForm.username,
      firstName: userForm.firstName,
      lastName: userForm.lastName,
      email: userForm.email,
    };
    let body: Registration = {
      credential,
      user,
    };
    let that = this;
    this.remoteService.signUp(body).subscribe({
      next(value) {
        if (value.status === 200 && value.body) {
          sessionStorage.setItem('isLoggedIn', 'true');
          sessionStorage.setItem('username', value.body.username);
          sessionStorage.setItem('id', value.body.id);
          sessionStorage.setItem('email', value.body.email);
          that.principal.username = value.body.username;
          that.principal.id = value.body.id;
          that.principal.email = value.body.email;
          that.router.navigate(['/dashboard']);
        }
      },
      error(error) {
        alert('An error has occurred');
      },
    });
  }

  signIn(credential: Credential): Observable<HttpResponse<UserReponse>> {
    return this.remoteService.signIn(credential);
  }

  signOut() {
    sessionStorage.setItem('isLoggedIn', 'false');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('id');
    sessionStorage.removeItem('email');
  }

  adminSignIn(credential: Credential): Observable<HttpResponse<AdminResponse>> {
    return this.remoteService.adminSignIn(credential);
  }

  authenticate(): boolean {
    let isLoggedIn = sessionStorage.getItem('isLoggedIn');
    return isLoggedIn === 'true' ? true : false;
  }
}
