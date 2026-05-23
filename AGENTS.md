# Smartoryx — AGENTS.md

Tienda de celulares en español, frontend Astro + backend Spring Boot.

---

## Stack

| Capa | Tecnología |
|---|---|
| **Frontend** | Astro 6 + React 19 + Tailwind CSS 4 + DaisyUI 5 + pnpm + Nanostores |
| **Backend** | Spring Boot 3.5.14 + Java 17 + Maven + MySQL 8 + Hibernate + MapStruct |
| **Seguridad** | Spring Security + BCryptPasswordEncoder + sesiones HTTP |
| **Validación** | Jakarta Validation (`@Valid`, `@NotBlank`, `@Size`, etc.) |

---

## Progreso

### ✅ Completado

#### Backend
- **pom.xml**: Corregida versión Spring Boot (4.0.5 → 3.5.14), agregadas dependencias de Security, Validation, MapStruct
- **Spring Security + BCrypt**: `SecurityConfig.java` con protección por roles (ADMIN/CLIENTE), contraseñas hasheadas
- **DTOs**: 8 Request DTOs + 17 Response DTOs (nunca se exponen entidades JPA)
- **MapStruct**: 14 interfaces Mapper generadas automáticamente
- **Excepciones**: `ResourceNotFoundException`, `BadRequestException`, `UnauthorizedException`, `ForbiddenException` + `GlobalExceptionHandler`
- **Endpoints nuevos**: `/usuario/sesion`, `/usuario/logout`, `/venta/usuario/{id}`, `/venta/{id}`, `/producto/completo`, `/producto/por-marca/{id}`, `/direccion/usuario/{id}`, `/pago/venta/{idVenta}`
- **Endpoints mejorados**: Carrito acepta body JSON (`POST /carrito/agregar/{idUsuario}` con `{idProducto, cantidad}`)
- **Todos los controllers** refactorizados para usar DTOs + `@Valid`
- **Repositories** ampliados: `findByUsuarioId`, `findByIdWithDetalles`, `findByMarcaAndActivo`, `findAllActivos`, `findByIdActivo`, `findByCorreoWithRol`, `findByVentaId`
- **Services** actualizados con excepciones custom y BCrypt
- **Categoria.java**: Corregido `@GeneratedValue` → `GenerationType.IDENTITY`
- **CORS**: Integrado en `SecurityFilterChain` (eliminado `CorsConfig.java`)
- **Imágenes**: 32 imágenes renombradas por marca (Apple, samsung, redmi, moto) con nombres de modelo real
- **Producto.java**: Agregado campo `imagen_url` (`@Column(length = 500)`)

#### Base de datos
- **Categorías**: Reducidas a 1 sola ("Celulares")
- **Marcas**: 4 (Samsung, Apple, Xiaomi, Motorola)
- **Productos**: 32 celulares con `imagen_url` apuntando a imágenes locales
- **Scripts**: `scriptsmartoryxjoseluis.sql` (32 productos) e `inserts_actualizados.sql` (16 productos)

### ⏳ Pendiente

#### Frontend
- [ ] Traducir todo el texto a español
- [ ] Rebrand "E-COMMER" → "Smartoryx"
- [ ] Crear `src/services/` — módulo API para consumir backend
- [ ] Conectar catálogo de productos al backend
- [ ] Conectar filtro por marcas
- [ ] Conectar carrito
- [ ] Implementar login/registro
- [ ] Implementar checkout y "Mis compras"

---

## API Endpoints

