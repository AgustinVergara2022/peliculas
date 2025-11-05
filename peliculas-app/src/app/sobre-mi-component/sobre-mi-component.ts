import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sobre-mi-component',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sobre-mi-component.html',
  styleUrl: './sobre-mi-component.css'
})
export class SobreMiComponent {
  info: any = {};

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get('http://localhost:8080/api/sobre-mi').subscribe(data => {
      this.info = data;
    });
  }
}
