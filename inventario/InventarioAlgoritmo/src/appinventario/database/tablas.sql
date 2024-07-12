IF OBJECT_ID('suministro', 'U') IS NOT NULL
    DROP TABLE suministro;
IF OBJECT_ID('consumo', 'U') IS NOT NULL
    DROP TABLE consumo;
IF OBJECT_ID('inventario', 'U') IS NOT NULL
    DROP TABLE inventario;
IF OBJECT_ID('producto', 'U') IS NOT NULL
    DROP TABLE producto;
IF OBJECT_ID('proveedor', 'U') IS NOT NULL
    DROP TABLE proveedor;
IF OBJECT_ID('usuario', 'U') IS NOT NULL
    DROP TABLE usuario;

CREATE TABLE "producto" (
	"id" INT IDENTITY(1,1) PRIMARY KEY,
	"nombre" VARCHAR(50) NULL DEFAULT 'NULL',
	"categoria" VARCHAR(50) NULL DEFAULT 'NULL',
	"descripcion" VARCHAR(255) NULL DEFAULT 'NULL',
	"precio" DECIMAL(10,2) NULL DEFAULT 'NULL',
	"unidad_medida" VARCHAR(50) NULL DEFAULT 'NULL'
)
;

CREATE TABLE "proveedor" (
	"id" INT IDENTITY(1,1) PRIMARY KEY,
	"nombre" VARCHAR(50) NULL DEFAULT 'NULL',
	"telefono" VARCHAR(50) NULL DEFAULT 'NULL',
	"direccion" VARCHAR(255) NULL DEFAULT 'NULL',
	"email" VARCHAR(100) NULL DEFAULT 'NULL'
)
;

CREATE TABLE "usuario" (
	"id" INT IDENTITY(1,1) PRIMARY KEY,
	"admin" BIT NULL DEFAULT 'NULL',
	"nombre" VARCHAR(50) NULL DEFAULT 'NULL',
	"apellido" VARCHAR(50) NULL DEFAULT 'NULL',
	"telefono" VARCHAR(50) NULL DEFAULT 'NULL',
	"usuario" VARCHAR(50) NULL DEFAULT 'NULL',
	"password" VARCHAR(100) NULL DEFAULT 'NULL',
	"cargo" VARCHAR(50) NULL DEFAULT 'NULL',
	"forcePass" BIT NULL DEFAULT 'NULL'
)
;

CREATE TABLE "suministro" (
	"id" INT IDENTITY(1,1) PRIMARY KEY,
	"producto_id" INT NOT NULL,
	"proveedor_id" INT NOT NULL,
	"usuario_id" INT NOT NULL,
	"cantidad" INT NOT NULL,
	"fecha_ingreso" DATE NOT NULL
)
;

CREATE TABLE "consumo" (
	"id" INT IDENTITY(1,1) PRIMARY KEY,
	"producto_id" INT NOT NULL,
	"usuario_id" INT NOT NULL,
	"cantidad" INT NOT NULL,
	"fecha_salida" DATE NOT NULL
)
;

CREATE TABLE "inventario" (
	"id" INT IDENTITY(1,1) PRIMARY KEY,
	"producto_id" INT NOT NULL,
	"cantidad" INT NOT NULL
)
;

ALTER TABLE "suministro" ADD CONSTRAINT fk_producto_id_suministro FOREIGN KEY ("producto_id") REFERENCES "producto"("id");
ALTER TABLE "suministro" ADD CONSTRAINT fk_proveedor_id_suministro FOREIGN KEY ("proveedor_id") REFERENCES "proveedor"("id");
ALTER TABLE "suministro" ADD CONSTRAINT fk_usuario_id_suministro FOREIGN KEY ("usuario_id") REFERENCES "usuario"("id");
ALTER TABLE "consumo" ADD CONSTRAINT fk_producto_id_consumo FOREIGN KEY ("producto_id") REFERENCES "producto"("id");
ALTER TABLE "consumo" ADD CONSTRAINT fk_usuario_id_consumo FOREIGN KEY ("usuario_id") REFERENCES "usuario"("id");
ALTER TABLE "inventario" ADD CONSTRAINT fk_producto_id_inventario FOREIGN KEY ("producto_id") REFERENCES "producto"("id");

