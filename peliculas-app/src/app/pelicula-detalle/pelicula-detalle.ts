import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { PeliculaService } from '../pelicula-service';
import { FavoritaService } from '../favorita-service';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pelicula-detalle',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './pelicula-detalle.html'
})
export class PeliculaDetalleComponent implements OnInit {
  pelicula: any;
  constructor(
    private route: ActivatedRoute,
    private peliculaService: PeliculaService,
    private favoritaService: FavoritaService
  ) {}
  

   ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.peliculaService.buscarPorId(id).subscribe({
        next: (data) => (this.pelicula = data),
        error: (err) => console.error('Error al cargar detalles:', err)
      });
    }
  }

agregarAFavoritas(): void {
  console.log('👉 Botón agregarAFavoritas() presionado');

  if (!this.pelicula?.Title) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: 'No se pudo obtener el título de la película.',
    });
    return;
  }

  this.favoritaService.agregarPorTitulo(this.pelicula.Title).subscribe({
    next: () => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: `"${this.pelicula.Title}" agregada a favoritas ❤️`,
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
      });
    },
    error: (err) => {
      console.error('Error al agregar favorita:', err);
      Swal.fire({
        icon: 'error',
        title: 'Error al agregar favorita',
        text: 'No se pudo conectar con el servidor o el título no fue encontrado.',
      });
    },
  });
}
}
