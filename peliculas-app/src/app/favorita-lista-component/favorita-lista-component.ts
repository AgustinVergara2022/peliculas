import { Component, inject } from '@angular/core';
import { Favorita } from '../favorita';
import { FavoritaService } from '../favorita-service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-favorita-lista',
  imports: [DatePipe],
  templateUrl: './favorita-lista.html',
})
export class FavoritaListaComponent {

  favoritas: Favorita[] = [];
  favoritaService = inject(FavoritaService);

  ngOnInit(): void {
    this.cargarFavoritas();
  }

  cargarFavoritas(): void {
    this.favoritaService.listar().subscribe({
      next: (data) => this.favoritas = data,
      error: (err) => console.error('Error al cargar favoritas', err)
    });
  }

  eliminar(id: number): void {
    this.favoritaService.eliminar(id).subscribe({
      next: () => this.cargarFavoritas(),
      error: (err) => console.error('Error al eliminar favorita', err)
    });
  }

}
