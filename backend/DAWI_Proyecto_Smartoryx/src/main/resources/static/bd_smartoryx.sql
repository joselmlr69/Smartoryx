DROP DATABASE IF EXISTS bd_smartoryx;
CREATE DATABASE bd_smartoryx;
USE bd_smartoryx;

-- =========================
-- ROLES
-- =========================
CREATE TABLE tb_roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30)
);

-- =========================
-- USUARIOS
-- =========================
CREATE TABLE tb_usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    correo VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    id_rol INT,
    estado TINYINT DEFAULT 1,
    FOREIGN KEY (id_rol) REFERENCES tb_roles(id_rol)
);

-- =========================
-- DIRECCIONES
-- =========================
CREATE TABLE tb_direcciones (
    id_direccion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    direccion VARCHAR(150),
    ciudad VARCHAR(50),
    referencia VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES tb_usuarios(id_usuario)
);

-- =========================
-- PRODUCTOS
-- =========================
CREATE TABLE tb_categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE tb_marcas (
    id_marca INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE tb_proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    ruc VARCHAR(11),
    telefono VARCHAR(20)
);

CREATE TABLE tb_productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    precio DECIMAL(10,2),
    stock INT,
    id_categoria INT,
    id_marca INT,
    id_proveedor INT,
    estado TINYINT DEFAULT 1,
    imagen_url VARCHAR(200),
    FOREIGN KEY (id_categoria) REFERENCES tb_categorias(id_categoria),
    FOREIGN KEY (id_marca) REFERENCES tb_marcas(id_marca),
    FOREIGN KEY (id_proveedor) REFERENCES tb_proveedores(id_proveedor)
);

-- =========================
-- STOCK
-- =========================
CREATE TABLE tb_movimientos_stock (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT,
    tipo VARCHAR(20), -- ENTRADA / SALIDA
    cantidad INT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_producto) REFERENCES tb_productos(id_producto)
);

-- =========================
-- VENTAS
-- =========================
CREATE TABLE tb_ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT,
    total DECIMAL(10,2),
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_usuario) REFERENCES tb_usuarios(id_usuario)
);

CREATE TABLE tb_detalle_venta (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT,
    id_producto INT,
    cantidad INT,
    precio DECIMAL(10,2),
    subtotal DECIMAL(10,2),
    FOREIGN KEY (id_venta) REFERENCES tb_ventas(id_venta),
    FOREIGN KEY (id_producto) REFERENCES tb_productos(id_producto)
);

-- =========================
-- PAGOS
-- =========================
CREATE TABLE tb_metodos_pago (
    id_metodo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30),
    estado TINYINT DEFAULT 1
);

CREATE TABLE tb_pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT,
    id_metodo INT,
    monto DECIMAL(10,2),
    FOREIGN KEY (id_venta) REFERENCES tb_ventas(id_venta),
    FOREIGN KEY (id_metodo) REFERENCES tb_metodos_pago(id_metodo)
);

-- =========================
-- CARRITO
-- =========================
CREATE TABLE tb_carrito (
    id_carrito INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES tb_usuarios(id_usuario)
);

CREATE TABLE tb_carrito_detalle (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_carrito INT,
    id_producto INT,
    cantidad INT,
    UNIQUE (id_carrito, id_producto),
    FOREIGN KEY (id_carrito) REFERENCES tb_carrito(id_carrito),
    FOREIGN KEY (id_producto) REFERENCES tb_productos(id_producto)
);

-- =========================
-- ENVÍOS
-- =========================
CREATE TABLE tb_envios (
    id_envio INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT,
    id_direccion INT,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    fecha_envio DATETIME,
    FOREIGN KEY (id_venta) REFERENCES tb_ventas(id_venta),
    FOREIGN KEY (id_direccion) REFERENCES tb_direcciones(id_direccion)
);

-- =========================
-- AUDITORÍA
-- =========================
CREATE TABLE tb_auditoria (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    tabla VARCHAR(50),
    accion VARCHAR(20),
    descripcion VARCHAR(255),
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT
);