import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

import { ProveedorService } from '../../services/proveedor.service';

@Component({
  selector: 'app-editar-proveedor',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './editar-proveedor.html',
  styleUrls: ['./editar-proveedor.css']
})
export class EditarProveedor implements OnInit {

  id: number = 0;

  proveedor: any = {
    nombre: '',
    ruc: '',
    telefono: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private proveedorService: ProveedorService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarProveedor();
  }

  cargarProveedor() {
    this.proveedorService.listar().subscribe(data => {

      const prov = data.find(p => p.id == this.id);

      if (prov) {
        this.proveedor = {
          nombre: prov.nombre,
          ruc: prov.ruc,
          telefono: prov.telefono
        };

        this.cd.detectChanges();
      }
    });
  }

  actualizar() {

    const data = {
      nombre: this.proveedor.nombre,
      ruc: this.proveedor.ruc,
      telefono: this.proveedor.telefono
    };

    this.proveedorService.actualizar(this.id, data).subscribe(() => {
      alert('Proveedor actualizado correctamente');
      this.router.navigateByUrl('/proveedores');
    });
  }
}