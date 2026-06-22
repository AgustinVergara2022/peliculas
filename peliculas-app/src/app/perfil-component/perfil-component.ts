import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-perfil-component',
  imports: [],
  templateUrl: './perfil-component.html',
  styleUrl: './perfil-component.css'
})
export class PerfilComponent {
   usuario: any = {};

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this.http.get('http://localhost:8080/auth/perfil', {
        headers: { Authorization: `Bearer ${token}` }
      }).subscribe({
        next: (data) => this.usuario = data,
        error: (err) => console.error('Error al cargar perfil', err)
      });
    }
  }
}