-- Insertar registros en la tabla "usuario"
INSERT INTO "usuario" ("admin", "nombre", "apellido", "telefono", "usuario", "password", "cargo", "forcePass") VALUES 
(1, 'Hillary', 'Castañeda', '987654321', 'hillary1304', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Administrador', 1),
(1, 'Jenny', 'Jenny', '987654321', 'jenny', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Administrador', 1),
(1, 'Ezer', 'Vidarte', '919196797', 'ezer', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Administrador', 1);

-- Insertar registros en la tabla "producto"
INSERT INTO "producto" ("nombre", "categoria", "descripcion", "precio", "unidad_medida") VALUES 
('Arroz', 'Granos y Cereales', 'Arroz blanco de grano largo', 20.00, 'kilogramo'),
('Fideos', 'Pasta', 'Fideos de trigo largo', 15.00, 'kilogramo'),
('Aceite de Oliva', 'Aceites y Vinagres', 'Aceite de oliva extra virgen', 30.00, 'litro'),
('Vinagre Balsámico', 'Aceites y Vinagres', 'Vinagre balsámico de Módena', 25.00, 'litro'),
('Sal', 'Condimentos', 'Sal marina fina', 5.00, 'kilogramo'),
('Pimienta', 'Condimentos', 'Pimienta negra molida', 10.00, 'kilogramo'),
('Azúcar', 'Endulzantes', 'Azúcar blanca refinada', 15.00, 'kilogramo'),
('Harina', 'Harinas y Almidones', 'Harina de trigo todo uso', 18.00, 'kilogramo'),
('Levadura', 'Repostería', 'Levadura seca instantánea', 25.00, 'kilogramo'),
('Mantequilla', 'Lácteos', 'Mantequilla sin sal', 12.00, 'kilogramo'),
('Queso Parmesano', 'Lácteos', 'Queso parmesano rallado', 40.00, 'kilogramo'),
('Huevos', 'Huevos y Lácteos', 'Huevos frescos de gallina', 0.50, 'unidad'),
('Pollo', 'Carnes', 'Pollo entero fresco', 3.00, 'kilogramo'),
('Carne de Res', 'Carnes', 'Carne de res magra', 12.00, 'kilogramo'),
('Pescado', 'Mariscos', 'Filete de pescado blanco', 8.00, 'kilogramo'),
('Langostinos', 'Mariscos', 'Langostinos frescos', 20.00, 'kilogramo'),
('Verduras Variadas', 'Frutas y Verduras', 'Paquete de verduras surtidas', 15.00, 'kilogramo'),
('Frutas Variadas', 'Frutas y Verduras', 'Paquete de frutas tropicales', 18.00, 'kilogramo'),
('Pan de Molde', 'Panadería', 'Pan de molde integral', 5.00, 'unidad'),
('Pan Francés', 'Panadería', 'Baguette francesa artesanal', 2.00, 'unidad'),
('Salsa de Tomate', 'Salsas y Conservas', 'Salsa de tomate casera', 8.00, 'litro'),
('Salsa de Soja', 'Salsas y Conservas', 'Salsa de soja baja en sodio', 10.00, 'litro'),
('Café', 'Bebidas', 'Café colombiano tostado', 30.00, 'kilogramo'),
('Té', 'Bebidas', 'Té negro de Assam', 25.00, 'kilogramo');

-- Insertar registros en la tabla "proveedor"
INSERT INTO "proveedor" ("nombre", "telefono", "direccion", "email") VALUES 
('Proveedor A', '123456789', 'Av. Proveedor A #123', 'proveedora@example.com'),
('Proveedor B', '987654321', 'Calle Proveedor B #456', 'proveedorb@example.com'),
('Proveedor C', '111222333', 'Jr. Proveedor C #789', 'proveedorc@example.com'),
('Proveedor D', '444555666', 'Av. Proveedor D #012', 'proveedord@example.com'),
('Proveedor E', '777888999', 'Calle Proveedor E #345', 'proveedore@example.com'),
('Proveedor F', '666777888', 'Jr. Proveedor F #678', 'proveedorf@example.com'),
('Proveedor G', '222333444', 'Av. Proveedor G #901', 'proveedorg@example.com'),
('Proveedor H', '555666777', 'Calle Proveedor H #234', 'proveedorh@example.com'),
('Proveedor I', '888999000', 'Jr. Proveedor I #567', 'proveedori@example.com'),
('Proveedor J', '333444555', 'Av. Proveedor J #890', 'proveedorj@example.com'),
('Proveedor K', '999000111', 'Calle Proveedor K #234', 'proveedork@example.com'),
('Proveedor L', '000111222', 'Jr. Proveedor L #567', 'proveedorl@example.com'),
('Proveedor M', '111222333', 'Av. Proveedor M #890', 'proveedorm@example.com'),
('Proveedor N', '222333444', 'Calle Proveedor N #234', 'proveedorn@example.com'),
('Proveedor O', '333444555', 'Jr. Proveedor O #567', 'proveedoro@example.com'),
('Proveedor P', '444555666', 'Av. Proveedor P #890', 'proveedorp@example.com'),
('Proveedor Q', '555666777', 'Calle Proveedor Q #234', 'proveedorq@example.com'),
('Proveedor R', '666777888', 'Jr. Proveedor R #567', 'proveedorr@example.com'),
('Proveedor S', '777888999', 'Av. Proveedor S #890', 'proveedors@example.com'),
('Proveedor T', '888999000', 'Calle Proveedor T #234', 'proveedort@example.com'),
('Proveedor U', '999000111', 'Jr. Proveedor U #567', 'proveedoru@example.com'),
('Proveedor V', '000111222', 'Av. Proveedor V #890', 'proveedorv@example.com'),
('Proveedor W', '111222333', 'Calle Proveedor W #234', 'proveedorw@example.com'),
('Proveedor X', '222333444', 'Jr. Proveedor X #567', 'proveedorx@example.com'),
('Proveedor Y', '333444555', 'Av. Proveedor Y #890', 'proveedory@example.com'),
('Proveedor Z', '444555666', 'Calle Proveedor Z #234', 'proveedorz@example.com');
