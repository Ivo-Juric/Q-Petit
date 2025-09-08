-- ======================
-- TABLAS BASE
-- ======================

CREATE TABLE Proveedor (
    id_Proveedor SERIAL PRIMARY KEY,
    Nombre VARCHAR(30),
    Contacto VARCHAR(30)
);

CREATE TABLE CategoriasMenu (
    id_Categoria SERIAL PRIMARY KEY,
    Descripcion VARCHAR(30)
);

CREATE TABLE TipoMenu (
    id_Tipo SERIAL PRIMARY KEY,
    Descripcion VARCHAR(30)
);

CREATE TABLE Ingredientes (
    id_Ingrediente SERIAL PRIMARY KEY,
    Nombre VARCHAR(30),
    Stock INT
);

-- ======================
-- MENU
-- ======================

CREATE TABLE Menu (
    id_Menu SERIAL PRIMARY KEY,
    Nombre VARCHAR(30),
    id_Categoria INT REFERENCES CategoriasMenu(id_Categoria),
    tipo_Menu INT REFERENCES TipoMenu(id_Tipo),
    id_Proveedor INT REFERENCES Proveedor(id_Proveedor),
    PrecioBase MONEY,
    CantidadMinComensales INT,
    Entrada BOOLEAN,
    Principal BOOLEAN,
    Postre BOOLEAN,
    Observaciones VARCHAR(200)
);

CREATE TABLE Menu_Ingredientes (
    id_Menu INT REFERENCES Menu(id_Menu),
    id_Ingrediente INT REFERENCES Ingredientes(id_Ingrediente),
    CantidadUtilizadaGr DOUBLE PRECISION,
    PRIMARY KEY (id_Menu, id_Ingrediente)
);

-- ======================
-- CLIENTE Y USUARIO
-- ======================

CREATE TABLE Rol (
    id_Rol SERIAL PRIMARY KEY,
    NombreUsuario VARCHAR(20)
);

CREATE TABLE Usuario (
    id_Usuario SERIAL PRIMARY KEY,
    NombreUsuario VARCHAR(20),
    Contraseña VARCHAR(30),
    id_Rol INT REFERENCES Rol(id_Rol),
    Telefono VARCHAR(30)
);

CREATE TABLE Cliente (
    DNI VARCHAR(8) PRIMARY KEY,
    Nombre VARCHAR(30),
    Apellido VARCHAR(30),
    Email VARCHAR(35),
    Telefono VARCHAR(16),
    Observaciones VARCHAR(300)
);

-- ======================
-- PERSONAL
-- ======================

CREATE TABLE Disponibilidades (
    id_Disponibilidad SERIAL PRIMARY KEY,
    Dia DATE
);

CREATE TABLE TipoPersonal (
    id_Tipo SERIAL PRIMARY KEY,
    Descripcion VARCHAR(30)
);

CREATE TABLE Personal (
    DNI INT PRIMARY KEY,
    Nombre VARCHAR(30),
    Apellido VARCHAR(30),
    Edad INT,
    id_Disponibilidad INT REFERENCES Disponibilidades(id_Disponibilidad),
    id_TipoPersonal INT REFERENCES TipoPersonal(id_Tipo),
    Telefono VARCHAR(30),
    Gmail VARCHAR(30)
);

CREATE TABLE Especialidades (
    id_Especialidad SERIAL PRIMARY KEY,
    Nombre VARCHAR(30)
);

CREATE TABLE Personal_Especialidades (
    id_Especialidad INT REFERENCES Especialidades(id_Especialidad),
    id_Personal INT REFERENCES Personal(DNI),
    PRIMARY KEY (id_Especialidad, id_Personal)
);

-- ======================
-- EVENTOS
-- ======================

CREATE TABLE Estado (
    id_Estado SERIAL PRIMARY KEY,
    Descripcion VARCHAR(30)
);

CREATE TABLE TipoEvento (
    id_TipoEvento SERIAL PRIMARY KEY,
    Descripcion VARCHAR(30)
);

CREATE TABLE Evento (
    id_Evento SERIAL PRIMARY KEY,
    fecha_Inicio TIMESTAMP,
    fecha_Fin TIMESTAMP,
    CantidadInvitados INT,
    id_TipoEvento INT REFERENCES TipoEvento(id_TipoEvento),
    tipo_Menu INT,
    id_Proveedor INT REFERENCES Proveedor(id_Proveedor),
    LinkHojaServicio VARCHAR(200),
    id_Cliente VARCHAR(8) REFERENCES Cliente(DNI),
    id_Menu INT REFERENCES Menu(id_Menu),
    id_Estado INT REFERENCES Estado(id_Estado)
);

CREATE TABLE Evento_Personal (
    id_Personal INT REFERENCES Personal(DNI),
    id_Evento INT REFERENCES Evento(id_Evento),
    CantidadUtilizadaGr DOUBLE PRECISION,
    PRIMARY KEY (id_Personal, id_Evento)
);

CREATE TABLE InsumoTecnico (
    id_insumo SERIAL PRIMARY KEY,
    Nombre VARCHAR(30),
    Marca VARCHAR(30),
    cantDisponible INT
);

CREATE TABLE Evento_Insumo (
    id_insumo INT REFERENCES InsumoTecnico(id_insumo),
    id_evento INT REFERENCES Evento(id_Evento),
    cantUtilizada INT,
    PRIMARY KEY (id_insumo, id_evento)
);

-- ======================
-- LOGÍSTICA
-- ======================

CREATE TABLE Ciudad (
    id_Ciudad SERIAL PRIMARY KEY,
    Descripcion VARCHAR(30)
);

CREATE TABLE Vehiculo (
    id_Vehiculo SERIAL PRIMARY KEY,
    Patente VARCHAR(10),
    Anio_Modelo INT,
    Disponibilidad BOOLEAN,
    CapacidadCarga INT
);

CREATE TABLE Logistica (
    id_Logistica SERIAL PRIMARY KEY,
    id_Vehiculo INT REFERENCES Vehiculo(id_Vehiculo)
);

CREATE TABLE Locacion (
    id_Locacion SERIAL PRIMARY KEY,
    id_Ciudad INT REFERENCES Ciudad(id_Ciudad),
    DistanciaSedeKm DOUBLE PRECISION,
    id_Logistica INT REFERENCES Logistica(id_Logistica)
);

CREATE TABLE Logistica_Vehiculo (
    id_Logistica INT REFERENCES Logistica(id_Logistica),
    id_Vehiculo INT REFERENCES Vehiculo(id_Vehiculo),
    PRIMARY KEY (id_Logistica, id_Vehiculo)
);

-- ======================
-- MOVIMIENTOS
-- ======================

CREATE TABLE Movimiento (
    id_Movimiento SERIAL PRIMARY KEY,
    fecha TIMESTAMP,
    esIngreso BOOLEAN,
    importe DECIMAL(12,3),
    descripcion VARCHAR(100),
    id_Evento INT REFERENCES Evento(id_Evento),
    id_Usuario INT REFERENCES Usuario(id_Usuario)
);