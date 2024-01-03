import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Card, CardResponse } from '../interfaces/interfaces';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants';

@Injectable({
  providedIn: 'root',
})
export class CardService {
  constructor(private http: HttpClient) {}
  addCard(body: Card): Observable<HttpResponse<CardResponse>> {
    let url = BASE_URL + '/cards';
    let options: object = {
      observe: 'response',
    };
    return this.http.post<HttpResponse<CardResponse>>(url, body, options);
  }
  deleteCard(cardId: string): Observable<CardResponse> {
    let url = BASE_URL + `/cards/${cardId}`;
    let options: object = {};
    return this.http.delete<CardResponse>(url, options);
  }
}
