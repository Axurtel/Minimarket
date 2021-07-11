drop database if exists minimarket;
create database minimarket;
use minimarket;

create table sLote (
	lote tinyint primary key,
    descripcion varchar(100)
);

insert into sLote values
	(1, "Útiles Escolares"),
    (2, "Alimentos Portadores de Calorías"),
	(3, "Elementos Sanitarios"),
    (4, "Derivados de casa"),
    (5, "Sanidad"),
    (6, "Derivados Cualquiera");

create table producto (
	codigo int primary key not null,
    producto varchar(20) not null,
    marca varchar(20) not null,
    lote int not null references sLote,
    precio float not null
);

insert into producto (codigo, producto, marca, lote, precio) values
	(101, "Desodorante", "Nivea", 3, 3.00),
	(102, "Jabón", "Nivea", 3, 2.1),
	(103, "Barbie", "Mattel", 6, 7.00),
    (105, "Maleta Pequeña", "Delsey", 4, 32.00),
    (301, "Rasuradora", "Gillete", 5, 3.20), 
    (302, "Colores", "FaberCastell", 1, 6.30),
    (307, "Toalla", "Elite", 3, 5.00),
    (404, "Gaseosa", "Pepsi", 2, 1.50),
    (205, "Lapicero", "Pillot", 1, 3.50),
    (200, "Cuaderno", "Norma", 1, 5.00),
    (215, "Borrador", "Artesco", 1, 1.00),
    (201, "Cargador", "Samsumg", 6, 5.70),
    (310, "Papas Fritas", "Lays", 2, 1.30),
    (320, "Galleta", "San Jorge", 2, 0.50),
    (401, "Shampoo", "Sedal", 3, 6.10);
    
create table cliente (
id int primary key not null,
nombre varchar(40),
apellido varchar(40),
direccion varchar(60),
dni char(8) unique not null
);

insert into cliente (id, nombre, apellido, direccion, dni) values
	(1, "Laura", "Ramirez", "Av.Larval 281", 71891294),
    (2, "Esteban", "Lopez", "Av.La encalada 102", 81749215),
    (3, "Alejandra", "Jacob", "La marina", 18391023),
    (4, "Karina", "Sandoval", "Santa rosa", 58193016),
    (5, "Gonzalo", "Leon", "San isidro", 28491873),
    (6, "Donatto", "Minaya", "Miraflores", 72701952),
    (7, "Rosa", "Muñez", "Ventanilla", 17129382),
    (8, "Estefano", "Garcia", "Jr.Tarata 101", 19582781),
    (9, "Carla", "Benites", "Chorrillos", 82930192),
    (10, "Josh", "Pardo", "Jr.España 453", 28591048),
    (11, "Estefany", "Wistong", "Callao La perla", 18592850),
    (12, "Luz", "Romero", "San Borja", 65928429),
    (13, "Lucas", "Juarez", "Lima", 71291045);

create table venta (
	codigo int not null,
    producto varchar(40),
    cantidad int,
    precio float not null,
    totalPagar float not null
);

insert into venta (codigo, producto, cantidad, precio, totalPagar) values
	(102, "Jabón", 3, 2.10, 6.30),
	(302, "Colores", 2, 6.30, 12.60),
    (215, "Borrador", 4, 1.00, 4.00),
    (301, "Rasuradora", 1, 3.20, 3.20),
    (101, "Desodorante", 5, 3.00, 15.00),
    (205, "Lapicero", 4, 3.50, 14.00),
    (303, "Papas Fritas", 3, 1.30, 3.90),
    (102, "Jabón", 5, 2.10, 10.50),
    (320, "Galleta", 2, 0.50, 1.00),
	(404, "Gaseosa", 2, 1.50, 3.00);
    
create table recepcion (
	sentencia varchar(50) not null,
    emisor varchar(50) not null,
    cargo varchar(30) not null,
    razon varchar(100) not null,
    fecha date not null
);

insert into recepcion (sentencia, emisor, cargo, razon, fecha) values
	("X3-FG4-0WM", "Emilson", "Dueño", "Alquiler de local", "2020/09/15"),
    ("L1-35T-NXZ", "Lina", "Proveedor", "Entrega de Productos bimestral", "2020/10/03"),
	("1R-POO-301", "Gonzalo", "Administrador", "Mantenimiento", "2020/10/10"),
    ("7V-MVC-2B6", "Miriam", "Proveedor", "Entrega por pedido de solicitud", "2020/09/15"),
    ("KR-9E0-AWX", "Donatto", "Administrador", "Actualización de Programa", "2020/11/10"),
    ("OP-4VL-AAC", "Aldair", "Administrador", "Nuevos Botones", "2020/08/27"),
	("L1-35T-NXZ", "Lina", "Proveedor", "Entrega de Productos bimestral", "2020/12/03");
    
