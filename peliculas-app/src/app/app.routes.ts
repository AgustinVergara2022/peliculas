import { Routes } from '@angular/router';
import { FavoritaListaComponent } from './favorita-lista-component/favorita-lista-component';
import { HomeComponent } from './home-component/home-component';
import { BuscarComponent } from './buscar-pelicula/buscar-component';
import { PeliculaDetalleComponent } from './pelicula-detalle/pelicula-detalle';
import { SobreMiComponent } from './sobre-mi-component/sobre-mi-component';
import { authGuard } from './guards/auth-guard';
import { LoginComponent } from './auth/login-component';

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full' },
    {path: 'favoritas', component: FavoritaListaComponent, canActivate: [authGuard]},
    {path: 'buscar', component: BuscarComponent},
    { path: 'pelicula/:id', component: PeliculaDetalleComponent },
    {path: 'sobre-mi', component: SobreMiComponent},
    { path: 'login', component: LoginComponent },
    { path: '**', redirectTo: '' }
];
