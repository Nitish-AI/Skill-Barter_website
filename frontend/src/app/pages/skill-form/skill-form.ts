import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { Skill } from '../../services/skill';
import { User } from '../../services/user';
import { SkillFormModel } from '../../models/skill-form-model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-skill-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './skill-form.html',
  styleUrls: ['./skill-form.css']
})
export class SkillForm implements OnInit {
  @Input() editMode: boolean = false;
  @Input() skillData: Partial<SkillFormModel> & { id?: number } = {};

  userId!: number;
  tagInput: string = '';

  skill: SkillFormModel = {
    title: '',
    description: '',
    category: '',
    tags: [],
    intent: 'teach',
    skillLevel: '',
    prerequisites: '',
    equipment: '',
    sessionFormat: ''
  };

  constructor(
    private skillService: Skill,
    private userService: User,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const currentUser = this.userService.getUser();
    if (currentUser?.id) {
      this.userId = currentUser.id;
    }

    const navigation = this.router.getCurrentNavigation();
    const stateSkill = navigation?.extras?.state?.['skill'];

    if (this.route.snapshot.routeConfig?.path === 'edit-skill/:id' && stateSkill) {
      this.editMode = true;
      this.skill = {
        ...this.skill,
        ...stateSkill,
        tags: Array.isArray(stateSkill.tags)
          ? stateSkill.tags
          : (stateSkill.tags as string).split(',').map((t: string) => t.trim())
      };
    }
  }
  addTag(): void {
    const tag = this.tagInput.trim();
    if (tag && !this.skill.tags.includes(tag)) {
      this.skill.tags.push(tag);
    }
    this.tagInput = '';
  }
  goToProfile(): void {
    this.router.navigate(['/user/profile/{user_id}']);
  }


  removeTag(tag: string): void {
    this.skill.tags = this.skill.tags.filter(t => t !== tag);
  }

  submitSkill(): void {
    const payload = {
      ...this.skill,
      tags: this.skill.tags
    };

    if (this.editMode && this.skillData.id) {
      this.skillService.updateSkill(this.userId, this.skillData.id, payload).subscribe({
        next: () => this.router.navigate(['/user/profile']),
        error: err => console.error('Update error:', err)
      });
    } else {
      this.skillService.addSkill(this.userId, payload).subscribe({
        next: () => this.router.navigate(['/user/profile']),
        error: err => console.error('Creation error:', err)
      });
    }
  }
}
