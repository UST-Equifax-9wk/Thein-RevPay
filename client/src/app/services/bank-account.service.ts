import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BankAccountRequestBody, Principal } from '../interfaces/interfaces';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class BankAccountService {
  constructor(private authService: AuthService, private http: HttpClient) {}

  fetchBankAccounts(): Observable<object> {
    let principalId: string = (this.authService.principal as Principal).id;
    let url: string = `${BASE_URL}/users/${principalId}/bank-accounts`;
    let options: object = { withCredentials: true };
    return this.http.get(url, options);
  }

  fetchBankAccount(bankAccountId: string): Observable<object> {
    let principalId: string = (this.authService.principal as Principal).id;
    let url: string = `${BASE_URL}/users/${principalId}/bank-accounts/${bankAccountId}`;
    let options: object = { withCredentials: true };
    return this.http.get(url, options);
  }

  openNewBankAccount(body: BankAccountRequestBody): Observable<object> {
    let url: string = `${BASE_URL}/users/${body.accountHolderId}/bank-accounts`;
    let options: object = { withCredentials: true };
    return this.http.post(url, body, options);
  }
}
