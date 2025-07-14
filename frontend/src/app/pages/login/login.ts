import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from '../../services/user';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  email: string = '';
  remember: boolean = false;
  password: string = '';
  showPassword: boolean = false;
  private isBrowser: boolean;

  constructor(
    private userService: User,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  login(): void {
    const credentials = { email: this.email, password: this.password };

    this.userService.loginUser(credentials).subscribe({
      next: (res: any) => {
        console.log('Login successful:', res);

        this.userService.setUser(res);

        // SSR-safe access to localStorage
        if (this.isBrowser) {
          if (this.remember) {
            localStorage.setItem('rememberedEmail', this.email);
          } else {
            localStorage.removeItem('rememberedEmail');
          }
        }

        this.router.navigate(['/profile']);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Login failed:', err.message);
        alert(err.error || 'Invalid credentials');
      }
    });
  }

  ngOnInit(): void {
    //  SSR-safe localStorage access
    if (this.isBrowser) {
      const savedEmail = localStorage.getItem('rememberedEmail');
      if (savedEmail) {
        this.email = savedEmail;
        this.remember = true;
      }
    }
  }

  ngOnDestroy(): void {
    this.password = '';
  }
}
