import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LeftSidePanel } from '../../components/left-side-panel/left-side-panel';
import { CommonModule } from '@angular/common';
import { SkillSearch } from '../../components/skill-search/skill-search';

@Component({
  selector: 'app-skills-i-want',
  imports: [LeftSidePanel, RouterModule, CommonModule, SkillSearch],
  templateUrl: './skills-i-want.html',
  styleUrl: './skills-i-want.css'
})
export class SkillsIWant {
  skillsToLearn: any[] = [];

}
