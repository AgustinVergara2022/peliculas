
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private base = 'http://localhost:8080/auth';

  login(username: string, password: string) {
    return this.http.post<{token:string, username:string}>(`${this.base}/login`, {username, password})
      .pipe(tap(res => localStorage.setItem('token', res.token)));
  }

  register(username: string, password: string) {
    return this.http.post(`${this.base}/register`, {username, password});
  }

  logout() { localStorage.removeItem('token'); }
  isLoggedIn() { return !!localStorage.getItem('token'); }
  getToken() { return localStorage.getItem('token'); }
}
