package com.sim.tesoreria

import java.util.List;

import mx.com.creditoreal.ws.client.Client
import mx.com.creditoreal.ws.dto.ComprasDia
import mx.com.creditoreal.ws.dto.SolicitudDia;

class DispersionController {

	def index() {
	}

	def show() {
		log.info("-- init show --");
	}
	
	def consultar() {
		log.info("-- init consultar -- ${params}");
		System.out.println(":P")

		// Invoca WS para obtener los creditos comprados del dia
		Client cliente = new Client(PfinCatParametro.findByClaveMedio("SistemaMtn").pruebasClienteWsCr);
		List<ComprasDia> compras=cliente.getComprasDia("9999", "20121213", "", "");
		for(ComprasDia c: compras){
			System.out.println(c.getNombre());
		}
				
		log.info("-- end consultar -- ${params}");
		
		redirect(action: "show")
	}

}
