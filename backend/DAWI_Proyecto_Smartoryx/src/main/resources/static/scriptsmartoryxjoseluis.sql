USE bd_smartoryx;

-- ROLES
INSERT INTO tb_roles (nombre) VALUES
('ADMIN'),
('CLIENTE');

-- USUARIOS
INSERT INTO tb_usuarios (nombre, apellido, correo, password, id_rol, estado) VALUES
('Admin', 'Principal', 'admin@gmail.com', '$2a$12$2.nVxnWEfrrzObzN3bGcMu4O5KQSMXY/VrVEawtqL3E3Xz1Y/kkaS', 1, 1),
('Juan', 'Perez', 'juan@gmail.com', '$2a$12$2.nVxnWEfrrzObzN3bGcMu4O5KQSMXY/VrVEawtqL3E3Xz1Y/kkaS', 2, 1),
('Luis', 'Ramirez', 'luis@gmail.com', '$2a$12$2.nVxnWEfrrzObzN3bGcMu4O5KQSMXY/VrVEawtqL3E3Xz1Y/kkaS', 2, 1);

-- CATEGORIA
INSERT INTO tb_categorias (nombre) VALUES
('Celulares'),
('Accesorios');

-- MARCAS
INSERT INTO tb_marcas (nombre) VALUES
('Samsung'),
('Apple'),
('Xiaomi'),
('Motorola');

-- PROVEEDORES
INSERT INTO tb_proveedores (nombre, ruc, telefono) VALUES
('Tech Import SAC', '20601234567', '999111222'),
('Smart Distribuciones', '20509876543', '988777666'),
('Global Mobile SAC', '20456789123', '977555444');

-- PRODUCTOS - SAMSUNG (id_marca = 1)
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Samsung Galaxy A05', 'Celular Samsung económico 64GB', 399.90, 30, 1, 1, 1, 1, '/samsung/A05.jpg'),
('Samsung Galaxy A35', 'Celular Samsung gama media 128GB', 1299.90, 20, 1, 1, 1, 1, '/samsung/A35.jpg'),
('Samsung Galaxy A55 5G', 'Celular Samsung 5G 256GB', 1899.90, 15, 1, 1, 2, 1, '/samsung/A55 5G.jpg'),
('Samsung Galaxy A55 Black', 'Celular Samsung 5G 256GB Negro', 1899.90, 15, 1, 1, 1, 1, '/samsung/A55 black.jpg'),
('Samsung Galaxy A55 White', 'Celular Samsung 5G 256GB Blanco', 1899.90, 15, 1, 1, 1, 1, '/samsung/A55 white.jpg'),
('Samsung Galaxy S20 Ultra 5G', 'Celular Samsung gama alta 5G 256GB', 2499.90, 10, 1, 1, 1, 1, '/samsung/S20 ulta 5g.jpg'),
('Samsung Galaxy S21+ 5G', 'Celular Samsung premium 5G 256GB', 2999.90, 8, 1, 1, 2, 1, '/samsung/S21+ 5G.jpg'),
('Samsung Galaxy S23 FE', 'Celular Samsung Fan Edition 256GB', 2199.90, 12, 1, 1, 1, 1, '/samsung/S23 FE.jpg');

-- PRODUCTOS - APPLE (id_marca = 2)
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('iPhone 15', 'Celular Apple 128GB', 4299.90, 10, 1, 2, 1, 1, '/Apple/iphone-15.jpg'),
('iPhone 15 Pro', 'Celular Apple Pro 256GB Titanio', 5499.90, 8, 1, 2, 1, 1, '/Apple/iphone-15-pro.jpg'),
('iPhone 15 Pro Max', 'Celular Apple Pro Max 256GB', 6299.90, 5, 1, 2, 1, 1, '/Apple/iphone-15-pro-max.jpg'),
('iPhone 14', 'Celular Apple 128GB', 3499.90, 15, 1, 2, 1, 1, '/Apple/iphone-14.jpg'),
('iPhone 14 Pro', 'Celular Apple Pro 256GB', 4799.90, 10, 1, 2, 2, 1, '/Apple/iphone-14-pro.jpg'),
('iPhone 13', 'Celular Apple 128GB', 2799.90, 20, 1, 2, 1, 1, '/Apple/iphone-13.jpg'),
('iPhone SE 2022', 'Celular Apple compacto 64GB', 1799.90, 25, 1, 2, 1, 1, '/Apple/iphone-se-2022.jpg'),
('iPhone 16', 'Celular Apple última generación 128GB', 4999.90, 5, 1, 2, 1, 1, '/Apple/iphone-16.jpg');

