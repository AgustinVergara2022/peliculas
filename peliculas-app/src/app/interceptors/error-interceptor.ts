import { HttpInterceptorFn } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      console.error('❌ Error global HTTP:', error);

      let mensaje = 'Error inesperado. Intenta nuevamente.';

      if (error.status === 0) {
        mensaje = 'No se pudo conectar con el servidor.';
      } else if (error.status === 404) {
        mensaje = 'Recurso no encontrado.';
      } else if (error.status === 500) {
        mensaje = 'Error interno del servidor.';
      }

      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: mensaje,
        confirmButtonColor: '#d33'
      });

      return throwError(() => error);
    })
  );
};