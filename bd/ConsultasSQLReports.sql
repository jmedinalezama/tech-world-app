-- consulta para los reportes

-- consulta para factura de venta 
select 
	concat('FACTURA_', v.idventa) as idFactura, 
    v.fecha as fechaFactura, 
    v.hora as horaFactura,
    v.subtotal as subtotalVenta,
    v.igv as igvVenta,
    v.total as totalVenta,
    c.nombrecliente as nombreCliente, 
    c.apellidocliente as apellidoCliente, 
    c.dnicliente as dniCliente, 
    c.direccioncliente as direccionCliente, 
    c.telefonocliente as telefonoCliente, 
    c.correocliente as correoCliente,
    u.nombreusuario as nombreUsuario, 
    u.apellidousuario as apellidoUsuario
from tbventa v
inner join tbcliente c on v.idcliente = c.idcliente
inner join tbusuario u on v.idusuario = u.idusuario
where v.idventa = 'VENTA2';

--- consulta para el detalle de la venta
select 
	dt.idventa as idventa,
	e.codigoequipo as codigoequipo, 
    e.nombreequipo as nombreequipo, 
    dt.precio as precio, 
    dt.cantidad as cantidad, 
    (dt.precio * dt.cantidad) as subtotal 
from tbdetalle_venta dt 
inner join tbequipo e on dt.idequipo = e.idequipo
where dt.idventa = 'VENTA2';


-- consulta para el reporte de clientes
select
    idcliente,
    nombrecliente,
	apellidocliente,	
	dnicliente,
	direccioncliente,
	telefonocliente,
	correocliente,
concat(nombrecliente, ' ', apellidocliente) as nombres
 from tbcliente;
 
-- consulta para el reporte de equipos
select
    idequipo,
    codigoequipo,
    nombreequipo,
    precio,
    stock
from tbequipo;

-- consulta para el reporte de usuarios
select
    u.idusuario,
    u.nombreusuario,
    u.apellidousuario,
    u.dniusuario,
    u.direccionusuario,
    u.correousuario,
    u.userusuario,
    u.passwordusuario,
    u.idcargo,
    c.tipocargo,
    concat(u.nombreusuario, ' ', u.apellidousuario) as nombreapellido
from tbusuario u
inner join tbcargo c on u.idcargo = c.idcargo;