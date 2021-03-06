package com.sim.credito

class PrestamoCrCartera {

	String consecutivo
	String claveCliente
	String numeroOperacion
	String nombre
	String apePat
	String apeMat
	Date fechaNac
	String rfcCliente
	String calleDom
	String numIntDom
	String numExtDom
	String coloniaDom
	String localidadDom
	Double cveMunicipioDom
	Double cveEstadoDom
	Double codPostalDom
	String tipPropiedad
	Double atgDomicilio
	String telDomicilio
	String edoCivil
	String nomConyuge
	String idePresupuestal
	Double numPersonal
	String cenTrabajo
	String telTrabajo
	Double atgTrabajo
	String calleTrab
	String numExtTra
	String numIntTra
	String coloniaTra
	String localidadTra
	Double cveMunicipioTra
	Double cveEstadoTra
	Double codPostalTra
	Double ingresoBruto
	Double deducciones
	Double ingresoNeto
	Date fecRegistro
	String promocion
	Double impCreditoSol
	Integer numParcialidades
	Double impDescuento
	Double intDescuento
	String claveSucursal
	String claveCia
	Integer estatusDap
	Integer estatusSolicitud
	String ref1Nombre
	String ref1ApePat
	String ref1ApeMat
	String ref1Domicilio
	String ref1Telefono
	String ref2Nombre
	String ref2ApePat
	String ref2ApeMat
	String ref2Domicilio
	String ref2Telefono
	String ref3Nombre
	String ref3ApePat
	String ref3ApeMat
	String ref3Domicilio
	String ref3Telefono
	Double numAgente
	Double cveSupervisor
	String claveCesion
	Double importeCedido
	Date fechaDeCompra
	String fechaDispersion	

	static belongsTo = [prestamo : Prestamo]

    static constraints = {
     consecutivo nullable:true
	 claveCliente nullable:true
	 numeroOperacion nullable:true
	 nombre nullable:true
	 apePat nullable:true
	 apeMat nullable:true
	 fechaNac nullable:true
	 rfcCliente nullable:true
	 calleDom nullable:true
	 numIntDom nullable:true
	 numExtDom nullable:true
	 coloniaDom nullable:true
	 localidadDom nullable:true
	 cveMunicipioDom nullable:true
	 cveEstadoDom nullable:true
	 codPostalDom nullable:true
	 tipPropiedad nullable:true
	 atgDomicilio nullable:true
	 telDomicilio nullable:true
	 edoCivil nullable:true
	 nomConyuge nullable:true
	 idePresupuestal nullable:true
	 numPersonal nullable:true
	 cenTrabajo nullable:true
	 telTrabajo nullable:true
	 atgTrabajo nullable:true
	 calleTrab nullable:true
	 numExtTra nullable:true
	 numIntTra nullable:true
	 coloniaTra nullable:true
	 localidadTra nullable:true
	 cveMunicipioTra nullable:true
	 cveEstadoTra nullable:true
	 codPostalTra nullable:true
	 ingresoBruto nullable:true
	 deducciones nullable:true
	 ingresoNeto nullable:true
	 fecRegistro nullable:true
	 promocion nullable:true
	 impCreditoSol nullable:true
	 numParcialidades nullable:true
	 impDescuento nullable:true
	 intDescuento nullable:true
	 claveSucursal nullable:true
	 claveCia nullable:true
	 estatusDap nullable:true
	 estatusSolicitud nullable:true
	 ref1Nombre nullable:true
	 ref1ApePat nullable:true
	 ref1ApeMat nullable:true
	 ref1Domicilio nullable:true
	 ref1Telefono nullable:true
	 ref2Nombre nullable:true
	 ref2ApePat nullable:true
	 ref2ApeMat nullable:true
	 ref2Domicilio nullable:true
	 ref2Telefono nullable:true
	 ref3Nombre nullable:true
	 ref3ApePat nullable:true
	 ref3ApeMat nullable:true
	 ref3Domicilio nullable:true
	 ref3Telefono nullable:true
	 numAgente nullable:true
	 cveSupervisor nullable:true
	 claveCesion nullable:true
	 importeCedido nullable:true
	 fechaDeCompra nullable:true
	 fechaDispersion nullable:true
    }
}
