import { Injectable } from '@angular/core';
import {
  AdminResponse,
  Credential,
  LoanResponse,
} from '../interfaces/interfaces';
import { RemoteService } from './remote.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private remoteService: RemoteService, private http: HttpClient) {}

  signIn(credential: Credential) {
    return this.remoteService.adminSignIn(credential);
  }

  authenticate(): boolean {
    let isLoggedIn = sessionStorage.getItem('isLoggedIn');
    return isLoggedIn === 'true' ? true : false;
  }

  getAdminByUsername(username: string): Observable<AdminResponse> {
    let url: string = `${BASE_URL}/admins/${username}`;
    let options: object = { withCredentials: true };
    return this.http.get<AdminResponse>(url, options);
  }
  approveLoan(loan: LoanResponse) {
    let url: string = `${BASE_URL}/loans/${loan.id}`;
    let options: object = { withCredentials: true };
    return this.http.post(url, loan, options);
  }
}
