
DROP DATABASE IF EXISTS `bdequiposelectronicos`;
CREATE DATABASE `bdequiposelectronicos`;

USE `bdequiposelectronicos`;

DROP TABLE IF EXISTS `tbcargo`;
CREATE TABLE `tbcargo` (
  `idcargo` int NOT NULL AUTO_INCREMENT,
  `tipocargo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idcargo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tbcargo` WRITE;
INSERT INTO `tbcargo` VALUES (1,'Administrador'),(2,'Vendedor'),(3,'Proveedor');
UNLOCK TABLES;

DROP TABLE IF EXISTS `tbcliente`;
CREATE TABLE `tbcliente` (
  `idcliente` int NOT NULL AUTO_INCREMENT,
  `nombrecliente` varchar(30) DEFAULT NULL,
  `apellidocliente` varchar(30) DEFAULT NULL,
  `dnicliente` varchar(10) DEFAULT NULL,
  `direccioncliente` varchar(50) DEFAULT NULL,
  `telefonocliente` varchar(20) DEFAULT NULL,
  `correocliente` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tbcliente` WRITE;
INSERT INTO `tbcliente` VALUES (1,'Juan','Paredes','32123212','Trujillo','986589874','juan@gmail.com'),(2,'Naomy','Perez','63565852','Chimbote','985852475','naomy@hotmail.com'),(3,'Jorge','Garcia','25014578','Lima','985898989','jorge@gmail.com'),(4,'Lorena','Diaz','85847510','Viru','985004500','lorena@hotmail.com');
UNLOCK TABLES;


DROP TABLE IF EXISTS `tbdetalle_venta`;
CREATE TABLE `tbdetalle_venta` (
  `iddetalle_venta` int NOT NULL AUTO_INCREMENT,
  `idventa` varchar(10) DEFAULT NULL,
  `idequipo` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`iddetalle_venta`),
  KEY `idventa` (`idventa`),
  KEY `idequipo` (`idequipo`),
  CONSTRAINT `tbdetalle_venta_ibfk_1` FOREIGN KEY (`idventa`) REFERENCES `tbventa` (`idventa`),
  CONSTRAINT `tbdetalle_venta_ibfk_2` FOREIGN KEY (`idequipo`) REFERENCES `tbequipo` (`idequipo`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `tbdetalle_venta` WRITE;
INSERT INTO `tbdetalle_venta` VALUES (1,'VENTA1',4,3,50.00),(2,'VENTA1',5,5,45.00),(3,'VENTA1',3,4,50.00),(4,'VENTA2',5,2,150.00),(5,'VENTA2',1,3,50.00),(6,'VENTA3',4,3,50.00),(7,'VENTA3',2,2,150.00),(8,'VENTA4',3,2,150.00),(9,'VENTA4',5,1,200.00),(10,'VENTA4',1,5,45.00),(11,'VENTA5',4,2,50.00),(12,'VENTA5',5,2,50.00),(13,'VENTA5',2,3,50.00),(14,'VENTA6',5,3,50.00),(15,'VENTA6',2,3,150.00);
UNLOCK TABLES;

DROP TABLE IF EXISTS `tbequipo`;
CREATE TABLE `tbequipo` (
  `idequipo` int NOT NULL AUTO_INCREMENT,
  `codigoequipo` varchar(20) DEFAULT NULL,
  `nombreequipo` varchar(30) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`idequipo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tbequipo` WRITE;
INSERT INTO `tbequipo` VALUES (1,'E1','Parlantes',50.00,15),(2,'E2','Monitor',150.00,16),(3,'E3','Mouse',35.00,31),(4,'E4','Teclado',50.00,18),(5,'E5','SSD',200.00,10);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tbusuario`;
CREATE TABLE `tbusuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombreusuario` varchar(30) DEFAULT NULL,
  `apellidousuario` varchar(30) DEFAULT NULL,
  `dniusuario` varchar(10) DEFAULT NULL,
  `direccionusuario` varchar(50) DEFAULT NULL,
  `correousuario` varchar(50) DEFAULT NULL,
  `userusuario` varchar(20) DEFAULT NULL,
  `passwordusuario` varchar(20) DEFAULT NULL,
  `idcargo` int DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `idcargo` (`idcargo`),
  CONSTRAINT `tbusuario_ibfk_1` FOREIGN KEY (`idcargo`) REFERENCES `tbcargo` (`idcargo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tbusuario` WRITE;
INSERT INTO `tbusuario` VALUES (1,'Noe','Med','85475210','Lima','noe@gmail.com','admin','123',1),(2,'Anna','Diaz','65852145','Trujillo','anna@gmail.com','vendedor','123',2);
UNLOCK TABLES;


DROP TABLE IF EXISTS `tbventa`;
CREATE TABLE `tbventa` (
  `idventa` varchar(10) NOT NULL,
  `idcliente` int DEFAULT NULL,
  `idusuario` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `igv` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idventa`),
  KEY `idcliente` (`idcliente`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `tbventa_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `tbcliente` (`idcliente`),
  CONSTRAINT `tbventa_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `tbusuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tbventa` WRITE;
INSERT INTO `tbventa` VALUES ('VENTA1',1,1,'2022-12-02','22:57:51',575.00,103.50,678.50),('VENTA2',1,1,'2022-12-02','23:01:08',450.00,81.00,531.00),('VENTA3',1,2,'2022-12-02','23:02:32',450.00,81.00,531.00),('VENTA4',1,2,'2022-12-03','12:31:33',725.00,130.50,855.50),('VENTA5',4,1,'2022-12-03','12:32:20',350.00,63.00,413.00),('VENTA6',3,2,'2022-12-03','12:32:48',600.00,108.00,708.00);
UNLOCK TABLES;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ActualizarCargo`(
	in intidcargo int,
    in srttipocargo varchar(20)
)
update TBCargo
    set tipocargo = srttipocargo
    where idcargo = intidcargo ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ActualizarCliente`(
	in intidcliente int,
	in strnombrecliente varchar(30),
    in strapellidocliente varchar(30),
    in strdnicliente varchar(10),
    in strdireccioncliente varchar(50),
    in strtelefonocliente varchar(20),
    in strcorreocliente varchar(50)
)
update TBCliente
	set nombrecliente = strnombrecliente,
		apellidocliente = strapellidocliente,
        dnicliente = strdnicliente,
        direccioncliente = strdireccioncliente,
        telefonocliente = strtelefonocliente,
        correocliente = strcorreocliente
	where
		idcliente = intidcliente ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ActualizarEquipo`(
	in intidequipo int,
	in strcodigoequipo varchar(20),
    in strnombreequipo varchar(30),
    in dcmprecio decimal(10,2),
    in intstock int
)
update TBEquipo
	set codigoequipo = strcodigoequipo,
		nombreequipo = strnombreequipo,
        precio = dcmprecio,
        stock = intstock
	where 
		idequipo = intidequipo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ActualizarStockEquipo`(
	in intidequipo int,
    in intcantidad int
)
update tbequipo
    set stock = stock - intcantidad
    where idequipo = intidequipo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ActualizarUsuario`(
	in intidusuario int,
	in strnombreusuario varchar(30),
    in strapellidousuario varchar(30),
    in strdniusuario varchar(10),
    in strdireccionusuario varchar(50),
    in strcorreousuario varchar(50),
    in srtuserusuario varchar(20),
    in strpasswordusuario varchar(20),
    in intidcargo int
)
update TBUsuario
    set nombreusuario = strnombreusuario,
		apellidousuario = strapellidousuario,
        dniusuario = strdniusuario,
        direccionusuario = strdireccionusuario,
        correousuario = strcorreousuario,
        userusuario = srtuserusuario,
        passwordusuario = strpasswordusuario,
        idcargo = intidcargo
	where
		idusuario = intidusuario ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarCargoPorId`(
	in intidcargo int
)
select * from TBCargo
    where idcargo = intidcargo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarClientePorId`(
	in intidcliente int
)
select * from TBCliente
    where idcliente = intidcliente ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarDetalleVentaPorIdVenta`(
	in stridventa varchar(10)
)
select * from tbdetalle_venta
    where idventa = stridventa ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarEquipoPorCodigoEquipo`(
	in strcodigoequipo varchar(20)
)
select * from TBEquipo 
	where codigoequipo = strcodigoequipo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarEquipoPorId`(
	in intidequipo int
)
select * from TBEquipo
    where idequipo = intidequipo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarUsuarioPorId`(
	in intidusuario int
)
select * from TBUsuario
    where idusuario = intidusuario ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarUsuarioPorIdCargo`(
	in intidcargo int
)
select * from TBUsuario
	where idcargo = intidcargo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_BuscarUsuarioPorUserAndPassword`(
	in struserusuario varchar(20),
    in strpasswordusuario varchar(20)
)
select * from TBUsuario
    where userusuario = struserusuario and passwordusuario = strpasswordusuario ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_CincoEquiposMasVendidos`()
select 
	e.nombreequipo as nombreequipo,  
    count(*) as nroventasequipo, 
    sum(cantidad) as nroequiposvendidos, 
    sum(dt.precio * cantidad) as montoadquirido
from tbdetalle_venta dt
inner join tbequipo e on dt.idequipo = e.idequipo
group by e.nombreequipo
order by nroequiposvendidos desc
limit 5 ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_CincoVentasMayorMonto`()
select 
	v.idventa, 
    concat(c.nombrecliente, ' ', c.apellidocliente) as cliente, 
    concat(u.nombreusuario, ' ', u.apellidousuario) as usuario, 
    v.fecha, 
    sum(total) as totalmonto
from tbventa v
inner join tbcliente c on c.idcliente = v.idcliente
inner join tbusuario u on u.idusuario = v.idusuario
group by idventa
order by totalmonto desc
limit 5 ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_EliminarCargo`(
	in intidcargo int
)
delete from TBCargo 
    where idcargo = intidcargo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_EliminarCliente`(
	in intidcliente int
)
delete from TBCliente
	where idcliente = intidcliente ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_EliminarEquipo`(
	in intidequipo int
)
delete from TBEquipo
    where idequipo = intidequipo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_EliminarUsuario`(
	in intidusuario int
)
delete from TBUsuario
    where idusuario = intidusuario ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InsertarCargo`(
	in strtipocargo varchar(20)
)
insert into TBCargo values(null, strtipocargo) ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InsertarCliente`(
	in strnombrecliente varchar(30),
    in strapellidocliente varchar(30),
    in strdnicliente varchar(10),
    in strdireccioncliente varchar(50),
    in strtelefonocliente varchar(20),
    in strcorreocliente varchar(50)
)
insert into TBCliente
    values(null, strnombrecliente, strapellidocliente, strdnicliente, strdireccioncliente, strtelefonocliente, strcorreocliente) ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InsertarDetalleVenta`(
	in stridventa varchar(10),
    in intidequipo int,
    in intcantidad int,
    in dcmprecio decimal(10,2)
)
insert into TBDetalle_Venta
    values (null, stridventa, intidequipo, intcantidad, dcmprecio) ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InsertarEquipo`(
	in strcodigoequipo varchar(20),
    in strnombreequipo varchar(30),
    in dcmprecio decimal(10,2),
    in intstock int
)
insert into TBEquipo
    values (null, strcodigoequipo, strnombreequipo, dcmprecio, intstock) ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InsertarUsuario`(
	in strnombreusuario varchar(30),
    in strapellidousuario varchar(30),
    in strdniusuario varchar(10),
    in strdireccionusuario varchar(50),
    in strcorreousuario varchar(50),
    in srtuserusuario varchar(20),
    in strpasswordusuario varchar(20),
    in intidcargo int
)
insert into TBUsuario 
    values(null, strnombreusuario, strapellidousuario, strdniusuario, strdireccionusuario, 
		strcorreousuario, srtuserusuario, strpasswordusuario, intidcargo) ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InsertarVenta`(
	in stridventa varchar(10),
	in intidcliente int,
    in intidusuario int, 
    in datefecha date,
    in timehora time,
	in dcmsubtotal decimal(10,2),
	in dcmigv decimal(10,2),
	in dcmtotal decimal(10,2)
)
insert into TBVenta
	values (stridventa, intidcliente, intidusuario, datefecha, timehora, dcmsubtotal, dcmigv, dcmtotal) ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarCargos`()
select * from TBCargo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarClientes`()
select * from TBCliente ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarEquipos`()
select * from TBEquipo ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarTodosClientes`()
select 0 as idcliente, 'Todos' as nombrecliente
union all
select idcliente, concat(nombrecliente, ' ', apellidocliente) as nombrecliente from tbcliente ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarTodosUsuarios`()
select 0 as idusuario, 'Todos' as nombreusuario
union all
select idusuario, concat(nombreusuario, ' ', apellidousuario) as nombreusuario from tbusuario ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarUsuarios`()
select * from TBUsuario ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarVentas`()
select * from TBVenta
order by idventa asc ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarVentasPorUsuarioClienteFechas`(
	in intidcliente int,
    in intidusuario int,
    in datfechainicio date,
    in datfechafin date
)
select * from tbventa v
    where 
		(v.idcliente = intidcliente or 0 = intidcliente)
        and
		(v.idusuario = intidusuario or 0 = intidusuario)
        and
        (v.fecha between datfechainicio and datfechafin)
	order by v.idventa asc ;;
DELIMITER ;

