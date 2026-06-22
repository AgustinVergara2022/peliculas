import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth-service';

@Component({
  selector: 'app-registro-component',
  imports: [CommonModule, FormsModule],
  templateUrl: './registro-component.html',
  styleUrl: './registro-component.css'
})
export class RegistroComponent {

   username = '';
  password = '';
  errorMsg = '';
  successMsg = '';

  constructor(private auth: AuthService, private router: Router) {}

  registrar() {
    if (!this.username || !this.password) {
      this.errorMsg = 'Todos los campos son obligatorios.';
      return;
    }

    this.auth.register(this.username, this.password).subscribe({
      next: () => {
        this.successMsg = 'Cuenta creada con éxito ✅';
        setTimeout(() => this.router.navigate(['/login']), 1200);
      },
      error: () => {
        this.errorMsg = 'No se pudo registrar. El usuario ya existe o hubo un problema.';
      }
    });
  }
}
