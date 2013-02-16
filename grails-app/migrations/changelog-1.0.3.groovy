databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1360984937428-1") {
		addColumn(tableName: "tabla_amortizacion_registro") {
			column(name: "lista_cobro_primer_pago_id", type: "number(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360984937428-2") {
		addColumn(tableName: "tabla_amortizacion_registro") {
			column(name: "lista_cobro_real_id", type: "number(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360984937428-20") {
		addForeignKeyConstraint(baseColumnNames: "lista_cobro_primer_pago_id", baseTableName: "tabla_amortizacion_registro", constraintName: "FKFC4201AD75A63987", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lista_cobro", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360984937428-21") {
		addForeignKeyConstraint(baseColumnNames: "lista_cobro_real_id", baseTableName: "tabla_amortizacion_registro", constraintName: "FKFC4201ADB7541A3E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lista_cobro", referencesUniqueColumn: "false")
	}
}
