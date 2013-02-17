databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1361065079871-1") {
		addColumn(tableName: "lista_cobro_detalle") {
			column(name: "amortizacion_real_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1361065079871-166") {
		dropColumn(columnName: "AMORTIZACION_ID", tableName: "LISTA_COBRO_DETALLE")
	}

	changeSet(author: "miguel (generated)", id: "1361065079871-20") {
		addForeignKeyConstraint(baseColumnNames: "amortizacion_real_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAF342715C2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tabla_amortizacion_registro", referencesUniqueColumn: "false")
	}

}
