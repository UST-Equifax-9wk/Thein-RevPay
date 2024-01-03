import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}
  principal: object = {
    id: '',
  };
  setPrincipal(user: object) {
    this.principal = user;
    console.log('Principal', this.principal);
  }
  getUserByUsernameOrEmail(usernameOrEmail: string): Observable<object> {
    let username: string = '';
    let email: string = '';
    if (usernameOrEmail.includes('@')) {
      email = usernameOrEmail;
    } else {
      username = usernameOrEmail;
    }
    let url: string = `${BASE_URL}/users?username=${username}&email=${email}`;
    return this.http.get(url);
  }
}
