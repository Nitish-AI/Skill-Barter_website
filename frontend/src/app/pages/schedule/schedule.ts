import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { LeftSidePanel } from '../../components/left-side-panel/left-side-panel';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-schedule',
  imports: [CommonModule, FormsModule, RouterModule, LeftSidePanel],
  templateUrl: './schedule.html',
  styleUrl: './schedule.css'
})
export class Schedule {

}
