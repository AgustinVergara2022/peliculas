import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from './auth-service';
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
  currentTheme: 'light' | 'dark' = 'dark';
  

  

  constructor(public auth: AuthService, private router: Router) {}

  ngOnInit() {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
      this.currentTheme = savedTheme as 'light' | 'dark';
    } else {
      const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
      this.currentTheme = prefersDark ? 'dark' : 'light';
    }
    this.updateTheme();
  }

  toggleTheme() {
    this.currentTheme = this.currentTheme === 'dark' ? 'light' : 'dark';
    localStorage.setItem('theme', this.currentTheme);
    this.updateTheme();
  }

  updateTheme() {
    document.documentElement.setAttribute('data-bs-theme', this.currentTheme);
  }
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

  logout(){ this.auth.logout(); this.router.navigate(['/']); }

}

