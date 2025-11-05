import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth-service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  standalone: true,
  selector: 'app-login',
  imports: [FormsModule],
  template: `
  <div class="container mt-5" style="max-width:420px">
    <h3 class="mb-3">Iniciar sesión</h3>
    <form (ngSubmit)="submit()">
      <div class="mb-3">
        <label class="form-label">Usuario</label>
        <input [(ngModel)]="username" name="username" class="form-control" required>
      </div>
      <div class="mb-3">
        <label class="form-label">Contraseña</label>
        <input [(ngModel)]="password" name="password" type="password" class="form-control" required>
      </div>
      <button class="btn btn-primary w-100" type="submit">Entrar</button>
    </form>
  </div>`
})
export class LoginComponent {
  username = '';
  password = '';
  constructor(private auth: AuthService, private router: Router) {}
  submit() {
    this.auth.login(this.username, this.password).subscribe({
      next: () => { Swal.fire('¡Bienvenido!', '', 'success'); this.router.navigate(['/favoritas']); },
      error: () => Swal.fire('Error', 'Credenciales inválidas', 'error')
    });
  }
}