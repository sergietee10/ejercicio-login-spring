INSERT INTO departamentos (nombre, ubicacion) VALUES("Informatica", "Planta baja");
INSERT INTO departamentos (nombre, ubicacion) VALUES("Matematicas", "Planta Primera");
INSERT INTO departamentos (nombre, ubicacion) VALUES("Biologia", "Planta Tercera");
INSERT INTO jefes (dni, nombre, salario, telefono, departamento_id) VALUES("2141241Q", "Marta", 6000, 9675473, 1);
INSERT INTO jefes (dni, nombre, salario, telefono, departamento_id) VALUES("5135612R", "Mario", 7000, 453452, 2);
INSERT INTO empleados (dni, nombre, salario, telefono, departamento_id) VALUES("22415115A", "Sergio", 2000, 6346363, 1);
INSERT INTO empleados (dni, nombre, salario, telefono, departamento_id) VALUES("2515115A", "Maria", 1000, 54363, 2);
INSERT INTO empleados (dni, nombre, salario, telefono, departamento_id) VALUES("26342415E", "Juan", 5000, 7543, 2);
INSERT INTO empleados (dni, nombre, salario, telefono, departamento_id) VALUES("27564151T", "Carlos", 3000, 2324, 3);
INSERT INTO login (dni, contraseña) VALUES("2141241Q", "1234");
INSERT INTO login (dni, contraseña) VALUES("5135612R", "123");


INSERT INTO usuarios (username,password,enabled) VALUES ('sergio','$2a$10$myJ.ZUAz/DCxNAFMf3.y4O08ZqcDKIgF6RfbMRvP0PaP3U4fo3RVe',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('admin','$2a$10$JGH.9j/rfTbByT3SsgsRHObTIyz7K4rWdQ8Knos2NjytyP2hL8zFy',1);

INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);