databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1361065908206-1") {
		addColumn(tableName: "lista_cobro_detalle") {
			column(name: "amortizacion_primer_pago_id", type: "number(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1361065908206-21") {
		addForeignKeyConstraint(baseColumnNames: "amortizacion_primer_pago_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAF84E35B79", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tabla_amortizacion_registro", referencesUniqueColumn: "false")
	}

}
