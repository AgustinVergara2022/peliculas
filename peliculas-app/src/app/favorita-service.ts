import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavoritaService {
   private urlBase = 'http://localhost:8080/api/favoritas';

  private http = inject(HttpClient);

  listar(): Observable<any[]> {
    return this.http.get<any[]>(this.urlBase);
  }

  agregarPorTitulo(titulo: string): Observable<any> {
    return this.http.post<any>(`${this.urlBase}/agregar-por-titulo?titulo=${titulo}`, {});
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.urlBase}/${id}`);
  }
}