-- PRODUCTOS - XIAOMI (id_marca = 3)
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Redmi Note 13', 'Celular Xiaomi 128GB económico', 699.90, 40, 1, 3, 2, 1, '/redmi/redmi-note-13.jpg'),
('Redmi Note 13 Pro', 'Celular Xiaomi Pro 256GB', 1099.90, 25, 1, 3, 2, 1, '/redmi/redmi-note-13-pro.jpg'),
('Redmi Note 12', 'Celular Xiaomi 128GB', 599.90, 35, 1, 3, 2, 1, '/redmi/redmi-note-12.jpg'),
('Redmi 13C', 'Celular Xiaomi entry-level 128GB', 449.90, 50, 1, 3, 2, 1, '/redmi/redmi-13c.jpg'),
('Redmi Note 13 Pro Plus', 'Celular Xiaomi Pro+ 5G 256GB', 1499.90, 15, 1, 3, 3, 1, '/redmi/redmi-note-13-pro-plus.jpg'),
('Xiaomi 14', 'Celular Xiaomi flagship 256GB', 2999.90, 10, 1, 3, 3, 1, '/redmi/xiaomi-14.jpg'),
('Redmi Note 12 Pro', 'Celular Xiaomi Pro 128GB', 899.90, 30, 1, 3, 2, 1, '/redmi/redmi-note-12-pro.jpg'),
('POCO X6 Pro', 'Celular POCO gaming 256GB', 1299.90, 20, 1, 3, 3, 1, '/redmi/poco-x6-pro.jpg');

-- PRODUCTOS - MOTOROLA (id_marca = 4)
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Motorola Edge 50 Pro', 'Cel Motorola premium 256GB', 1999.90, 15, 1, 4, 1, 1, '/moto/motorola-edge-50-pro.jpg'),
('Motorola Edge 40', 'Cel Motorola gama alta 256GB', 1699.90, 12, 1, 4, 1, 1, '/moto/motorola-edge-40.jpg'),
('Moto G84', 'Cel Motorola gama media 256GB', 899.90, 30, 1, 4, 2, 1, '/moto/moto-g84.jpg'),
('Moto E13', 'Cel Motorola económico 64GB', 349.90, 50, 1, 4, 2, 1, '/moto/moto-e13.jpg'),
('Motorola Razr 40', 'Cel Motorola plegable 256GB', 3499.90, 5, 1, 4, 1, 1, '/moto/motorola-razr-40.jpg'),
('Moto G54', 'Cel Motorola 5G 128GB', 699.90, 25, 1, 4, 2, 1, '/moto/moto-g54.jpg'),
('Motorola Edge 50 Ultra', 'Cel Motorola ultra premium 512GB', 4299.90, 5, 1, 4, 1, 1, '/moto/motorola-edge-50-ultra.jpg'),
('Moto G34', 'Cel Motorola entry-level 128GB', 499.90, 40, 1, 4, 3, 1, '/moto/moto-g34.jpg');

-- PRODUCTOS - ACCESORIOS: CARGADORES (id_categoria = 2)
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Cargador Samsung 45W', 'Cargador rápido USB-C 45W original Samsung', 89.90, 50, 2, 1, 1, 1, '/Accesorios/cargador samsung 45 w.jpg'),
('Cargador Apple 20W', 'Cargador rápido USB-C 20W para iPhone', 79.90, 40, 2, 2, 1, 1, '/Accesorios/cargador apple.jpg'),
('Cargador Xiaomi 120W', 'Cargador HyperCharge 120W rápido', 99.90, 35, 2, 3, 2, 1, '/Accesorios/cargador xiomi 120w.jpg'),
('Cargador Motorola 33W', 'Cargador turbo power 33W original', 69.90, 45, 2, 4, 2, 1, '/Accesorios/cargador motorola.jpg');

