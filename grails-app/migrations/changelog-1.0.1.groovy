databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1360636713409-1") {
		addColumn(tableName: "prestamo") {
			column(name: "monto_autorizado", type: "number(19,2)")
		}
	}

}
