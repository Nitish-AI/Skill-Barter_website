import { Component } from '@angular/core';
import { Skill } from '../../services/skill';
import { SkillSearchParams } from '../../models/skill-search-params';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-skill-search',
  templateUrl: './skill-search.html',
  styleUrls: ['./skill-search.css'],
  imports: [FormsModule, CommonModule],
  standalone: true
})
export class SkillSearch {
  skills: any[] = [];
  filters: SkillSearchParams = {
    query: '',
    category: '',
    level: '',
    sessionFormat: '',
    sort: 'newest'
  };

  constructor(private skillService: Skill) { }

  onSearch(): void {
    this.skillService.searchSkills(this.filters).subscribe({
      next: (res) => this.skills = res,
      error: (err) => console.error('Search failed:', err)
    });
  }
}

