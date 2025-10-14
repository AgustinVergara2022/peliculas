import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PeliculaService {
  private urlBase = 'http://localhost:8080/api/peliculas';

  private http = inject(HttpClient);

  buscarPorTitulo(titulo: string): Observable<any> {
    return this.http.get<any>(`${this.urlBase}/buscar?titulo=${titulo}`);
  }
}
