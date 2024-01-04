import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../interfaces/interfaces';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  constructor(private http: HttpClient) {}

  sendMoney(transaction: object): Observable<object> {
    let url: string = `${BASE_URL}/transactions/send`;
    let options: object = { withCredentials: true };
    return this.http.post(url, transaction, options);
  }

  requestMoney(transaction: object) {
    let url = `${BASE_URL}/transactions/request`;
    let options: object = { withCredentials: true };
    return this.http.post(url, transaction, options);
  }

  approve(transactionId: string): Observable<HttpResponse<Transaction>> {
    let url: string = `${BASE_URL}/transactions/${transactionId}`;
    let body: object = { id: transactionId };
    let options: object = { observe: 'response', withCredentials: true };
    return this.http.put<HttpResponse<Transaction>>(url, body, options);
  }

  decline(transactionId: string): Observable<HttpResponse<Transaction>> {
    let url: string = `${BASE_URL}/transactions/${transactionId}`;
    let options: object = { obserbe: 'response', withCredentials: true };
    return this.http.delete<HttpResponse<Transaction>>(url, options);
  }
}
