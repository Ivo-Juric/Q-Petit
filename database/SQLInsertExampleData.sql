-- ======================
-- EXAMPLE DATA FOR ENGLISH SCHEMA
-- ======================

-- Insert Suppliers
INSERT INTO Suppliers (name, contact) VALUES 
('Supplier A', 'Contact A'),
('Supplier B', 'Contact B');

-- Insert Menu Categories
INSERT INTO MenuCategories (description) VALUES 
('Vegan'),
('Classic');

-- Insert Menu Types
INSERT INTO MenuTypes (description) VALUES 
('Buffet'),
('A la carte');

-- Insert Ingredients
INSERT INTO Ingredients (name, stock) VALUES 
('Meat', 1000),
('Potatoes', 5000),
('Carrots', 2000);

-- Insert Menus
INSERT INTO Menus (name, category_ID, menu_type_ID, supplier_ID, base_price, min_guest_count, is_starter, is_main_course, is_dessert, notes)
VALUES 
('Menu 1', 1, 1, 1, 1500, 10, TRUE, TRUE, FALSE, 'Menu 1 notes'),
('Menu 2', 2, 2, 2, 2500, 15, FALSE, TRUE, TRUE, 'Menu 2 notes');

-- Insert Menu Ingredients
INSERT INTO MenuIngredients (menu_ID, ingredient_ID, grams_used) VALUES 
(1, 1, 200),
(1, 2, 300),
(2, 3, 150);

-- Insert User Roles
INSERT INTO UserRoles (role_name) VALUES 
('Admin'),
('User');

-- Insert Users
INSERT INTO Users (username, password, role_ID, phone_number) VALUES 
('ivo', '1234', 1, '351111111'),
('ana', 'abcd', 2, '351222222');

-- Insert Customers
INSERT INTO Customers (DNI, first_name, last_name, email, phone, notes) VALUES 
('12345678', 'Juan', 'Perez', 'juan@mail.com', '351333333', 'VIP customer');

-- Insert Availability
INSERT INTO Availability (date_available) VALUES 
('2025-09-10'),
('2025-09-11');

-- Insert Staff Types
INSERT INTO StaffTypes (description) VALUES 
('Chef'),
('Waiter');

-- Insert Staff
INSERT INTO Staff (DNI, first_name, last_name, age, availability_ID, staff_type_ID, phone, email) VALUES 
(101, 'Carlos', 'Lopez', 30, 1, 1, '351444444', 'carlos@mail.com');

-- Insert Specialities
INSERT INTO Specialities (name) VALUES 
('Pastry'),
('Meat dishes');

-- Insert Staff Specialities
INSERT INTO StaffSpecialities (specialty_ID, staff_ID) VALUES 
(1, 101),
(2, 101);

-- Insert Status
INSERT INTO Status (description) VALUES 
('Confirmed'),
('Pending'),
('Cancelled');

-- Insert Event Types
INSERT INTO EventTypes (description) VALUES 
('Wedding'),
('Birthday'),
('Corporate');

-- Insert Events
INSERT INTO Events (start_date, end_date, guest_count, event_type_ID, menu_type_ID, supplier_ID, service_sheet_link, customer_ID, menu_ID, status_ID)
VALUES 
('2025-10-25 12:00:00', '2025-10-25 18:00:00', 100, 1, 1, 1, 'http://example.com/sheet1', '12345678', 1, 1),
('2025-11-15 19:00:00', '2025-11-15 23:00:00', 50, 2, 2, 2, 'http://example.com/sheet2', '12345678', 2, 2);

-- Insert Event Staff
INSERT INTO EventStaff (staff_ID, event_ID) VALUES 
(101, 1),
(101, 2);

-- Insert Technical Supplies
INSERT INTO TechnicalSupplies (name, brand, available_quantity) VALUES 
('Projector', 'Epson', 5),
('Speaker', 'Sony', 10);

-- Insert Event Supplies
INSERT INTO EventSupplies (supply_ID, event_ID, quantity_used) VALUES 
(1, 1, 1),
(2, 1, 2);

-- Insert Cities
INSERT INTO Cities (description) VALUES 
('Cordoba'),
('Buenos Aires');

-- Insert Vehicles
INSERT INTO Vehicles (number_plate, model_year, availability, load_capacity) VALUES 
('ABC123', 2020, TRUE, 1000),
('XYZ789', 2021, TRUE, 1500);

-- Insert Logistics
INSERT INTO Logistics (vehicle_ID) VALUES 
(1);

-- Insert Locations
INSERT INTO Locations (city_ID, distance_from_headquarters_km, logistics_ID) VALUES 
(1, 15.5, 1);

-- Insert Logistics Vehicles
INSERT INTO Logistics_Vehicles (logistics_ID, vehicle_ID) VALUES 
(1, 1);

-- Insert Movements
INSERT INTO Movements (date, is_income, amount, description, event_ID, user_ID) VALUES 
('2025-10-08 15:00:00', TRUE, 5000.00, 'Initial payment', 1, 1),
('2025-10-20 10:00:00', TRUE, 2500.00, 'Final payment', 2, 1);