-- > Producto
Delimiter $
create procedure selectProducto()
begin
	select codigo, producto, marca, lote, format(precio,2) as 'precio' from producto;
end $

Delimiter $
create procedure moduleProd(thisValor int, thisCodigo int)
begin
	#eliminar
	if (thisValor = 1) then
		delete from producto where codigo = thisCodigo;
	end if;
    
    #buscar
    if (thisValor = 2) then
		select * from producto where codigo = thisCodigo;
    end if;
end $

Delimiter $
create procedure agregarProducto 
(thisCodigo int, thisProducto varchar(20), thisMarca varchar(20), thisLote int, thisPrecio float)
begin
	insert into producto (codigo, producto, marca, lote, precio)
    values (thisCodigo, thisProducto, thisMarca, thisLote, thisPrecio);
end $

Delimiter $
create procedure modificarProducto 
(thisProducto varchar(20), thisMarca varchar(20), thisLote int, thisPrecio float, thisCodigo int)
begin
	update producto set 
		producto = thisProducto, 
        marca = thisMarca, 
        lote = thisLote, 
        precio = thisPrecio
    where codigo = thisCodigo;
end $
-- ---------------------------------------------
    
-- > Cliente
Delimiter %
create procedure agregarCliente 
(thisId int, thisNombre varchar(40), thisApellido varchar(40), thisDireccion varchar(60), thisDni char(8))
begin
	insert into cliente (id, nombre, apellido, direccion, dni)
    values (thisId, thisNombre, thisApellido, thisDireccion, thisDni);
end %

Delimiter %
create procedure modificarCliente 
(thisNombre varchar(40), thisApellido varchar(40), thisDireccion varchar(60), thisDni char(8), thisId int)
begin
	update cliente set
        nombre = thisNombre,
        apellido = thisApellido,
        direccion = thisDireccion,
        dni = thisDni
	where id = thisId;
end %

Delimiter %
create procedure moduleCli(thisValor int, thisId int)
begin
	#eliminar
	if (thisValor = 1) then
		delete from cliente where id = thisId;
	end if;
    
    #buscar
    if (thisValor = 2) then
		select * from cliente where id = thisId;
    end if;
end %
-- ---------------------------------------------

-- > Venta
Delimiter &
create procedure insertarVenta(thisCodigo int, thisProducto varchar(40), thisCantidad int, thisPrecio float)
begin
	declare mult float;
    set mult = thisCantidad * thisPrecio;

	insert into venta (codigo, producto, cantidad, precio, totalPagar)
    values (thisCodigo, thisProducto, thisCantidad, thisPrecio, format(mult, 2));
end &

Delimiter &
create procedure selectVenta()
begin 
	select codigo, producto, cantidad, precio, format(totalPagar, 2) from venta;
end &

Delimiter &
create procedure borrarVenta (thisCodigo int)
begin
	delete from venta where codigo = thisCodigo;
end &

Delimiter &
create procedure modificarVenta 
(thisCantidad int, thisPrecio float, thisCodigo int, thisProducto varchar(40))
begin
	declare mult float;
    set mult = thisCantidad * thisPrecio;
    
    update venta set
        cantidad = thisCantidad,
        totalPagar = mult
	where codigo = thisCodigo and producto = thisProducto;
end &

Delimiter &
create procedure buscarVenta(thisCodigo int)
begin
	select * from venta where codigo = thisCodigo;
end &
-- --------------------------------------------------------------

-- > Recepcion
Delimiter |
create procedure insertarRecepcion(thisSentencia varchar(50), thisEmisor varchar(50), thisCargo varchar(30), thisRazon varchar(100), thisFecha date)
begin
	insert into recepcion(sentencia, emisor, cargo, razon, fecha)
    values (thisSentencia, thisEmisor, thisCargo, thisRazon, thisFecha);
end |

Delimiter |
create procedure eliminarRecepcion(thisSentencia varchar(50), thisEmisor varchar(50), thisCargo varchar(30), thisRazon varchar(100), thisFecha varchar(10))
begin
	delete from recepcion where (sentencia = thisSentencia and emisor = thisEmisor and cargo = thisCargo and razon = thisRazon and fecha = thisFecha);
end |

# Modo SuperUsuario
Delimiter \
create procedure spDeleteClient()
begin
	truncate table cliente;
end \

Delimiter \
create procedure spDeleteProd()
begin
	truncate table producto;
end \

Delimiter \
create procedure spDeleteRecepcion()
begin
	truncate table recepcion;
end \

Delimiter \
create procedure spDeleteTransaccion()
begin
	truncate table venta;
end \
