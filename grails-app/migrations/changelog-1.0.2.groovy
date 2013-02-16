databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1360980933847-740") {
		dropColumn(columnName: "LISTA_COBRO_ID", tableName: "TABLA_AMORTIZACION_REGISTRO")
	}

	changeSet(author: "miguel (generated)", id: "1360980933847-2") {
		addColumn(tableName: "tabla_amortizacion_registro") {
			column(name: "lista_cobro_fecha_cobro_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360980933847-21") {
		addForeignKeyConstraint(baseColumnNames: "lista_cobro_fecha_cobro_id", baseTableName: "tabla_amortizacion_registro", constraintName: "FKFC4201ADA67F89B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lista_cobro", referencesUniqueColumn: "false")
	}

}
