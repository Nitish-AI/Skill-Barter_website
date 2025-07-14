import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { User } from '../../services/user';

@Component({
  selector: 'app-left-side-panel',
  imports: [CommonModule, RouterModule],
  templateUrl: './left-side-panel.html',
  styleUrl: './left-side-panel.css'
})
export class LeftSidePanel {
  constructor(
    private userService: User,
    private router: Router
  ) { }
  userName: string = '';
  logout(): void {
    this.userService.logout();
    this.router.navigate(['/login']);
  }





}
