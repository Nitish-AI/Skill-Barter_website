import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from '../../services/user';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './signup.html',
  styleUrls: ['./signup.css']
})
export class Signup {
  constructor(
    private userService: User,
    private router: Router
  ) { }

  // Form model matching UserRegistrationDTO
  user: any = {
    firstName: '',
    lastName: '',
    userName: '',
    email: '',
    password: '',
    bio: '',
    address: '',
    agree: false
  };

  showPassword: boolean = false;

  // Toggle password visibility
  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  // Submit signup form
  register(): void {
    if (!this.user.agree) {
      alert('You must agree to the Terms and Conditions.');
      return;
    }

    this.userService.registerUser(this.user).subscribe({
      next: (res: HttpErrorResponse) => {
        console.log('User registered:', res);
        alert('Signup successful! Please login to continue.');
        localStorage.setItem('skillBarterUser', JSON.stringify(res)); // Store user data
        this.router.navigate(['/login']); // Redirect to login page
      },
      error: (err: HttpErrorResponse) => {
        console.error('Signup failed:', err.message);
        alert(err.error || 'Signup failed. Please try again.');
      }
    });
  }
}
