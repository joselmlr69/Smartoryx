USE bd_smartoryx;

-- ROLES
INSERT INTO tb_roles (nombre) VALUES 
('ADMIN'),
('CLIENTE');

-- METODOS DE PAGO
INSERT INTO tb_metodos_pago (nombre, estado) VALUES 
('EFECTIVO', 1),
('TARJETA', 1),
('YAPE', 1),
('PLIN', 1);

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
('Smart Distribuciones', '20509876543', '988777666');

-- USUARIOS
INSERT INTO tb_usuarios (nombre, apellido, correo, password, id_rol, estado) VALUES
('Admin', 'Principal', 'admin@test.com', '12345', 1, 1),
('Juan', 'Perez', 'juan@test.com', '12345', 2, 1);

-- PRODUCTOS - SAMSUNG
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Samsung Galaxy A05', 'Celular Samsung económico 64GB', 399.90, 30, 1, 1, 1, 1, '/samsung/A05.jpg'),
('Samsung Galaxy A35', 'Celular Samsung gama media 128GB', 1299.90, 20, 1, 1, 1, 1, '/samsung/A35.jpg'),
('Samsung Galaxy S23 FE', 'Celular Samsung Fan Edition 256GB', 2199.90, 12, 1, 1, 1, 1, '/samsung/S23 FE.jpg'),
('Samsung Galaxy A55 5G', 'Celular Samsung 5G 256GB', 1899.90, 15, 1, 1, 1, 1, '/samsung/A55 5G.jpg');

-- PRODUCTOS - APPLE
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('iPhone 15', 'Celular Apple 128GB', 4299.90, 10, 1, 2, 1, 1, '/Apple/iphone-15.jpg'),
('iPhone 15 Pro', 'Celular Apple Pro 256GB Titanio', 5499.90, 8, 1, 2, 1, 1, '/Apple/iphone-15-pro.jpg'),
('iPhone 14', 'Celular Apple 128GB', 3499.90, 15, 1, 2, 1, 1, '/Apple/iphone-14.jpg'),
('iPhone 13', 'Celular Apple 128GB', 2799.90, 20, 1, 2, 1, 1, '/Apple/iphone-13.jpg');

-- PRODUCTOS - XIAOMI
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Redmi Note 13', 'Celular Xiaomi 128GB económico', 699.90, 40, 1, 3, 2, 1, '/redmi/redmi-note-13.jpg'),
('Redmi Note 13 Pro', 'Celular Xiaomi Pro 256GB', 1099.90, 25, 1, 3, 2, 1, '/redmi/redmi-note-13-pro.jpg'),
('Xiaomi 14', 'Celular Xiaomi flagship 256GB', 2999.90, 10, 1, 3, 2, 1, '/redmi/xiaomi-14.jpg'),
('POCO X6 Pro', 'Celular POCO gaming 256GB', 1299.90, 20, 1, 3, 2, 1, '/redmi/poco-x6-pro.jpg');

-- PRODUCTOS - MOTOROLA
INSERT INTO tb_productos (nombre, descripcion, precio, stock, id_categoria, id_marca, id_proveedor, estado, imagen_url) VALUES
('Motorola Edge 50 Pro', 'Cel Motorola premium 256GB', 1999.90, 15, 1, 4, 1, 1, '/moto/motorola-edge-50-pro.jpg'),
('Moto G84', 'Cel Motorola gama media 256GB', 899.90, 30, 1, 4, 2, 1, '/moto/moto-g84.jpg'),
('Moto E13', 'Cel Motorola económico 64GB', 349.90, 50, 1, 4, 2, 1, '/moto/moto-e13.jpg'),
('Motorola Razr 40', 'Cel Motorola plegable 256GB', 3499.90, 5, 1, 4, 1, 1, '/moto/motorola-razr-40.jpg');
