databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1361483424619-1") {
		addColumn(tableName: "lista_cobro_detalle") {
			column(name: "amortizacion_fecha_cobro_id", type: "number(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1361483424619-19") {
		addForeignKeyConstraint(baseColumnNames: "amortizacion_fecha_cobro_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAF19A51A8D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tabla_amortizacion_registro", referencesUniqueColumn: "false")
	}
}