### Públicos
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/producto/listar` | Todos los productos activos |
| GET | `/producto/completo` | Productos con JOIN FETCH |
| GET | `/producto/por-marca/{id}` | Filtrar por marca |
| GET | `/producto/{id}` | Producto por ID |
| GET | `/marca/listar` | Todas las marcas |
| GET | `/marca/{id}` | Marca por ID |
| GET | `/categoria/listar` | Todas las categorías |
| GET | `/categoria/{id}` | Categoría por ID |
| GET | `/metodo-pago/listar` | Métodos de pago |
| GET | `/metodo-pago/{id}` | Método por ID |
| GET | `/rol/listar` | Roles |
| POST | `/usuario/login` | Login (body: `{correo, password}`) |
| POST | `/usuario/agregar` | Registro (body: `UsuarioRequest`) |
| GET | `/usuario/sesion` | Usuario logueado actual |
| POST | `/usuario/logout` | Cerrar sesión |

### Autenticado (requiere login)
| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/carrito/{idUsuario}` | Obtener carrito |
| POST | `/carrito/agregar/{idUsuario}` | Agregar al carrito (body: `{idProducto, cantidad}`) |
| DELETE | `/carrito/eliminar/{idUsuario}/{idProducto}` | Quitar del carrito |
| DELETE | `/carrito/vaciar/{idUsuario}` | Vaciar carrito |
| GET | `/carrito/total/{idUsuario}` | Calcular total |
| POST | `/venta/generar-desde-carrito/{idUsuario}` | Checkout desde carrito |
| GET | `/venta/usuario/{idUsuario}` | "Mis compras" |
| GET | `/venta/{id}` | Detalle de venta |
| POST | `/venta/pagar` | Pagar (body: `{idVenta, idMetodo}`) |
| POST | `/venta/pagar/{idVenta}/{idMetodo}` | Pagar por path params |
| GET | `/direccion/usuario/{idUsuario}` | Direcciones del usuario |
| POST | `/direccion/agregar/{idUsuario}` | Crear dirección |
| PUT | `/direccion/{id}` | Actualizar dirección |
| DELETE | `/direccion/{id}` | Eliminar dirección |
| PUT | `/usuario/{id}` | Actualizar usuario |
| DELETE | `/usuario/{id}` | Eliminar usuario |

### Solo ADMIN
| Método | Endpoint | Descripción |
|---|---|---|
| POST/PUT/DELETE | `/producto/**` | CRUD productos |
| POST/PUT/DELETE | `/categoria/**` | CRUD categorías |
| POST/PUT | `/marca/**` | CRUD marcas |
| POST/PUT/DELETE | `/proveedor/**` | CRUD proveedores |
| GET | `/usuario/listar` | Listar todos los usuarios |
| PUT/DELETE | `/usuario/**` | Gestionar usuarios |
| GET/POST/PUT/DELETE | `/envio/**` | CRUD envíos |
| POST | `/movimiento-stock/**` | Movimientos de stock |

---

## Estructura de paquetes

```
com.cibertec.DAWI_Proyecto_Smartoryx/
├── config/
│   └── SecurityConfig.java          # Spring Security + BCrypt + CORS
├── controller/                      # 15 controllers (todos usan DTOs)
├── dto/
│   ├── request/                     # 8 DTOs con @Valid
│   └── response/                    # 17 DTOs (sin password, sin ciclos)
├── exception/
│   ├── BadRequestException.java
│   ├── ForbiddenException.java
│   ├── GlobalExceptionHandler.java  # @ControllerAdvice
│   ├── ResourceNotFoundException.java
│   └── UnauthorizedException.java
├── mapper/                          # 14 interfaces MapStruct
├── model/                           # 16 entidades JPA
├── repository/                      # 16 repositorios JPA
└── service/                         # 16 servicios
```

---

## Decisiones clave

| Decisión | Razón |
|---|---|
| **Spring Security completo** | Protección por roles, sesiones HTTP, BCrypt para contraseñas |
| **MapStruct** | Genera mappers en compilación, menos boilerplate |
| **DTOs separados** | Nunca exponer entidades JPA (evita ciclos JSON, password leaks) |
| **Sesión HTTP** | Compatible con Astro frontend, `credentials: 'include'` |
| **Filtro por marca** | Solo 1 categoría "Celulares", el frontend filtra por `id_marca` |
| **Imágenes locales** | Servidas por Spring Boot desde `/static/{brand}/` |
| **Soft delete** | Productos y usuarios usan `estado=0` en vez de borrado físico |

---

## Configuración

| Parámetro | Valor |
|---|---|
| Backend port | `8080` |
| Frontend port | `4321` |
| Base de datos | `bd_smartoryx` |
| CORS | `http://localhost:4321` |
| `ddl-auto` | `update` |

---

## Notas

- Java 17 requerido
- MySQL 8 requerido
- Ejecutar script SQL antes de arrancar para datos iniciales
- Usuarios existentes tienen contraseñas en texto plano — crear nuevos usuarios desde la API o actualizar manualmente con BCrypt
- El frontend aún no está conectado al backend
