import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class EmailService {
  constructor(private http: HttpClient) {}

  sendEmail(email: object): Observable<object> {
    let url: string = `${BASE_URL}/emails`;
    return this.http.post(url, email);
  }
}
