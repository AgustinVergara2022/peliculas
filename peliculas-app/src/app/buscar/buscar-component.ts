import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PeliculaService } from '../pelicula-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-buscar',
  imports: [CommonModule],
  templateUrl: './buscar-component.html'
})
export class BuscarComponent implements OnInit {
  termino: string = '';
  resultados: any[] = [];
  cargando: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private peliculaService: PeliculaService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.termino = params['q'] || '';
      if (this.termino) {
        this.buscarPeliculas(this.termino);
      }
    });
  }

  buscarPeliculas(titulo: string) {
    this.cargando = true;
    this.peliculaService.buscarPorTitulo(titulo).subscribe({
      next: (data) => {
        this.resultados = Array.isArray(data) ? data : [data];
        this.cargando = false;
      },
      error: (error) => {
        console.error('Error al buscar películas:', error);
        this.cargando = false;
      }
    });
  }
}