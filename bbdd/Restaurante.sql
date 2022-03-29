create or REPLACE DATABASE `restaurante` /*!40100 DEFAULT CHARACTER SET latin1 */;


create table empleado(
	id int(10) not null auto_increment,
	codigo_empleado varchar(10),
	nombre varchar(50) not null,
	apellidos varchar(100) not  null,
	primary key (id)
);


create table mesa(
	id int(10) not null auto_increment,
	num_mesa int(10),
	primary key (id)
);

create table producto(
	id int(10) not null auto_increment,
	descripcion varchar(150) not null,
	tipo int(1),
	tipo_producto varchar(50) not null,
	primary key (id)
);

create table tipo_plato_producto(
	id int(10) not null auto_increment,
	media_racion double not null,
	racion double not null,
	tapa double not null,
	id_producto int(10) not null,
	primary key (id),
	constraint producto_fk2 foreign key(id_producto) references producto(id)
);


create table comanda(
	id int(10) not null auto_increment,
	codigo_comanda varchar(10),
	id_mesa int(10) not null,
	id_empleado int(10) not  null,
	primary key (id),
	constraint mesa_fk foreign key (id_mesa) references mesa(id),
	constraint empleado_fk1 foreign key (id_empleado) references empleado(id)
);

create table linea_comanda(
	id_comanda int(10) not null,
	id_producto int(10) not null,
	cantidad_pedida int (10) not null,
	cantidad_servida int(10) not null default 0,
	constraint comanda_fk3 foreign key (id_comanda) references comanda(id),
	constraint producto_fk4 foreign key (id_producto) references producto(id)
);
