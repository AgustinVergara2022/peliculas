import { Routes } from '@angular/router';
import { FavoritaListaComponent } from './favorita-lista-component/favorita-lista-component';
import { HomeComponent } from './home-component/home-component';
import { BuscarComponent } from './buscar-pelicula/buscar-component';
import { PeliculaDetalleComponent } from './pelicula-detalle/pelicula-detalle';
import { SobreMiComponent } from './sobre-mi-component/sobre-mi-component';

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full' },
    {path: 'favoritas', component: FavoritaListaComponent},
    {path: 'buscar', component: BuscarComponent},
    { path: 'pelicula/:id', component: PeliculaDetalleComponent },
    { path: '**', redirectTo: '' }
];
