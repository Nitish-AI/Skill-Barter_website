import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../../services/user';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { Skill } from '../../services/skill';
import { LeftSidePanel } from '../../components/left-side-panel/left-side-panel';
import { SkillSearch } from '../../components/skill-search/skill-search';
import { SkillForm } from '../skill-form/skill-form';

@Component({
  selector: 'app-skills-i-teach',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    LeftSidePanel,
    SkillForm
  ],
  templateUrl: './skills-i-teach.html',
  styleUrls: ['./skills-i-teach.css']
})
export class SkillsITeach implements OnInit {
  skillsToTeach: any[] = [];
  selectedSkill: any = {};
  editMode: boolean = false;

  userId!: number;

  constructor(
    private skillService: Skill,
    private userService: User,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { }

  async ngOnInit(): Promise<void> {
    // ✅ Dynamically import Bootstrap only in browser
    if (isPlatformBrowser(this.platformId)) {
      await import('bootstrap');
      console.log('Bootstrap JS loaded (client only)');
    }

    const currentUser = this.userService.getUser();
    this.userId = currentUser?.id;

    if (this.userId) {
      this.skillService.getUserSkills(this.userId).subscribe({
        next: (skills) => {
          this.skillsToTeach = skills.filter((s) => s.intent === 'teach');
        },
        error: (err) => console.error('Error fetching teaching skills', err)
      });
    }
  }

  loadSkills(): void {
    this.skillService.getUserSkills(this.userId).subscribe({
      next: (skills) => {
        this.skillsToTeach = skills.filter((s) => s.intent === 'teach');
      },
      error: (err) => console.error('Failed to reload skills:', err)
    });
  }

  deleteSkill(skillId: number): void {
    if (confirm('Are you sure you want to delete this skill?')) {
      this.skillService.deleteSkill(this.userId, skillId).subscribe({
        next: () => {
          this.skillsToTeach = this.skillsToTeach.filter((s) => s.id !== skillId);
        },
        error: (err) => alert('Error deleting skill: ' + err.message)
      });
    }
  }


  updateSkill(): void {
    const userId = this.userService.getUser().id;
    const skillId = this.selectedSkill.id;
    const payload = {
      title: this.selectedSkill.title,
      description: this.selectedSkill.description,
      tags: this.selectedSkill.tags.split(',').map((t: string) => t.trim())
    };

    this.skillService.updateSkill(userId, skillId, payload).subscribe({
      next: () => {
        this.loadSkills();
        this.editMode = false;
      },
      error: (err) => console.error('Update failed', err)
    });
  }

  openEdit(skill: any): void {
    this.editMode = true;
    this.selectedSkill = skill;
  }

  onEditCompleted(): void {
    this.editMode = false;
    this.selectedSkill = null;
    this.loadSkills();
  }
}
