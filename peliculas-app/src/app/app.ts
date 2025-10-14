import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
//import { FavoritaListaComponent } from "./favorita-lista-component/favorita-lista-component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
})


export class App {
  termino: string = '';

  constructor(private router: Router) {}

  onSearch(event: Event) {
    event.preventDefault();
    if (this.termino.trim() !== '') {
      this.router.navigate(['/buscar'], { queryParams: { q: this.termino } });
      this.termino = '';
    }
  }

    buscarPelicula(event: Event): void {
    event.preventDefault(); // evita recargar la página
    if (this.termino.trim()) {
      this.router.navigate(['/buscar'], { queryParams: { q: this.termino } });
      this.termino = '';
    }
  }
}

