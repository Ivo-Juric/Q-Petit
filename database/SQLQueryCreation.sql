-- ======================
-- BASE TABLES
-- ======================

CREATE TABLE Suppliers (
    supplier_ID SERIAL PRIMARY KEY,
    name VARCHAR(30),
    contact VARCHAR(30)
);

CREATE TABLE MenuCategories (
    category_ID SERIAL PRIMARY KEY,
    description VARCHAR(30)
);

CREATE TABLE MenuTypes (
    type_ID SERIAL PRIMARY KEY,
    description VARCHAR(30)
);

CREATE TABLE Ingredients (
    ingredient_ID SERIAL PRIMARY KEY,
    name VARCHAR(30),
    stock INT
);

-- ======================
-- MENU
-- ======================

CREATE TABLE Menus (
    menu_ID SERIAL PRIMARY KEY,
    name VARCHAR(30),
    category_ID INT REFERENCES MenuCategories(category_ID),
    menu_type_ID INT REFERENCES MenuTypes(type_ID),
    supplier_ID INT REFERENCES Suppliers(supplier_ID),
    base_price MONEY,
    min_guest_count INT,
    is_starter BOOLEAN,
    is_main_course BOOLEAN,
    is_dessert BOOLEAN,
    notes VARCHAR(200)
);

CREATE TABLE MenuIngredients (
    menu_ID INT REFERENCES Menus(menu_ID),
    ingredient_ID INT REFERENCES Ingredients(ingredient_ID),
    grams_used DOUBLE PRECISION,
    PRIMARY KEY (menu_ID, ingredient_ID)
);

-- ======================
-- CLIENT AND USERS
-- ======================

CREATE TABLE UserRoles (
    role_ID SERIAL PRIMARY KEY,
    role_name VARCHAR(20)
);

CREATE TABLE Users (
    user_ID SERIAL PRIMARY KEY,
    username VARCHAR(20),
    password VARCHAR(30),
    role_ID INT REFERENCES UserRoles(role_ID),
    phone_number VARCHAR(30)
);

CREATE TABLE Customers (
    DNI VARCHAR(8) PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(35),
    phone VARCHAR(16),
    notes VARCHAR(300)
);

-- ======================
-- STAFF
-- ======================

CREATE TABLE Availability (
    availability_ID SERIAL PRIMARY KEY,
    date_available DATE
);

CREATE TABLE StaffTypes (
    type_ID SERIAL PRIMARY KEY,
    description VARCHAR(30)
);

CREATE TABLE Staff (
    DNI INT PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    age INT,
    availability_ID INT REFERENCES Availability(availability_ID),
    staff_type_ID INT REFERENCES StaffTypes(type_ID),
    phone VARCHAR(30),
    email VARCHAR(30)
);

CREATE TABLE Specialities (
    specialty_ID SERIAL PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE StaffSpecialities (
    specialty_ID INT REFERENCES Specialities(specialty_ID),
    staff_ID INT REFERENCES Staff(DNI),
    PRIMARY KEY (specialty_ID, staff_ID)
);

-- ======================
-- EVENTS
-- ======================

CREATE TABLE Status (
    status_ID SERIAL PRIMARY KEY,
    description VARCHAR(30)
);

CREATE TABLE EventTypes (
    event_type_ID SERIAL PRIMARY KEY,
    description VARCHAR(30)
);

CREATE TABLE Events (
    event_ID SERIAL PRIMARY KEY,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    guest_count INT,
    event_type_ID INT REFERENCES EventTypes(event_type_ID),
    menu_type_ID INT REFERENCES MenuTypes(type_ID),
    supplier_ID INT REFERENCES Suppliers(supplier_ID),
    service_sheet_link VARCHAR(200),
    customer_ID VARCHAR(8) REFERENCES Customers(DNI),
    menu_ID INT REFERENCES Menus(menu_ID),
    status_ID INT REFERENCES Status(status_ID)
);

CREATE TABLE EventStaff (
    staff_ID INT REFERENCES Staff(DNI),
    event_ID INT REFERENCES Events(event_ID),
    PRIMARY KEY (staff_ID, event_ID)
);

CREATE TABLE TechnicalSupplies (
    supply_ID SERIAL PRIMARY KEY,
    name VARCHAR(30),
    brand VARCHAR(30),
    available_quantity INT
);

CREATE TABLE EventSupplies (
    supply_ID INT REFERENCES TechnicalSupplies(supply_ID),
    event_ID INT REFERENCES Events(event_ID),
    quantity_used INT,
    PRIMARY KEY (supply_ID, event_ID)
);

-- ======================
-- LOGISTICS
-- ======================

CREATE TABLE Cities (
    city_ID SERIAL PRIMARY KEY,
    description VARCHAR(30)
);

CREATE TABLE Vehicles (
    vehicle_ID SERIAL PRIMARY KEY,
    number_plate VARCHAR(10),
    model_year INT,
    availability BOOLEAN,
    load_capacity INT
);

CREATE TABLE Logistics (
    logistics_ID SERIAL PRIMARY KEY,
    vehicle_ID INT REFERENCES Vehicles(vehicle_ID)
);

CREATE TABLE Locations (
    location_ID SERIAL PRIMARY KEY,
    city_ID INT REFERENCES Cities(city_ID),
    distance_from_headquarters_km DOUBLE PRECISION,
    logistics_ID INT REFERENCES Logistics(logistics_ID)
);

CREATE TABLE Logistics_Vehicles (
    logistics_ID INT REFERENCES Logistics(logistics_ID),
    vehicle_ID INT REFERENCES Vehicles(vehicle_ID),
    PRIMARY KEY (logistics_ID, vehicle_ID)
);

-- ======================
-- MOVEMENTS
-- ======================

CREATE TABLE Movements (
    movement_ID SERIAL PRIMARY KEY,
    date TIMESTAMP,
    is_income BOOLEAN,
    amount DECIMAL(12,3),
    description VARCHAR(100),
    event_ID INT REFERENCES Events(event_ID),
    user_ID INT REFERENCES Users(user_ID)
);
