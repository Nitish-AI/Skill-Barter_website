import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navbar } from "./components/navbar/navbar";
import { Footer } from "./components/footer/footer";
import { User } from './services/user';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, Navbar, Footer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App implements OnInit {
  constructor(private service: User) { }

  ngOnInit(): void {
    this.service.checkConnection().subscribe({
      next: (res) => {
        console.log('Backend says:', res);
      },
      error: (err) => {
        console.error('Error while connecting to backend:', err);
      }
    });
  }

}
