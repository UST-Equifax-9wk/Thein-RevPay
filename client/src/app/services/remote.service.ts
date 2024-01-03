import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  Registration,
  Credential,
  UserReponse,
  AdminResponse,
} from '../interfaces/interfaces';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class RemoteService {
  constructor(private http: HttpClient) {}

  signUp(body: Registration): Observable<HttpResponse<UserReponse>> {
    let url: string = BASE_URL + '/sign-up';
    let options: object = { observe: 'response', withCredentials: true };
    return this.http.post<HttpResponse<UserReponse>>(url, body, options);
  }

  signIn(body: Credential): Observable<HttpResponse<UserReponse>> {
    let url: string = BASE_URL + '/sign-in';
    let options: object = { observe: 'response', withCredentials: true };
    return this.http.post<HttpResponse<UserReponse>>(url, body, options);
  }

  adminSignIn(body: Credential): Observable<HttpResponse<AdminResponse>> {
    let url: string = BASE_URL + '/admin-sign-in';
    let options: object = { observe: 'response', withCredentials: true };
    return this.http.post<HttpResponse<AdminResponse>>(url, body, options);
  }
}
