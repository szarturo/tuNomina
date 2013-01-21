package com.sim.publicacion

import com.sim.usuario.Usuario
import com.sim.listacobro.ListaCobroDetalle
import com.sim.listacobro.ListaCobroDetalleEstatus
import com.sim.pfin.PfinCatParametro

class PublicacionLoteException extends RuntimeException {
	String mensaje
}

class PublicacionLoteService {

	static transactional = true
    //SERVICIO PARA RECUPERAR EL USUARIO
    def springSecurityService    	

    def publicar() {
    	Usuario usuario = springSecurityService.getCurrentUser()
    	log.info ("Usuario: ${usuario}")

    	//SE OBTIENE LA FECHA DEL MEDIO
		PfinCatParametro parametros = PfinCatParametro.findByClaveMedio("SistemaMtn")
		Date fechaMedio = parametros?.fechaMedio
		if (!fechaMedio){
			throw new PublicacionLoteException(mensaje: "No existe la fecha del medio")
		}

    	//SE CREA EL REGISTRO DE LA PUBLICACION LOTE
		PublicacionLote publicacionLote =
				new PublicacionLote(fechaMedio:  fechaMedio,
					fechaRegistro: new Date(),
					importeLote: 0,
					usuario: usuario,
					).save()


		def criteriaDetallesListaCobro = 
			ListaCobroDetalle.createCriteria()
		ArrayList detalleRegistros = 
			criteriaDetallesListaCobro.list() {
			and {
				eq("usuario",usuario)
				le("estatus",ListaCobroDetalleEstatus.APLICADO)
			}
		}

		Double totalPublicacion = 0

		detalleRegistros.each{
			log.info ("Detalle: ${it}")
			totalPublicacion = totalPublicacion + it.pago.importePago
			//SE CREA EL DETALLE DE LA PUBLICACION
			/*
			new SimCatPeriodicidad(clavePeriodicidad:  'SEMANA',
					nombrePeriodicidad: 'SEMANAL',
					cantidadPagos: 52,
					numeroDias: 7,
					).save(failOnError: true)
			*/


		}
    }
}
