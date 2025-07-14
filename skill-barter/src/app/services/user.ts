import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { isPlatformBrowser } from '@angular/common';
import { Observable } from 'rxjs';
import { UserProfile } from '../models/user-profile';
import { UserProfileUpdate } from '../models/user-profile-update';

@Injectable({
  providedIn: 'root'
})
export class User {
  private baseUrl = 'http://localhost:8080/skill-barter';
  private userKey = 'skillBarterUser';
  private user: any = null;
  private isBrowser: boolean;

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) platformId: Object
  ) {
    this.isBrowser = isPlatformBrowser(platformId);

    if (this.isBrowser) {
      const stored = localStorage.getItem(this.userKey);
      if (stored) {
        this.user = JSON.parse(stored);
      }
    }
  }

  // Save user after login
  setUser(userData: any): void {
    this.user = userData;
    if (this.isBrowser) {
      localStorage.setItem(this.userKey, JSON.stringify(userData));
    }
  }

  // Get current user
  getUser(): any {
    if (!this.user && typeof window !== 'undefined') {
      const stored = localStorage.getItem(this.userKey);
      if (stored) {
        this.user = JSON.parse(stored);
      }
    }
    return this.user;
  }


  // Is logged in
  isLoggedIn(): boolean {
    return !!this.getUser();
  }

  // Clear user data
  clearUser(): void {
    this.user = null;
    if (this.isBrowser) {
      localStorage.removeItem(this.userKey);
    }
  }

  logout(): void {
    this.clearUser();
    if (this.isBrowser) {
      localStorage.removeItem('rememberedEmail');
    }
  }



  // API calls
  checkConnection(): Observable<string> {
    return this.http.get(`${this.baseUrl}/connect`, { responseType: 'text' });
  }

  registerUser(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/user/signup`, userData);
  }

  loginUser(credentials: { email: string; password: string }) {
    return this.http.post(`${this.baseUrl}/user/login`, credentials);
  }

  getUserProfile(userId: number): Observable<UserProfile> {
    return this.http.get<UserProfile>(`${this.baseUrl}/user/profile/${userId}`);
  }
  updateUserProfile(userId: number, data: UserProfileUpdate) {
    return this.http.put(`${this.baseUrl}/user/updateProfile/${userId}`, data);
  }

}
