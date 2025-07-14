import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LeftSidePanel } from '../../components/left-side-panel/left-side-panel';

@Component({
  selector: 'app-reviews',
  imports: [CommonModule, FormsModule, RouterModule, LeftSidePanel],
  templateUrl: './reviews.html',
  styleUrl: './reviews.css'
})
export class Reviews {

}
