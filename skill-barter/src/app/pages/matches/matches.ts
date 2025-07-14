import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { LeftSidePanel } from '../../components/left-side-panel/left-side-panel';

@Component({
  selector: 'app-matches',
  imports: [CommonModule, RouterModule, FormsModule, LeftSidePanel],
  templateUrl: './matches.html',
  styleUrl: './matches.css'
})
export class Matches {

}
