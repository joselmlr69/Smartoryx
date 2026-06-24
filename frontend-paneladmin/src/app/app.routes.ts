import { Routes } from '@angular/router';
import { Productos } from './components/productos/productos';
import { RegistroProducto } from './components/registro-producto/registro-producto';
import { EditarProducto } from './components/editar-producto/editar-producto';
import { Login } from './components/login/login';
import { Categorias } from './components/categorias/categorias';
import { EditarCategorias } from './components/editar-categorias/editar-categorias';
import { RegistrarCategoria } from './components/registrar-categoria/registrar-categoria';
import { Usuarios } from './components/usuarios/usuarios';
import { RegistroUsuario } from './components/registro-usuario/registro-usuario';
import { EditarUsuario } from './components/editar-usuario/editar-usuario';
import { Direcciones } from './components/direcciones/direcciones';
import { RegistroDireccion } from './components/registro-direccion/registro-direccion';
import { EditarDireccion } from './components/editar-direccion/editar-direccion';
import { Proveedores } from './components/proveedores/proveedores';
import { RegistroProveedor } from './components/registro-proveedor/registro-proveedor';
import { EditarProveedor } from './components/editar-proveedor/editar-proveedor';
import { Dashboard } from './components/dashboard/dashboard';


export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'dashboard', component: Dashboard },

  //Productos
  { path: 'productos', component: Productos },
  { path: 'registrar', component: RegistroProducto },
  { path: 'editar/:id', component: EditarProducto },

  //Categorias
  { path: 'categorias', component: Categorias },
  { path: 'editar-categorias/:id', component: EditarCategorias },
  { path: 'categorias/registrar', component: RegistrarCategoria },

  //Usuarios
  { path: 'usuarios', component: Usuarios },
  { path: 'usuarios/registrar', component: RegistroUsuario },
  { path: 'usuarios/editar/:id', component: EditarUsuario },

  //Direcciones
  { path: 'direcciones', component: Direcciones },
  { path: 'direcciones/registrar', component: RegistroDireccion },
  { path: 'direcciones/editar/:id', component: EditarDireccion },

  //Proveedores
  { path: 'proveedores', component: Proveedores },
  { path: 'proveedores/registrar', component: RegistroProveedor },
  { path: 'proveedores/editar/:id', component: EditarProveedor },
  
];
