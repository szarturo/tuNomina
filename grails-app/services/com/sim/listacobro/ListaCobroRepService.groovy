package com.sim.listacobro

import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.apache.commons.io.FileUtils

class ListaCobroRepService {

	def jasperService

	public def reporteListaCobro() {
		def reportDef = new JasperReportDef(
			name:'listaCobro/reportPrueba.jrxml',
			fileFormat:JasperExportFormat.XLSX_FORMAT,
			reportData: ListaCobro.findAll()
		)
        FileUtils.writeByteArrayToFile(new File("${System.getProperty('user.home')}/Documents/tuNomina/test.xlsx"), jasperService.generateReport(reportDef).toByteArray())
	}

	public def reporteListaCobroBrowser() {
		def reportDef = new JasperReportDef(
			name:'listaCobro/reportPrueba.jrxml',
			fileFormat:JasperExportFormat.PDF_FORMAT,
			reportData: ListaCobro.findAll()
		)
        return jasperService.generateReport(reportDef)
	}	
}
