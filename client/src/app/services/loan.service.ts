import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class LoanService {
  constructor(private http: HttpClient) {}

  applyForLoan(body: object) {
    let url: string = `${BASE_URL}/loans`;
    let options: object = { observe: 'response', withCredentials: true };
    return this.http.post(url, body, options);
  }
}
