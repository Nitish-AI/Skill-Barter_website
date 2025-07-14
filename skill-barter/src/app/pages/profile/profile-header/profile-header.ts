import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { User } from '../../../services/user';
import { UserProfile } from '../../../models/user-profile';
import { CommonModule, DatePipe } from '@angular/common';
import { UserProfileUpdate } from '../../../models/user-profile-update';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile-header',
  standalone: true,
  templateUrl: './profile-header.html',
  styleUrls: ['./profile-header.css'],
  imports: [CommonModule, DatePipe, FormsModule]
})
export class ProfileHeader implements OnInit {

  userData: UserProfile | null = null;

  updateData: UserProfileUpdate = {
    firstName: '',
    lastName: '',
    userName: '',
    bio: '',
    address: '',
    imageUrl: '',
    currentPassword: ''
  };

  isEditing: boolean = false;


  constructor(private userService: User, private cd: ChangeDetectorRef) { }

  ngOnInit(): void {
    const currentUser = this.userService.getUser();

    if (currentUser?.id) {
      this.userService.getUserProfile(currentUser.id).subscribe({
        next: (res: UserProfile) => {
          this.userData = res;

          //  Pre-fill updateData form with current profile
          this.updateData = {
            firstName: res.firstName,
            lastName: res.lastName,
            userName: res.userName,
            bio: res.bio,
            address: res.address,
            imageUrl: res.imageUrl,
            currentPassword: '' // leave blank, user must enter
          };

          this.cd.detectChanges(); // prevents ExpressionChanged error
        },
        error: (err) => console.error('Profile fetch failed:', err)
      });
    }
  }

  submitUpdate(): void {
    const user = this.userService.getUser();
    if (!user?.id) {
      console.error('User ID not found');
      return;
    }

    this.userService.updateUserProfile(user.id, this.updateData).subscribe({
      next: (res: any) => {
        this.userData = res;
        this.isEditing = false;
        console.log(' Profile updated:', res);
      },
      error: (err) => {
        console.error(' Update failed:', err);
        alert(err.error || 'Failed to update profile');
      }
    });
  }
  toggleEdit(): void {
    this.isEditing = !this.isEditing;
    if (this.isEditing) {
      // Reset updateData to current user data when entering edit mode
      if (this.userData) {
        this.updateData = { ...this.userData, currentPassword: '' };
      }
    }
  }


}
