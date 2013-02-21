databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1361467026052-1") {
		addColumn(tableName: "prestamo") {
			column(name: "fecha_instalacion", type: "timestamp")
		}
	}

}
