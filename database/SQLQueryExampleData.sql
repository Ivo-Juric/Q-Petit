INSERT INTO Proveedor (Nombre, Contacto) VALUES 
('Proveedor A','Contacto A'),
('Proveedor B','Contacto B');

INSERT INTO CategoriasMenu (Descripcion) VALUES 
('Vegano'),
('Clasico');

INSERT INTO TipoMenu (Descripcion) VALUES 
('Buffet'),
('A la carta');

INSERT INTO Ingredientes (Nombre, Stock) VALUES 
('Carne',1000),
('Papas',5000),
('Zanahoria',2000);

INSERT INTO Menu (Nombre,id_Categoria,tipo_Menu,id_Proveedor,PrecioBase,CantidadMinComensales,Entrada,Principal,Postre,Observaciones)
VALUES 
('Menu 1',1,1,1,1500,10,TRUE,TRUE,FALSE,'Observaciones menu1'),
('Menu 2',2,2,2,2500,15,FALSE,TRUE,TRUE,'Observaciones menu2');

INSERT INTO Menu_Ingredientes (id_Menu,id_Ingrediente,CantidadUtilizadaGr) VALUES 
(1,1,200),
(1,2,300),
(2,3,150);

INSERT INTO Rol (NombreUsuario) VALUES 
('Admin'),
('Usuario');

INSERT INTO Usuario (NombreUsuario,Contraseña,id_Rol,Telefono) VALUES 
('ivo','1234',1,'351111111'),
('ana','abcd',2,'351222222');

INSERT INTO Cliente (DNI,Nombre,Apellido,Email,Telefono,Observaciones) VALUES 
('12345678','Juan','Perez','juan@mail.com','351333333','Cliente vip');

INSERT INTO Disponibilidades (Dia) VALUES 
('2025-09-10'),
('2025-09-11');

INSERT INTO TipoPersonal (Descripcion) VALUES 
('Cocinero'),
('Mozo');

INSERT INTO Personal (DNI,Nombre,Apellido,Edad,id_Disponibilidad,id_TipoPersonal,Telefono,Gmail) VALUES 
(101,'Carlos','Lopez',30,1,1,'351444444','carlos@mail.com');

INSERT INTO Especialidades (Nombre) VALUES 
('Pasteleria'),
('Carnes');

INSERT INTO Personal_Especialidades (id_Especialidad,id_Personal) VALUES 
(1,101),
(2,101);

INSERT INTO Estado (Descripcion) VALUES 
('Confirmado'),
('Pendiente');

INSERT INTO TipoEvento (Descripcion) VALUES 
('Casamiento'),
('Cumpleaños');

INSERT INTO Evento (fecha_Inicio,fecha_Fin,CantidadInvitados,id_TipoEvento,tipo_Menu,id_Proveedor,LinkHojaServicio,id_Cliente,id_Menu,id_Estado)
VALUES 
('2025-10-01 12:00','2025-10-01 18:00',100,1,1,1,'link1','12345678',1,1);

INSERT INTO Evento_Personal (id_Personal,id_Evento,CantidadUtilizadaGr) VALUES 
(101,1,500);

INSERT INTO InsumoTecnico (Nombre,Marca,cantDisponible) VALUES 
('Proyector','Epson',5),
('Parlante','Sony',10);

INSERT INTO Evento_Insumo (id_insumo,id_evento,cantUtilizada) VALUES 
(1,1,1);

INSERT INTO Ciudad (Descripcion) VALUES 
('Cordoba'),
('Buenos Aires');

INSERT INTO Vehiculo (Patente,Anio_Modelo,Disponibilidad,CapacidadCarga) VALUES 
('ABC123',2020,TRUE,1000);

INSERT INTO Logistica (id_Vehiculo) VALUES 
(1);

INSERT INTO Locacion (id_Ciudad,DistanciaSedeKm,id_Logistica) VALUES 
(1,15.5,1);

INSERT INTO Logistica_Vehiculo (id_Logistica,id_Vehiculo) VALUES 
(1,1);

INSERT INTO Movimiento (fecha,esIngreso,importe,descripcion,id_Evento,id_Usuario) VALUES 
('2025-09-08 15:00',TRUE,5000.00,'Pago inicial',1,1);


SELECT * FROM evento;