-- PRODUCTOS - ACCESORIOS: AUDIFONOS (id_categoria = 2)
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Samsung Galaxy Buds4 Pro', 'Audífonos inalámbricos con ANC', 349.90, 25, 2, 1, 1, 1, '/Accesorios/samsung bud 4 pro.jpg'),
('Apple AirPods Pro', 'Audífonos inalámbricos con cancelación de ruido', 549.90, 20, 2, 2, 1, 1, '/Accesorios/airpods pro.jpg'),
('Redmi Buds 6', 'Audífonos inalámbricos económicos', 79.90, 60, 2, 3, 2, 1, '/Accesorios/redmi buds 6.jpg'),
('Motorola Moto Buds C30', 'Audífonos inalámbricos compactos', 89.90, 40, 2, 4, 2, 1, '/Accesorios/motorola moto buds c30.jpg');

-- PRODUCTOS - ACCESORIOS: FUNDAS (id_categoria = 2)
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Funda Samsung Galaxy', 'Funda protectora transparente anti-golpes', 39.90, 100, 2, 1, 1, 1, '/Accesorios/fundas samsung.jpg'),
('Funda Apple iPhone', 'Funda protectora de silicona premium', 49.90, 80, 2, 2, 1, 1, '/Accesorios/fundas apple.jpg'),
('Funda Redmi/Xiaomi', 'Funda protectora resistente transparente', 29.90, 120, 2, 3, 2, 1, '/Accesorios/fundas redmi.jpg'),
('Funda Motorola', 'Funda protectora flexible anti-impacto', 34.90, 90, 2, 4, 2, 1, '/Accesorios/fundas motorola.jpg');

-- METODOS DE PAGO
INSERT INTO tb_metodos_pago (nombre, estado) VALUES
('Yape', 1),
('Plin', 1),
('Tarjeta', 1),
('Efectivo', 1);

-- DIRECCIONES
INSERT INTO tb_direcciones (id_usuario, direccion, ciudad, referencia) VALUES
(2, 'Av. Aviacion 123', 'Lima', 'Cerca al Metro'),
(2, 'Jr. Los Pinos 456', 'Lima', 'Frente al parque'),
(3, 'Av. Angamos 789', 'Lima', 'Cerca al cruce');

-- CARRITOS
INSERT INTO tb_carrito (id_usuario) VALUES
(2),
(3);

-- DETALLE CARRITO
INSERT INTO tb_carrito_detalle (id_carrito, id_producto, cantidad) VALUES
(1, 1, 1),
(1, 9, 1),
(2, 17, 1);

-- MOVIMIENTOS DE STOCK
INSERT INTO tb_movimientos_stock (id_producto, tipo, cantidad) VALUES
(1, 'ENTRADA', 30),
(2, 'ENTRADA', 20),
(3, 'ENTRADA', 15),
(4, 'ENTRADA', 15),
(5, 'ENTRADA', 15),
(6, 'ENTRADA', 10),
(7, 'ENTRADA', 8),
(8, 'ENTRADA', 12),
(9, 'ENTRADA', 10),
(10, 'ENTRADA', 8),
(11, 'ENTRADA', 5),
(12, 'ENTRADA', 15),
(13, 'ENTRADA', 10),
(14, 'ENTRADA', 20),
(15, 'ENTRADA', 25),
(16, 'ENTRADA', 5),
(17, 'ENTRADA', 40),
(18, 'ENTRADA', 25),
(19, 'ENTRADA', 35),
(20, 'ENTRADA', 50),
(21, 'ENTRADA', 15),
(22, 'ENTRADA', 10),
(23, 'ENTRADA', 30),
(24, 'ENTRADA', 20),
(25, 'ENTRADA', 15),
(26, 'ENTRADA', 12),
(27, 'ENTRADA', 30),
(28, 'ENTRADA', 50),
(29, 'ENTRADA', 5),
(30, 'ENTRADA', 25),
(31, 'ENTRADA', 5),
(32, 'ENTRADA', 40),
(33, 'ENTRADA', 50),
(34, 'ENTRADA', 40),
(35, 'ENTRADA', 35),
(36, 'ENTRADA', 45),
(37, 'ENTRADA', 25),
(38, 'ENTRADA', 20),
(39, 'ENTRADA', 60),
(40, 'ENTRADA', 40),
(41, 'ENTRADA', 100),
(42, 'ENTRADA', 80),
(43, 'ENTRADA', 120),
(44, 'ENTRADA', 90);
