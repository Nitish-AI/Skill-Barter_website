import { Component } from '@angular/core';
import { LeftSidePanel } from '../../components/left-side-panel/left-side-panel';
import { RouterModule } from '@angular/router';
import { ProfileHeader } from './profile-header/profile-header';
import { ProfileSkills } from './profile-skills/profile-skills';

@Component({
  selector: 'app-profile',
  imports: [LeftSidePanel, RouterModule, ProfileHeader, ProfileSkills],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile {

}
