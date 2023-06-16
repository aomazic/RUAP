import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';
import {Input} from "./Input";

@Injectable({
  providedIn: 'root'
})
export class InputService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getMLResponse(input: Input): Observable<string> {
    const url = `${this.apiUrl}/getML`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<string>(url, input, { headers });
  }

}
