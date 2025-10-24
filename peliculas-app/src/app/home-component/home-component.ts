import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-component',
  imports: [CommonModule],
  templateUrl: './home-component.html',
  styleUrl: './home-component.css'
})
export class HomeComponent {
   constructor(private router: Router) {}

  verDetalle(imdbID: string) {
    this.router.navigate(['/pelicula', imdbID]);
  }
}
