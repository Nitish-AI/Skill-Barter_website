import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SkillSearchParams } from '../models/skill-search-params';

@Injectable({
  providedIn: 'root'
})
export class Skill {
  private baseUrl = 'http://localhost:8080/skill-barter';

  constructor(private http: HttpClient) { }

  getUserSkills(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/user/${userId}/skills`);
  }

  deleteSkill(userId: number, skillId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/user/${userId}/skill/${skillId}`);
  }
  addSkill(userId: number, skill: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/post/${userId}`, skill);
  }

  updateSkill(userId: number, skillId: number, skill: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/user/${userId}/skill/${skillId}`, skill);
  }

  searchSkills(params: SkillSearchParams): Observable<any[]> {
    // Clean undefined/null values from params
    const cleanedParams: Record<string, string> = {};

    for (const key in params) {
      const value = params[key as keyof SkillSearchParams];
      if (value !== undefined && value !== null && value !== '') {
        cleanedParams[key] = String(value);
      }
    }

    return this.http.get<any[]>(`${this.baseUrl}/skills/search`, {
      params: cleanedParams
    });
  }


}
