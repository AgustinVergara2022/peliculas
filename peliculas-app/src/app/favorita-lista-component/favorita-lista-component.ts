import { Component, inject } from '@angular/core';
import { Favorita } from '../favorita';
import { FavoritaService } from '../favorita-service';
import { DatePipe, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-favorita-lista',
  standalone: true,
  imports: [DatePipe, FormsModule, NgFor, NgIf],
  templateUrl: './favorita-lista.html',
})
export class FavoritaListaComponent {

  favoritas: Favorita[] = [];
  favoritaService = inject(FavoritaService);
  router = inject(Router);

  mensaje: string = '';

  ngOnInit(): void {
    this.cargarFavoritas();
  }

  cargarFavoritas(): void {
    this.favoritaService.listar().subscribe({
      next: (data) => this.favoritas = data,
      error: (err) => console.error('Error al cargar favoritas', err)
    });
  }

  setPuntuacion(favorita: Favorita, valor: number): void {
    favorita.puntuacion = valor;
  }

  eliminar(id: number): void {
    Swal.fire({
      title: '¿Eliminar favorita?',
      text: 'Esta acción no se puede deshacer.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#6c757d',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) {
        this.favoritaService.eliminar(id).subscribe({
          next: () => {
            this.cargarFavoritas();
            Swal.fire('Eliminada', 'La película fue eliminada de tus favoritas', 'success');
          },
          error: (err) => console.error('Error al eliminar favorita', err)
        });
      }
    });
  }

  guardarComentarioYPuntuacion(favorita: Favorita): void {
    if (!favorita.puntuacion || favorita.puntuacion < 1 || favorita.puntuacion > 5) {
      Swal.fire('Advertencia', 'Debes seleccionar una puntuación válida entre 1 y 5', 'warning');
      return;
    }

    this.favoritaService.actualizar(favorita).subscribe({
      next: () => {
        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Comentario y puntuación guardados ✅',
          showConfirmButton: false,
          timer: 2000
        });
      },
      error: (err) => {
        console.error('Error al actualizar favorita:', err);
        Swal.fire('Error', 'No se pudo guardar la información', 'error');
      }
    });
  }

  verDetalle(imdbID: string): void {
    this.router.navigate(['/pelicula', imdbID]);
  }

  filtrarPorAnio(anio: number): void {
    if (isNaN(anio) || anio < 0) {
    this.mensaje = '⚠️ Por favor ingresa un año válido (0 o mayor).';
    setTimeout(() => this.mensaje = '', 3000); // se borra a los 3 segundos
    return;
  }

  this.favoritaService.filtrarPorAnio(anio).subscribe({
    next: data => {
      this.favoritas = data;
      this.mensaje = data.length
        ? `✅ Se encontraron ${data.length} películas del año ${anio} o posterior.`
        : `❌ No se encontraron películas a partir del año ${anio}.`;
      setTimeout(() => this.mensaje = '', 3000);
    },
    error: err => this.manejarError(err)
  });
  }

  ordenarPor(campo: string): void {
    if (!campo) return;

  this.favoritaService.ordenarPor(campo).subscribe({
    next: data => this.favoritas = data,
    error: err => this.manejarError(err)
  });
  }

  manejarError(err: any): void {
    console.error('Error en la solicitud:', err);
    Swal.fire('Error', 'Ocurrió un error al procesar la solicitud. Intenta nuevamente más tarde.', 'error');
  }
}
