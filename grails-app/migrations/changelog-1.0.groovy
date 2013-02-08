databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1360355282933-21") {
		createTable(tableName: "ADDRESS") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660000", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CITY", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CONTACT_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "LINE1", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "LINE2", type: "VARCHAR2(255 CHAR)")

			column(name: "STATE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "ZIP", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-22") {
		createTable(tableName: "CALL_CENTER") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660008", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CERRAR_REGISTRO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "COMENTARIOS", type: "VARCHAR2(255 CHAR)")

			column(name: "FECHA_LLAMADA", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_COBRADO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "SE_CONTACTO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-23") {
		createTable(tableName: "CONTACT") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660015", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "EMAIL", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "ESTATUS", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "FIRST_NAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_NAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PHONE", type: "VARCHAR2(255 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-24") {
		createTable(tableName: "CUSTOMER") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660021", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BALANCE", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "CREDIT_LIMIT", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-25") {
		createTable(tableName: "CUSTOMER_AUDIT") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660028", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BALANCE", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "CREATED_ON", type: "TIMESTAMP(6)")

			column(name: "CREDIT_LIMIT", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "CUSTOMER_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-26") {
		createTable(tableName: "DUMMY_COBRANZA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660035", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DETALLE_REGISTRO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FIELD1", type: "FLOAT(126)") {
				constraints(nullable: "false")
			}

			column(name: "FIELD2", type: "FLOAT(126)") {
				constraints(nullable: "false")
			}

			column(name: "FIELD3", type: "VARCHAR2(255 CHAR)")

			column(name: "FIELD4", type: "VARCHAR2(255 CHAR)")

			column(name: "FIELD5", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FIELD6", type: "VARCHAR2(255 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-27") {
		createTable(tableName: "DUMMY_CREDITO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660045", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CREDITO_OK", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "ID_CLIENTE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "ID_CREDITO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "INGRESOS", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "MONTO_PRESTAMO", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-28") {
		createTable(tableName: "DUMMY_PERSONA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660053", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "APELLIDO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CALLE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CODIGO_POSTAL", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-29") {
		createTable(tableName: "EMP_EMPLEADO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660063", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_PROMOTOR", type: "VARCHAR2(25 CHAR)")

			column(name: "ES_VIGENTE", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_INGRESO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_NOMINA", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PERSONA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PUESTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SUCURSAL_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TIPO_EMPLEADO", type: "VARCHAR2(7 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-30") {
		createTable(tableName: "EMP_PUESTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660071", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_PUESTO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DEPENDE_DE_ID", type: "NUMBER(19,0)")

			column(name: "DESCRIPCION_PUESTO", type: "VARCHAR2(150 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_PUESTO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-31") {
		createTable(tableName: "ENT_DELEGACION") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660078", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_DELEGACION", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_DELEGACION", type: "VARCHAR2(150 CHAR)")

			column(name: "NOMBRE_DELEGACION", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "SUCURSAL_ID", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-32") {
		createTable(tableName: "ENT_DEPENDENCIA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660086", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_DEPENDENCIA", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_DEPENDENCIA", type: "VARCHAR2(150 CHAR)")

			column(name: "DISTRIBUIDOR", type: "VARCHAR2(4 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_DEPENDENCIA", type: "VARCHAR2(100 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-33") {
		createTable(tableName: "ENT_DEPENDENCIA_PROMOCION") {
			column(name: "ENT_DEPENDENCIA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRO_PROMOCION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-34") {
		createTable(tableName: "ENT_OFICINA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660095", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_OFICINA", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_OFICINA", type: "VARCHAR2(150 CHAR)")

			column(name: "NOMBRE_OFICINA", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "SUCURSAL_ID", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-35") {
		createTable(tableName: "ENT_REGION") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660102", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_REGION", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_REGION", type: "VARCHAR2(150 CHAR)")

			column(name: "NOMBRE_REGION", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-36") {
		createTable(tableName: "ENT_SUCURSAL") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660111", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_SUCURSAL", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_SUCURSAL", type: "VARCHAR2(150 CHAR)")

			column(name: "ESTADO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_SUCURSAL", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "TIPO_SUCURSAL", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-37") {
		createTable(tableName: "FECHA_EVENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660119", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DEPENDENCIA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "EVENTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-38") {
		createTable(tableName: "LINE_ITEM") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660127", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "AMOUNT", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "PRODUCT_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRODUCT_PRICE", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "PURCHASE_ORDER_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "QTY_ORDERED", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-39") {
		createTable(tableName: "LISTA_COBRO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660136", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ANIO", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "DEPENDENCIA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ESTATUS_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_FIN", type: "TIMESTAMP(6)")

			column(name: "FECHA_INICIO", type: "TIMESTAMP(6)")

			column(name: "NUMERO_PAGO", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "PARCIALIDADES", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "PERIODICIDAD_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-40") {
		createTable(tableName: "LISTA_COBRO_DETALLE") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660143", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "AMORTIZACION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ESTATUS", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "LISTA_COBRO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PAGO_ID", type: "NUMBER(19,0)")

			column(name: "TIPO_EMPLEADO_DEP_ID", type: "NUMBER(19,0)")

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-41") {
		createTable(tableName: "LISTA_COBRO_PROCESO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660152", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "COMENTARIOS", type: "VARCHAR2(255 CHAR)")

			column(name: "DATE_CREATED", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "ESTATUS_LISTA_COBRO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_APLICACION", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_MEDIO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP(6)")

			column(name: "LISTA_COBRO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-42") {
		createTable(tableName: "PFIN_CAT_AFECTA_OPERACION") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660157", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_AFECTA", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "COMENTARIOS", type: "VARCHAR2(150 CHAR)")

			column(name: "DESCRIPCION_AFECTA", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-43") {
		createTable(tableName: "PFIN_CAT_CONCEPTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660165", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_CONCEPTO", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_CORTA", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_LARGA", type: "VARCHAR2(150 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "SITUACION", type: "VARCHAR2(8 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-44") {
		createTable(tableName: "PFIN_CAT_DIA_FESTIVO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660172", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_DIA_FESTIVO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_DIA_FESTIVO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "PAIS_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-45") {
		createTable(tableName: "PFIN_CAT_OPERACION") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660181", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "AFECTA_ID", type: "NUMBER(19,0)")

			column(name: "CLAVE_AFECTA_SALDO", type: "VARCHAR2(10 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_OPERACION", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_CORTA", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_LARGA", type: "VARCHAR2(150 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "SITUACION", type: "VARCHAR2(8 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-46") {
		createTable(tableName: "PFIN_CAT_OPERACION_CONCEPTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660189", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_AFECTA", type: "VARCHAR2(10 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CONCEPTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "OPERACION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SITUACION", type: "VARCHAR2(8 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-47") {
		createTable(tableName: "PFIN_CAT_PARAMETRO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660200", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_MEDIO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CONSECUTIVO_PUBLICACION", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_MEDIO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_DIGITOS_DESPLIEGA", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "OPERA_DIA_FESTIVO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "OPERA_DOMINGO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "OPERA_SABADO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRUEBAS_CLIENTE_WS_CR", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-48") {
		createTable(tableName: "PFIN_CUENTA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660207", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLIENTE_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "COMENTARIO", type: "VARCHAR2(255 CHAR)")

			column(name: "SITUACION", type: "VARCHAR2(8 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "TIPO_CUENTA", type: "VARCHAR2(7 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-49") {
		createTable(tableName: "PFIN_DIVISA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660212", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_DIVISA", type: "VARCHAR2(10 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_DIVISA", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-50") {
		createTable(tableName: "PFIN_MOVIMIENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660231", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CANCELA_TRANSACCION_ID", type: "NUMBER(19,0)")

			column(name: "CUENTA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DIVISA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_APLICACION", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_LIQUIDACION", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_OPERACION", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_REGISTRO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_NETO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "LOG_HOST", type: "VARCHAR2(255 CHAR)")

			column(name: "LOG_IP_DIRECCION", type: "VARCHAR2(255 CHAR)")

			column(name: "LOG_USUARIO", type: "VARCHAR2(255 CHAR)")

			column(name: "NOTA", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_PAGO_AMORTIZACION", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "OPERACION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PFIN_PRE_MOVIMIENTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_PAGO_ID", type: "NUMBER(19,0)")

			column(name: "REFERENCIA", type: "NUMBER(10,0)")

			column(name: "SITUACION_MOVIMIENTO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-51") {
		createTable(tableName: "PFIN_MOVIMIENTO_DET") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660239", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CONCEPTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_CONCEPTO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "MOVIMIENTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOTA", type: "VARCHAR2(80 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-52") {
		createTable(tableName: "PFIN_PRE_MOVIMIENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660255", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CUENTA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DIVISA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_APLICACION", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_LIQUIDACION", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_OPERACION", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_REGISTRO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_NETO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "LOG_HOST", type: "VARCHAR2(255 CHAR)")

			column(name: "LOG_IP_DIRECCION", type: "VARCHAR2(255 CHAR)")

			column(name: "LOG_USUARIO", type: "VARCHAR2(255 CHAR)")

			column(name: "NOTA", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_PAGO_AMORTIZACION", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "OPERACION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "REFERENCIA", type: "NUMBER(10,0)")

			column(name: "SITUACION_PRE_MOVIMIENTO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-53") {
		createTable(tableName: "PFIN_PRE_MOVIMIENTO_DET") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660262", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CONCEPTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_CONCEPTO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "NOTA", type: "VARCHAR2(80 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PRE_MOVIMIENTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-54") {
		createTable(tableName: "PFIN_SALDO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660268", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CUENTA_ID", type: "NUMBER(19,0)")

			column(name: "DIVISA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_FOTO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "SALDO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-55") {
		createTable(tableName: "PRESTAMO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660289", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "APPROVAL_STATUS", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "APROBADO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLIENTE_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "COMENTARIOS", type: "VARCHAR2(255 CHAR)")

			column(name: "CONSECUTIVO_CR", type: "VARCHAR2(255 CHAR)")

			column(name: "CORREO_SOLICITANTE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "DEDUCCIONES_MENSUALES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "DELEGACION_ID", type: "NUMBER(19,0)")

			column(name: "DEPENDENCIA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DOCUMENTOS_CORRECTOS", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "ESTATUS_SOLICITUD", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "EXPLICACION_DEVOLUCION", type: "VARCHAR2(255 CHAR)")

			column(name: "FECHA_COBRO", type: "TIMESTAMP(6)")

			column(name: "FECHA_SOLICITUD", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FOLIO_SOLICITUD", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "FORMA_DE_DISPERCION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "INCLUIR_EN_LISTAS_COBRO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP(6)")

			column(name: "MONTO_SOLICITADO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "MOVITO_RESPUESTA_CR_ID", type: "NUMBER(19,0)")

			column(name: "PERCEPCIONES_MENSUALES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "PROMOCION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "REENVIAR_SOLICITUD", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "SUCURSAL_ID", type: "NUMBER(19,0)")

			column(name: "TIPO_EMPLEADO_DEP_ID", type: "NUMBER(19,0)")

			column(name: "USUARIO_MESA_CONTROL", type: "VARCHAR2(255 CHAR)")

			column(name: "VENDEDOR_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-56") {
		createTable(tableName: "PRESTAMO_ACCESORIO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660296", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ACCESORIO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PERIODICIDAD_ID", type: "NUMBER(19,0)")

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "UNIDAD_ID", type: "NUMBER(19,0)")

			column(name: "VALOR", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-57") {
		createTable(tableName: "PRESTAMO_CR_CARTERA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660300", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "APE_MAT", type: "VARCHAR2(255 CHAR)")

			column(name: "APE_PAT", type: "VARCHAR2(255 CHAR)")

			column(name: "ATG_DOMICILIO", type: "FLOAT(126)")

			column(name: "ATG_TRABAJO", type: "FLOAT(126)")

			column(name: "CALLE_DOM", type: "VARCHAR2(255 CHAR)")

			column(name: "CALLE_TRAB", type: "VARCHAR2(255 CHAR)")

			column(name: "CEN_TRABAJO", type: "VARCHAR2(255 CHAR)")

			column(name: "CLAVE_CESION", type: "VARCHAR2(255 CHAR)")

			column(name: "CLAVE_CIA", type: "VARCHAR2(255 CHAR)")

			column(name: "CLAVE_CLIENTE", type: "VARCHAR2(255 CHAR)")

			column(name: "CLAVE_SUCURSAL", type: "VARCHAR2(255 CHAR)")

			column(name: "COD_POSTAL_DOM", type: "FLOAT(126)")

			column(name: "COD_POSTAL_TRA", type: "FLOAT(126)")

			column(name: "COLONIA_DOM", type: "VARCHAR2(255 CHAR)")

			column(name: "COLONIA_TRA", type: "VARCHAR2(255 CHAR)")

			column(name: "CONSECUTIVO", type: "VARCHAR2(255 CHAR)")

			column(name: "CVE_ESTADO_DOM", type: "FLOAT(126)")

			column(name: "CVE_ESTADO_TRA", type: "FLOAT(126)")

			column(name: "CVE_MUNICIPIO_DOM", type: "FLOAT(126)")

			column(name: "CVE_MUNICIPIO_TRA", type: "FLOAT(126)")

			column(name: "CVE_SUPERVISOR", type: "FLOAT(126)")

			column(name: "DEDUCCIONES", type: "FLOAT(126)")

			column(name: "EDO_CIVIL", type: "VARCHAR2(255 CHAR)")

			column(name: "ESTATUS_DAP", type: "NUMBER(10,0)")

			column(name: "ESTATUS_SOLICITUD", type: "NUMBER(10,0)")

			column(name: "FEC_REGISTRO", type: "TIMESTAMP(6)")

			column(name: "FECHA_DE_COMPRA", type: "TIMESTAMP(6)")

			column(name: "FECHA_DISPERSION", type: "VARCHAR2(255 CHAR)")

			column(name: "FECHA_NAC", type: "TIMESTAMP(6)")

			column(name: "IDE_PRESUPUESTAL", type: "VARCHAR2(255 CHAR)")

			column(name: "IMP_CREDITO_SOL", type: "FLOAT(126)")

			column(name: "IMP_DESCUENTO", type: "FLOAT(126)")

			column(name: "IMPORTE_CEDIDO", type: "FLOAT(126)")

			column(name: "INGRESO_BRUTO", type: "FLOAT(126)")

			column(name: "INGRESO_NETO", type: "FLOAT(126)")

			column(name: "INT_DESCUENTO", type: "FLOAT(126)")

			column(name: "LOCALIDAD_DOM", type: "VARCHAR2(255 CHAR)")

			column(name: "LOCALIDAD_TRA", type: "VARCHAR2(255 CHAR)")

			column(name: "NOM_CONYUGE", type: "VARCHAR2(255 CHAR)")

			column(name: "NOMBRE", type: "VARCHAR2(255 CHAR)")

			column(name: "NUM_AGENTE", type: "FLOAT(126)")

			column(name: "NUM_EXT_DOM", type: "VARCHAR2(255 CHAR)")

			column(name: "NUM_EXT_TRA", type: "VARCHAR2(255 CHAR)")

			column(name: "NUM_INT_DOM", type: "VARCHAR2(255 CHAR)")

			column(name: "NUM_INT_TRA", type: "VARCHAR2(255 CHAR)")

			column(name: "NUM_PARCIALIDADES", type: "NUMBER(10,0)")

			column(name: "NUM_PERSONAL", type: "FLOAT(126)")

			column(name: "NUMERO_OPERACION", type: "VARCHAR2(255 CHAR)")

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PROMOCION", type: "VARCHAR2(255 CHAR)")

			column(name: "REF1APE_MAT", type: "VARCHAR2(255 CHAR)")

			column(name: "REF1APE_PAT", type: "VARCHAR2(255 CHAR)")

			column(name: "REF1DOMICILIO", type: "VARCHAR2(255 CHAR)")

			column(name: "REF1NOMBRE", type: "VARCHAR2(255 CHAR)")

			column(name: "REF1TELEFONO", type: "VARCHAR2(255 CHAR)")

			column(name: "REF2APE_MAT", type: "VARCHAR2(255 CHAR)")

			column(name: "REF2APE_PAT", type: "VARCHAR2(255 CHAR)")

			column(name: "REF2DOMICILIO", type: "VARCHAR2(255 CHAR)")

			column(name: "REF2NOMBRE", type: "VARCHAR2(255 CHAR)")

			column(name: "REF2TELEFONO", type: "VARCHAR2(255 CHAR)")

			column(name: "REF3APE_MAT", type: "VARCHAR2(255 CHAR)")

			column(name: "REF3APE_PAT", type: "VARCHAR2(255 CHAR)")

			column(name: "REF3DOMICILIO", type: "VARCHAR2(255 CHAR)")

			column(name: "REF3NOMBRE", type: "VARCHAR2(255 CHAR)")

			column(name: "REF3TELEFONO", type: "VARCHAR2(255 CHAR)")

			column(name: "RFC_CLIENTE", type: "VARCHAR2(255 CHAR)")

			column(name: "TEL_DOMICILIO", type: "VARCHAR2(255 CHAR)")

			column(name: "TEL_TRABAJO", type: "VARCHAR2(255 CHAR)")

			column(name: "TIP_PROPIEDAD", type: "VARCHAR2(255 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-58") {
		createTable(tableName: "PRESTAMO_CR_COMPRADA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660325", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CESION", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLASIFICADOR", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_CIA", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_SUCURSAL", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_COMPRA", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_PROX_PAGO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_CEDIDO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_DESCUENTO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_INTERESES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IVA_CAPITAL", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IVA_DIFERIDO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IVA_INTERESES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "NETO_PAGADO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_OPERACION", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_SOLICITUD", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PAGO_TIENDA", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRIMER_CREDITO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "RESERVA", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "TIPO_PROMOCION", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-59") {
		createTable(tableName: "PRESTAMO_CR_RESPUESTA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660331", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_DISTRIBUIDOR", type: "VARCHAR2(255 CHAR)")

			column(name: "CLAVE_TIENDA", type: "VARCHAR2(255 CHAR)")

			column(name: "CONSECUTIVO", type: "VARCHAR2(255 CHAR)")

			column(name: "ESTATUS", type: "VARCHAR2(255 CHAR)")

			column(name: "FECHA_RECEPCION", type: "TIMESTAMP(6)")

			column(name: "FECHA_RESPUESTA", type: "TIMESTAMP(6)")

			column(name: "FOLIO", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "MONTO_AUTORIZADO", type: "NUMBER(19,2)")

			column(name: "MONTO_SOLICITADO", type: "NUMBER(19,2)")

			column(name: "MOTIVO", type: "VARCHAR2(255 CHAR)")

			column(name: "NOMBRE", type: "VARCHAR2(255 CHAR)")

			column(name: "NOMBRE_SUCURSAL", type: "VARCHAR2(255 CHAR)")

			column(name: "NUMERO_CLIENTE", type: "VARCHAR2(255 CHAR)")

			column(name: "OBSERVACIONES", type: "VARCHAR2(255 CHAR)")

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PROMOCION", type: "VARCHAR2(255 CHAR)")

			column(name: "REFERENCIA", type: "VARCHAR2(255 CHAR)")

			column(name: "VENDEDOR", type: "VARCHAR2(255 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-60") {
		createTable(tableName: "PRESTAMO_DOCUMENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660337", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DOCUMENTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_ARCHIVO", type: "VARCHAR2(100 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-61") {
		createTable(tableName: "PRESTAMO_PAGO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660343", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_PAGO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_PAGO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-62") {
		createTable(tableName: "PRO_PROMOCION") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660357", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_PROMOCION", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_FIN_VIGENCIA", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_INICIO_VIGENCIA", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "IVA", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "METODO_CALCULO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_PROMOCION", type: "VARCHAR2(100 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_DE_PAGOS", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PERIODICIDAD_PAGOS_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PERIODICIDAD_TASA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TASA_DE_INTERES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "VIGENCIA_INDEFINIDA", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-63") {
		createTable(tableName: "PRO_PROMOCION_ACCESORIO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660365", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ACCESORIO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FORMA_APLICACION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ORDEN", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRO_PROMOCION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-64") {
		createTable(tableName: "PRODUCT") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660370", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PRICE", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-65") {
		createTable(tableName: "PUBLICACION_DET") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660390", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_CIA", type: "VARCHAR2(5 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_SUCURSAL", type: "VARCHAR2(5 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CONCEPTO", type: "VARCHAR2(3 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_PAGO_ANIO", type: "VARCHAR2(4 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_PAGO_DIA", type: "VARCHAR2(2 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_PAGO_MES", type: "VARCHAR2(2 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "ID_CR", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_MORATORIOS", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_PAGO", type: "NUMBER(9,2)") {
				constraints(nullable: "false")
			}

			column(name: "LISTA_COBRO_DETALLE_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_CLIENTE", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_OPERACION", type: "VARCHAR2(26 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PASSWORD", type: "VARCHAR2(10 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PUBLICACION_LOTE_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "REFERENCIA", type: "VARCHAR2(26 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "RESPUESTA_CR", type: "VARCHAR2(255 CHAR)")

			column(name: "TIPO_PAGO", type: "VARCHAR2(3 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "USUARIO", type: "VARCHAR2(10 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-66") {
		createTable(tableName: "PUBLICACION_LOTE") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660399", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_MEDIO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_REGISTRO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_LOTE", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-67") {
		createTable(tableName: "PURCHASE_ORDER") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660407", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "AMOUNT_TOTAL", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "CUSTOMER_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOTES", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PAID", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "READY", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-68") {
		createTable(tableName: "REGISTRATION_CODE") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660412", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "TOKEN", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "USERNAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-69") {
		createTable(tableName: "REL_TIPOS_EMPLEADOS") {
			column(name: "ID_DEPENDENCIA", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ID_TIPO_EMPLEADO", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-70") {
		createTable(tableName: "REQUESTMAP") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660417", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CONFIG_ATTRIBUTE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "URL", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-71") {
		createTable(tableName: "ROL") {
			column(name: "ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660423", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "AUTHORITY", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "TYPE", type: "VARCHAR2(255 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-72") {
		createTable(tableName: "RS_CLIENTE") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660431", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "APELLIDO_MATERNO", type: "VARCHAR2(255 CHAR)")

			column(name: "APELLIDO_PATERNO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_DE_NOMINA", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PERSONA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRIMER_NOMBRE", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "RFC", type: "VARCHAR2(255 CHAR)")

			column(name: "SEGUNDO_NOMBRE", type: "VARCHAR2(255 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-73") {
		createTable(tableName: "RS_CLIENTE_CTA_BANCARIA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660440", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BANCO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLABE", type: "VARCHAR2(25 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CLIENTE_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DEPOSITO_PRESTAMO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_DE_CUENTA", type: "VARCHAR2(25 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-74") {
		createTable(tableName: "RS_CLIENTE_EMPLEO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660448", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "AREA", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CLIENTE_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DOMICILIO_ID", type: "NUMBER(19,0)")

			column(name: "FECHA_INGRESO", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "INGRESO_MENSUAL", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-75") {
		createTable(tableName: "RS_CLIENTE_ENT_DEPENDENCIA") {
			column(name: "RS_CLIENTE_DEPENDENCIA_ID", type: "NUMBER(19,0)")

			column(name: "ENT_DEPENDENCIA_ID", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-76") {
		createTable(tableName: "RS_CLIENTE_REFERENCIA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660456", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLIENTE_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_TELEFONO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_COMPLETO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "TELEFONO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-77") {
		createTable(tableName: "RS_GRAL_ASENTAMIENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660463", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CODIGO_POSTAL", type: "VARCHAR2(5 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DELEGACION_MUNICIPIO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_ASENTAMIENTO", type: "VARCHAR2(100 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "TIPO_ASENTAMIENTO", type: "VARCHAR2(100 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-78") {
		createTable(tableName: "RS_GRAL_DELEGACION_MUNICIPIO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660468", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ESTADO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_CIUDAD", type: "VARCHAR2(50 CHAR)")

			column(name: "NOMBRE_DELEGACION_MUNICIPIO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-79") {
		createTable(tableName: "RS_GRAL_DOMICILIO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660476", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ANIOS_RESIDENCIA", type: "NUMBER(10,0)")

			column(name: "CALLE", type: "VARCHAR2(100 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "COMENTARIOS", type: "VARCHAR2(300 CHAR)")

			column(name: "ENTRE_CALLES", type: "VARCHAR2(300 CHAR)")

			column(name: "ES_FISCAL", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_EXTERIOR", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_INTERIOR", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PERSONA_ID", type: "NUMBER(19,0)")

			column(name: "RS_GRAL_ASENTAMIENTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SUCURSAL_ID", type: "NUMBER(19,0)")

			column(name: "TIPO_VIVIENDA", type: "VARCHAR2(10 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-80") {
		createTable(tableName: "RS_GRAL_ESTADO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660482", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ALIAS_ESTADO", type: "VARCHAR2(255 CHAR)")

			column(name: "CVE_ESTADO", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_ESTADO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "REGION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-81") {
		createTable(tableName: "RS_GRAL_TELEFONO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660489", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_TELEFONO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PERSONA_ID", type: "NUMBER(19,0)")

			column(name: "TELEFONO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-82") {
		createTable(tableName: "RS_PERSONA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660495", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "APELLIDO_MATERNO", type: "VARCHAR2(25 CHAR)")

			column(name: "APELLIDO_PATERNO", type: "VARCHAR2(25 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CURP", type: "VARCHAR2(255 CHAR)")

			column(name: "EMAIL", type: "VARCHAR2(255 CHAR)")

			column(name: "ENTIDAD_NACIMIENTO_ID", type: "NUMBER(19,0)")

			column(name: "ESCOLARIDAD_ID", type: "NUMBER(19,0)")

			column(name: "ESTADO_CIVIL", type: "VARCHAR2(28 CHAR)")

			column(name: "FECHA_NACIMIENTO", type: "TIMESTAMP(6)")

			column(name: "IDENTIFICACION_OFICIAL_ID", type: "NUMBER(19,0)")

			column(name: "NOMBRE_ALTERNO", type: "VARCHAR2(50 CHAR)")

			column(name: "NUMERO_IDENTIFICACION_OFICIAL", type: "VARCHAR2(255 CHAR)")

			column(name: "NUMERO_IMSS", type: "VARCHAR2(50 CHAR)")

			column(name: "PRIMER_NOMBRE", type: "VARCHAR2(25 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "RFC", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "SEGUNDO_NOMBRE", type: "VARCHAR2(25 CHAR)")

			column(name: "SEXO", type: "VARCHAR2(9 CHAR)")

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-83") {
		createTable(tableName: "RS_PERSONA_TIPOS_PERSONA") {
			column(name: "RS_PERSONA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SIM_CAT_TIPO_PERSONA_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-84") {
		createTable(tableName: "SIM_CAT_ACCESORIO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660505", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CONCEPTO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TIPO_ACCESORIO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-85") {
		createTable(tableName: "SIM_CAT_BANCO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660511", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_BANCO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_BANCO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-86") {
		createTable(tableName: "SIM_CAT_CR_MOTIVO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660519", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CODIGO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION", type: "VARCHAR2(80 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PROCESO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-87") {
		createTable(tableName: "SIM_CAT_DESC_TELEFONO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660524", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_DESCRIPCION_TELEFONO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_DESCRIPCION_TELEFONO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-88") {
		createTable(tableName: "SIM_CAT_DOCUMENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660531", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_DOCUMENTO", type: "VARCHAR2(40 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION", type: "VARCHAR2(300 CHAR)")

			column(name: "NOMBRE_DOCUMENTO", type: "VARCHAR2(80 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "TIPO_DOCUMENTO_ID", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-89") {
		createTable(tableName: "SIM_CAT_ESCOLARIDAD") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660537", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_ESCOLARIDAD", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_ESCOLARIDAD", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-90") {
		createTable(tableName: "SIM_CAT_EVENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660543", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "EVENTO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-91") {
		createTable(tableName: "SIM_CAT_FORMA_APLICACION") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660548", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_FORMA_APLICACION", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_FORMA_APLICACION", type: "VARCHAR2(60 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-92") {
		createTable(tableName: "SIM_CAT_FORMA_ENTREGA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660555", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_FORMA_ENTREGA", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_FORMA_ENTREGA", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-93") {
		createTable(tableName: "SIM_CAT_LISTA_COBRO_ESTATUS") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660562", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_LISTA_ESTATUS", type: "VARCHAR2(40 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_LISTA_ESTATUS", type: "VARCHAR2(150 CHAR)")

			column(name: "NOMBRE_LISTA_ESTATUS", type: "VARCHAR2(90 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-94") {
		createTable(tableName: "SIM_CAT_METODO_CALCULO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660570", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_METODO_CALCULO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_METODO_CALCULO", type: "VARCHAR2(300 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_METODO_CALCULO", type: "VARCHAR2(150 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-95") {
		createTable(tableName: "SIM_CAT_PAIS") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660577", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_PAIS", type: "VARCHAR2(10 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_PAIS", type: "VARCHAR2(30 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-96") {
		createTable(tableName: "SIM_CAT_PERIODICIDAD") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660586", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CANTIDAD_PAGOS", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_PERIODICIDAD", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_PERIODICIDAD", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_DIAS", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-97") {
		createTable(tableName: "SIM_CAT_TIPO_ACCESORIO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660593", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_TIPO_ACCESORIO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_TIPO_ACCESORIO", type: "VARCHAR2(80 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-98") {
		createTable(tableName: "SIM_CAT_TIPO_ASENTAMIENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660600", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_TIPO_ASENTAMIENTO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_TIPO_ASENTAMIENTO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-99") {
		createTable(tableName: "SIM_CAT_TIPO_DOCUMENTO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660607", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_TIPO_DOCUMENTO", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_TIPO_DOCUMENTO", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-100") {
		createTable(tableName: "SIM_CAT_TIPO_EMP") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660614", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_TIPO_EMPLEADO_DEP", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_TIPO_EMPLEADO_DEP", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-101") {
		createTable(tableName: "SIM_CAT_TIPO_PERSONA") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660621", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_TIPO_PERSONA", type: "VARCHAR2(15 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPCION_TIPO_PERSONA", type: "VARCHAR2(150 CHAR)")

			column(name: "NOMBRE_TIPO_PERSONA", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-102") {
		createTable(tableName: "SIM_CAT_UNIDAD") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660629", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CLAVE_UNIDAD", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_UNIDAD", type: "VARCHAR2(20 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "VALOR", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-103") {
		createTable(tableName: "SOLICITUD_CREDITO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660637", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "APPROVAL_REMARK", type: "VARCHAR2(255 CHAR)")

			column(name: "APPROVAL_STATUS", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CANTIDAD_SOLICITADA", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP(6)")

			column(name: "MOTIVO", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "NOMBRE_SOLICITANTE", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "REENVIAR_SOLICITUD", type: "NUMBER(1,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-104") {
		createTable(tableName: "SOLICITUD_PRESTAMO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660646", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "APPROVAL_STATUS", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "CORREO_SOLICITANTE", type: "VARCHAR2(255 CHAR)")

			column(name: "DATE_CREATED", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "EXPLICACION_CREDITO", type: "VARCHAR2(255 CHAR)")

			column(name: "EXPLICACION_SOLICITUD", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP(6)")

			column(name: "NOMBRE_SOLICITANTE", type: "VARCHAR2(50 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_AUTORIZADO", type: "NUMBER(1,0)")

			column(name: "SUELDO_MENSUAL", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-105") {
		createTable(tableName: "TABLA_AMORTIZACION_ACCESORIO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660653", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ACCESORIO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_ACCESORIO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_ACCESORIO_PAGADO", type: "NUMBER(19,2)")

			column(name: "IMPORTE_IVA_ACCESORIO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORTE_IVA_ACCESORIO_PAGADO", type: "NUMBER(19,2)")

			column(name: "TABLA_AMORTIZACION_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-106") {
		createTable(tableName: "TABLA_AMORTIZACION_REGISTRO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660669", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "FECHA_PAGO_ULTIMO", type: "TIMESTAMP(6)")

			column(name: "FECHA_VALOR_CALCULADO", type: "TIMESTAMP(6)")

			column(name: "IMP_CAPITAL", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMP_CAPITAL_PAGADO", type: "NUMBER(19,2)")

			column(name: "IMP_INTERES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMP_INTERES_PAGADO", type: "NUMBER(19,2)")

			column(name: "IMP_IVA_INTERES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMP_IVA_INTERES_PAGADO", type: "NUMBER(19,2)")

			column(name: "IMP_PAGO", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMP_PAGO_PAGADO", type: "NUMBER(19,2)")

			column(name: "IMP_SALDO_FINAL", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "IMP_SALDO_INICIAL", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "LISTA_COBRO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "NUMERO_PAGO", type: "NUMBER(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "PAGADO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "PAGO_PUNTUAL", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "PRESTAMO_ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TASA_INTERES", type: "NUMBER(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-107") {
		createTable(tableName: "USUARIO") {
			column(name: "ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660679", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_EXPIRED", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_LOCKED", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "EMAIL", type: "VARCHAR2(255 CHAR)")

			column(name: "ENABLED", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "FIRST_NAME", type: "VARCHAR2(255 CHAR)")

			column(name: "IS_PASSWORD_ENCODED", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_NAME", type: "VARCHAR2(255 CHAR)")

			column(name: "password", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "PASSWORD_EXPIRED", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "USERNAME", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-108") {
		createTable(tableName: "USUARIO_ACCESO") {
			column(name: "ID", type: "NUMBER(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00660685", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VERSION", type: "NUMBER(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ACCESO_TODO", type: "NUMBER(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-109") {
		createTable(tableName: "USUARIO_ACCESO_ENT_REGION") {
			column(name: "USUARIO_ACCESO_REGIONES_ID", type: "NUMBER(19,0)")

			column(name: "ENT_REGION_ID", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-110") {
		createTable(tableName: "USUARIO_ACCESO_ENT_SUCURSAL") {
			column(name: "USUARIO_ACCESO_SUCURSALES_ID", type: "NUMBER(19,0)")

			column(name: "ENT_SUCURSAL_ID", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-111") {
		createTable(tableName: "USUARIO_ROL") {
			column(name: "ROL_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}

			column(name: "USUARIO_ID", type: "VARCHAR2(255 CHAR)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-113") {
		addPrimaryKey(columnNames: "ENT_DEPENDENCIA_ID, PRO_PROMOCION_ID", constraintName: "SYS_C00660090", tableName: "ENT_DEPENDENCIA_PROMOCION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-114") {
		addPrimaryKey(columnNames: "RS_PERSONA_ID, SIM_CAT_TIPO_PERSONA_ID", constraintName: "SYS_C00660500", tableName: "RS_PERSONA_TIPOS_PERSONA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-115") {
		addPrimaryKey(columnNames: "ROL_ID, USUARIO_ID", constraintName: "SYS_C00660689", tableName: "USUARIO_ROL", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-117") {
		addUniqueConstraint(columnNames: "CLAVE_PROMOTOR", constraintName: "SYS_C00660064", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "EMP_EMPLEADO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-118") {
		addUniqueConstraint(columnNames: "PERSONA_ID", constraintName: "SYS_C00660065", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "EMP_EMPLEADO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-119") {
		addUniqueConstraint(columnNames: "CLAVE_PUESTO", constraintName: "SYS_C00660072", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "EMP_PUESTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-120") {
		addUniqueConstraint(columnNames: "NOMBRE_PUESTO", constraintName: "SYS_C00660073", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "EMP_PUESTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-121") {
		addUniqueConstraint(columnNames: "CLAVE_DELEGACION", constraintName: "SYS_C00660079", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_DELEGACION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-122") {
		addUniqueConstraint(columnNames: "NOMBRE_DELEGACION", constraintName: "SYS_C00660080", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_DELEGACION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-123") {
		addUniqueConstraint(columnNames: "CLAVE_DEPENDENCIA", constraintName: "SYS_C00660087", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_DEPENDENCIA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-124") {
		addUniqueConstraint(columnNames: "CLAVE_OFICINA", constraintName: "SYS_C00660096", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_OFICINA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-125") {
		addUniqueConstraint(columnNames: "NOMBRE_OFICINA", constraintName: "SYS_C00660097", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_OFICINA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-126") {
		addUniqueConstraint(columnNames: "CLAVE_REGION", constraintName: "SYS_C00660103", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_REGION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-127") {
		addUniqueConstraint(columnNames: "NOMBRE_REGION", constraintName: "SYS_C00660104", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_REGION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-128") {
		addUniqueConstraint(columnNames: "CLAVE_SUCURSAL", constraintName: "SYS_C00660112", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_SUCURSAL", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-129") {
		addUniqueConstraint(columnNames: "NOMBRE_SUCURSAL", constraintName: "SYS_C00660113", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ENT_SUCURSAL", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-130") {
		addUniqueConstraint(columnNames: "CLAVE_AFECTA", constraintName: "SYS_C00660158", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_CAT_AFECTA_OPERACION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-131") {
		addUniqueConstraint(columnNames: "CLAVE_CONCEPTO", constraintName: "SYS_C00660166", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_CAT_CONCEPTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-132") {
		addUniqueConstraint(columnNames: "DESCRIPCION_DIA_FESTIVO", constraintName: "SYS_C00660173", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_CAT_DIA_FESTIVO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-133") {
		addUniqueConstraint(columnNames: "CLAVE_OPERACION", constraintName: "SYS_C00660182", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_CAT_OPERACION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-134") {
		addUniqueConstraint(columnNames: "CLAVE_MEDIO", constraintName: "SYS_C00660201", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_CAT_PARAMETRO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-135") {
		addUniqueConstraint(columnNames: "CLAVE_DIVISA", constraintName: "SYS_C00660213", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_DIVISA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-136") {
		addUniqueConstraint(columnNames: "DESCRIPCION_DIVISA", constraintName: "SYS_C00660214", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_DIVISA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-137") {
		addUniqueConstraint(columnNames: "PFIN_PRE_MOVIMIENTO_ID", constraintName: "SYS_C00660232", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PFIN_MOVIMIENTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-138") {
		addUniqueConstraint(columnNames: "FOLIO_SOLICITUD", constraintName: "SYS_C00660290", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PRESTAMO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-139") {
		addUniqueConstraint(columnNames: "PRESTAMO_ID", constraintName: "SYS_C00660326", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PRESTAMO_CR_COMPRADA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-140") {
		addUniqueConstraint(columnNames: "CLAVE_PROMOCION", constraintName: "SYS_C00660358", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PRO_PROMOCION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-141") {
		addUniqueConstraint(columnNames: "ID_CR", constraintName: "SYS_C00660391", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PUBLICACION_DET", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-142") {
		addUniqueConstraint(columnNames: "LISTA_COBRO_DETALLE_ID", constraintName: "SYS_C00660392", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "PUBLICACION_DET", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-143") {
		addUniqueConstraint(columnNames: "URL", constraintName: "SYS_C00660418", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "REQUESTMAP", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-144") {
		addUniqueConstraint(columnNames: "AUTHORITY", constraintName: "SYS_C00660424", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ROL", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-145") {
		addUniqueConstraint(columnNames: "PERSONA_ID", constraintName: "SYS_C00660432", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "RS_CLIENTE", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-146") {
		addUniqueConstraint(columnNames: "CLABE", constraintName: "SYS_C00660441", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "RS_CLIENTE_CTA_BANCARIA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-147") {
		addUniqueConstraint(columnNames: "AREA", constraintName: "SYS_C00660449", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "RS_CLIENTE_EMPLEO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-148") {
		addUniqueConstraint(columnNames: "CVE_ESTADO", constraintName: "SYS_C00660483", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "RS_GRAL_ESTADO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-149") {
		addUniqueConstraint(columnNames: "NOMBRE_ESTADO", constraintName: "SYS_C00660484", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "RS_GRAL_ESTADO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-150") {
		addUniqueConstraint(columnNames: "EMAIL", constraintName: "SYS_C00660496", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "RS_PERSONA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-151") {
		addUniqueConstraint(columnNames: "USUARIO_ID", constraintName: "SYS_C00660497", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "RS_PERSONA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-152") {
		addUniqueConstraint(columnNames: "CONCEPTO_ID", constraintName: "SYS_C00660506", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_ACCESORIO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-153") {
		addUniqueConstraint(columnNames: "CLAVE_BANCO", constraintName: "SYS_C00660512", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_BANCO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-154") {
		addUniqueConstraint(columnNames: "NOMBRE_BANCO", constraintName: "SYS_C00660513", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_BANCO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-155") {
		addUniqueConstraint(columnNames: "CLAVE_DESCRIPCION_TELEFONO", constraintName: "SYS_C00660525", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_DESC_TELEFONO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-156") {
		addUniqueConstraint(columnNames: "NOMBRE_DESCRIPCION_TELEFONO", constraintName: "SYS_C00660526", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_DESC_TELEFONO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-157") {
		addUniqueConstraint(columnNames: "CLAVE_DOCUMENTO", constraintName: "SYS_C00660532", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_DOCUMENTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-158") {
		addUniqueConstraint(columnNames: "CLAVE_ESCOLARIDAD", constraintName: "SYS_C00660538", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_ESCOLARIDAD", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-159") {
		addUniqueConstraint(columnNames: "NOMBRE_ESCOLARIDAD", constraintName: "SYS_C00660539", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_ESCOLARIDAD", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-160") {
		addUniqueConstraint(columnNames: "CLAVE_FORMA_APLICACION", constraintName: "SYS_C00660549", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_FORMA_APLICACION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-161") {
		addUniqueConstraint(columnNames: "NOMBRE_FORMA_APLICACION", constraintName: "SYS_C00660550", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_FORMA_APLICACION", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-162") {
		addUniqueConstraint(columnNames: "CLAVE_FORMA_ENTREGA", constraintName: "SYS_C00660556", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_FORMA_ENTREGA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-163") {
		addUniqueConstraint(columnNames: "NOMBRE_FORMA_ENTREGA", constraintName: "SYS_C00660557", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_FORMA_ENTREGA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-164") {
		addUniqueConstraint(columnNames: "CLAVE_LISTA_ESTATUS", constraintName: "SYS_C00660563", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_LISTA_COBRO_ESTATUS", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-165") {
		addUniqueConstraint(columnNames: "NOMBRE_LISTA_ESTATUS", constraintName: "SYS_C00660564", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_LISTA_COBRO_ESTATUS", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-166") {
		addUniqueConstraint(columnNames: "CLAVE_METODO_CALCULO", constraintName: "SYS_C00660571", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_METODO_CALCULO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-167") {
		addUniqueConstraint(columnNames: "NOMBRE_METODO_CALCULO", constraintName: "SYS_C00660572", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_METODO_CALCULO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-168") {
		addUniqueConstraint(columnNames: "CLAVE_PAIS", constraintName: "SYS_C00660578", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_PAIS", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-169") {
		addUniqueConstraint(columnNames: "NOMBRE_PAIS", constraintName: "SYS_C00660579", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_PAIS", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-170") {
		addUniqueConstraint(columnNames: "CLAVE_PERIODICIDAD", constraintName: "SYS_C00660587", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_PERIODICIDAD", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-171") {
		addUniqueConstraint(columnNames: "NOMBRE_PERIODICIDAD", constraintName: "SYS_C00660588", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_PERIODICIDAD", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-172") {
		addUniqueConstraint(columnNames: "CLAVE_TIPO_ACCESORIO", constraintName: "SYS_C00660594", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_ACCESORIO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-173") {
		addUniqueConstraint(columnNames: "NOMBRE_TIPO_ACCESORIO", constraintName: "SYS_C00660595", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_ACCESORIO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-174") {
		addUniqueConstraint(columnNames: "CLAVE_TIPO_ASENTAMIENTO", constraintName: "SYS_C00660601", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_ASENTAMIENTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-175") {
		addUniqueConstraint(columnNames: "NOMBRE_TIPO_ASENTAMIENTO", constraintName: "SYS_C00660602", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_ASENTAMIENTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-176") {
		addUniqueConstraint(columnNames: "CLAVE_TIPO_DOCUMENTO", constraintName: "SYS_C00660608", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_DOCUMENTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-177") {
		addUniqueConstraint(columnNames: "NOMBRE_TIPO_DOCUMENTO", constraintName: "SYS_C00660609", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_DOCUMENTO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-178") {
		addUniqueConstraint(columnNames: "CLAVE_TIPO_EMPLEADO_DEP", constraintName: "SYS_C00660615", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_EMP", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-179") {
		addUniqueConstraint(columnNames: "NOMBRE_TIPO_EMPLEADO_DEP", constraintName: "SYS_C00660616", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_EMP", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-180") {
		addUniqueConstraint(columnNames: "CLAVE_TIPO_PERSONA", constraintName: "SYS_C00660622", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_PERSONA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-181") {
		addUniqueConstraint(columnNames: "NOMBRE_TIPO_PERSONA", constraintName: "SYS_C00660623", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "SIM_CAT_TIPO_PERSONA", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-182") {
		addUniqueConstraint(columnNames: "USERNAME", constraintName: "SYS_C00660680", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "USUARIO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-183") {
		addUniqueConstraint(columnNames: "USUARIO_ID", constraintName: "SYS_C00660686", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "USUARIO_ACCESO", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-199") {
		addForeignKeyConstraint(baseColumnNames: "CONTACT_ID", baseTableName: "ADDRESS", baseTableSchemaName: "NOMINA", constraintName: "FKBB979BF45B4C908F", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CONTACT", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-200") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "CALL_CENTER", baseTableSchemaName: "NOMINA", constraintName: "FKD4458956EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-201") {
		addForeignKeyConstraint(baseColumnNames: "CUSTOMER_ID", baseTableName: "CUSTOMER_AUDIT", baseTableSchemaName: "NOMINA", constraintName: "FK8617A03AF2D21C9F", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CUSTOMER", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-202") {
		addForeignKeyConstraint(baseColumnNames: "DETALLE_REGISTRO_ID", baseTableName: "DUMMY_COBRANZA", baseTableSchemaName: "NOMINA", constraintName: "FK2E3B1D87BAE40654", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "TABLA_AMORTIZACION_REGISTRO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-203") {
		addForeignKeyConstraint(baseColumnNames: "PERSONA_ID", baseTableName: "EMP_EMPLEADO", baseTableSchemaName: "NOMINA", constraintName: "FK9B1B0A62D994BF21", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_PERSONA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-204") {
		addForeignKeyConstraint(baseColumnNames: "PUESTO_ID", baseTableName: "EMP_EMPLEADO", baseTableSchemaName: "NOMINA", constraintName: "FK9B1B0A623C489099", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "EMP_PUESTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-205") {
		addForeignKeyConstraint(baseColumnNames: "SUCURSAL_ID", baseTableName: "EMP_EMPLEADO", baseTableSchemaName: "NOMINA", constraintName: "FK9B1B0A627251421C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_SUCURSAL", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-206") {
		addForeignKeyConstraint(baseColumnNames: "DEPENDE_DE_ID", baseTableName: "EMP_PUESTO", baseTableSchemaName: "NOMINA", constraintName: "FK5BCFAE85C4116620", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "EMP_PUESTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-207") {
		addForeignKeyConstraint(baseColumnNames: "SUCURSAL_ID", baseTableName: "ENT_DELEGACION", baseTableSchemaName: "NOMINA", constraintName: "FKA8F78EAD7251421C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_SUCURSAL", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-208") {
		addForeignKeyConstraint(baseColumnNames: "ENT_DEPENDENCIA_ID", baseTableName: "ENT_DEPENDENCIA_PROMOCION", baseTableSchemaName: "NOMINA", constraintName: "FK5E21D987A2269A2C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_DEPENDENCIA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-209") {
		addForeignKeyConstraint(baseColumnNames: "PRO_PROMOCION_ID", baseTableName: "ENT_DEPENDENCIA_PROMOCION", baseTableSchemaName: "NOMINA", constraintName: "FK5E21D987DC993F49", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRO_PROMOCION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-210") {
		addForeignKeyConstraint(baseColumnNames: "SUCURSAL_ID", baseTableName: "ENT_OFICINA", baseTableSchemaName: "NOMINA", constraintName: "FK153351B77251421C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_SUCURSAL", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-211") {
		addForeignKeyConstraint(baseColumnNames: "ESTADO_ID", baseTableName: "ENT_SUCURSAL", baseTableSchemaName: "NOMINA", constraintName: "FK40FDCF5435D313A9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_GRAL_ESTADO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-212") {
		addForeignKeyConstraint(baseColumnNames: "DEPENDENCIA_ID", baseTableName: "FECHA_EVENTO", baseTableSchemaName: "NOMINA", constraintName: "FK34002017DD160858", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_DEPENDENCIA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-213") {
		addForeignKeyConstraint(baseColumnNames: "EVENTO_ID", baseTableName: "FECHA_EVENTO", baseTableSchemaName: "NOMINA", constraintName: "FK34002017EF003D8D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_EVENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-214") {
		addForeignKeyConstraint(baseColumnNames: "PRODUCT_ID", baseTableName: "LINE_ITEM", baseTableSchemaName: "NOMINA", constraintName: "FK94ED5C7E3F156F15", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRODUCT", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-215") {
		addForeignKeyConstraint(baseColumnNames: "PURCHASE_ORDER_ID", baseTableName: "LINE_ITEM", baseTableSchemaName: "NOMINA", constraintName: "FK94ED5C7E4A5DA772", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PURCHASE_ORDER", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-216") {
		addForeignKeyConstraint(baseColumnNames: "DEPENDENCIA_ID", baseTableName: "LISTA_COBRO", baseTableSchemaName: "NOMINA", constraintName: "FK6260E277DD160858", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_DEPENDENCIA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-217") {
		addForeignKeyConstraint(baseColumnNames: "ESTATUS_ID", baseTableName: "LISTA_COBRO", baseTableSchemaName: "NOMINA", constraintName: "FK6260E277FA699F7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_LISTA_COBRO_ESTATUS", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-218") {
		addForeignKeyConstraint(baseColumnNames: "PERIODICIDAD_ID", baseTableName: "LISTA_COBRO", baseTableSchemaName: "NOMINA", constraintName: "FK6260E2778C9C7CCD", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_PERIODICIDAD", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-219") {
		addForeignKeyConstraint(baseColumnNames: "AMORTIZACION_ID", baseTableName: "LISTA_COBRO_DETALLE", baseTableSchemaName: "NOMINA", constraintName: "FK41971FAF15EDF8FF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "TABLA_AMORTIZACION_REGISTRO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-220") {
		addForeignKeyConstraint(baseColumnNames: "LISTA_COBRO_ID", baseTableName: "LISTA_COBRO_DETALLE", baseTableSchemaName: "NOMINA", constraintName: "FK41971FAFFAC0148D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "LISTA_COBRO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-221") {
		addForeignKeyConstraint(baseColumnNames: "PAGO_ID", baseTableName: "LISTA_COBRO_DETALLE", baseTableSchemaName: "NOMINA", constraintName: "FK41971FAF51B73DB5", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO_PAGO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-222") {
		addForeignKeyConstraint(baseColumnNames: "TIPO_EMPLEADO_DEP_ID", baseTableName: "LISTA_COBRO_DETALLE", baseTableSchemaName: "NOMINA", constraintName: "FK41971FAF84C83195", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_TIPO_EMP", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-223") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "LISTA_COBRO_DETALLE", baseTableSchemaName: "NOMINA", constraintName: "FK41971FAFB6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-224") {
		addForeignKeyConstraint(baseColumnNames: "ESTATUS_LISTA_COBRO_ID", baseTableName: "LISTA_COBRO_PROCESO", baseTableSchemaName: "NOMINA", constraintName: "FKD24B40A3CDB238BF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_LISTA_COBRO_ESTATUS", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-225") {
		addForeignKeyConstraint(baseColumnNames: "LISTA_COBRO_ID", baseTableName: "LISTA_COBRO_PROCESO", baseTableSchemaName: "NOMINA", constraintName: "FKD24B40A3FAC0148D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "LISTA_COBRO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-226") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "LISTA_COBRO_PROCESO", baseTableSchemaName: "NOMINA", constraintName: "FKD24B40A3B6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-227") {
		addForeignKeyConstraint(baseColumnNames: "PAIS_ID", baseTableName: "PFIN_CAT_DIA_FESTIVO", baseTableSchemaName: "NOMINA", constraintName: "FK6666E732880C008D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_PAIS", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-228") {
		addForeignKeyConstraint(baseColumnNames: "AFECTA_ID", baseTableName: "PFIN_CAT_OPERACION", baseTableSchemaName: "NOMINA", constraintName: "FKBEDB1E4BDEEBD66", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_AFECTA_OPERACION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-229") {
		addForeignKeyConstraint(baseColumnNames: "CONCEPTO_ID", baseTableName: "PFIN_CAT_OPERACION_CONCEPTO", baseTableSchemaName: "NOMINA", constraintName: "FKD6EFCA7B9F810BC6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_CONCEPTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-230") {
		addForeignKeyConstraint(baseColumnNames: "OPERACION_ID", baseTableName: "PFIN_CAT_OPERACION_CONCEPTO", baseTableSchemaName: "NOMINA", constraintName: "FKD6EFCA7B91AA9CCE", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_OPERACION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-231") {
		addForeignKeyConstraint(baseColumnNames: "CLIENTE_ID", baseTableName: "PFIN_CUENTA", baseTableSchemaName: "NOMINA", constraintName: "FK3D242B6CA3A068E1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_CLIENTE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-232") {
		addForeignKeyConstraint(baseColumnNames: "CANCELA_TRANSACCION_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB94CC36D42", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_MOVIMIENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-233") {
		addForeignKeyConstraint(baseColumnNames: "CUENTA_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB9C1C07D84", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CUENTA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-234") {
		addForeignKeyConstraint(baseColumnNames: "DIVISA_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB9F33DD4C4", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_DIVISA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-235") {
		addForeignKeyConstraint(baseColumnNames: "OPERACION_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB991AA9CCE", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_OPERACION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-236") {
		addForeignKeyConstraint(baseColumnNames: "PFIN_PRE_MOVIMIENTO_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB955469E53", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_PRE_MOVIMIENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-237") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB9EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-238") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_PAGO_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB9741B475", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO_PAGO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-239") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "PFIN_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK526F9DB9B6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-240") {
		addForeignKeyConstraint(baseColumnNames: "CONCEPTO_ID", baseTableName: "PFIN_MOVIMIENTO_DET", baseTableSchemaName: "NOMINA", constraintName: "FK31B4CC8D9F810BC6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_CONCEPTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-241") {
		addForeignKeyConstraint(baseColumnNames: "MOVIMIENTO_ID", baseTableName: "PFIN_MOVIMIENTO_DET", baseTableSchemaName: "NOMINA", constraintName: "FK31B4CC8D66C78964", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_MOVIMIENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-242") {
		addForeignKeyConstraint(baseColumnNames: "CUENTA_ID", baseTableName: "PFIN_PRE_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK99E81AD5C1C07D84", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CUENTA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-243") {
		addForeignKeyConstraint(baseColumnNames: "DIVISA_ID", baseTableName: "PFIN_PRE_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK99E81AD5F33DD4C4", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_DIVISA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-244") {
		addForeignKeyConstraint(baseColumnNames: "OPERACION_ID", baseTableName: "PFIN_PRE_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK99E81AD591AA9CCE", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_OPERACION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-245") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PFIN_PRE_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK99E81AD5EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-246") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "PFIN_PRE_MOVIMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FK99E81AD5B6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-247") {
		addForeignKeyConstraint(baseColumnNames: "CONCEPTO_ID", baseTableName: "PFIN_PRE_MOVIMIENTO_DET", baseTableSchemaName: "NOMINA", constraintName: "FKE0315BA99F810BC6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_CONCEPTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-248") {
		addForeignKeyConstraint(baseColumnNames: "PRE_MOVIMIENTO_ID", baseTableName: "PFIN_PRE_MOVIMIENTO_DET", baseTableSchemaName: "NOMINA", constraintName: "FKE0315BA923194FF7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_PRE_MOVIMIENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-249") {
		addForeignKeyConstraint(baseColumnNames: "CUENTA_ID", baseTableName: "PFIN_SALDO", baseTableSchemaName: "NOMINA", constraintName: "FKF24D4145C1C07D84", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CUENTA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-250") {
		addForeignKeyConstraint(baseColumnNames: "DIVISA_ID", baseTableName: "PFIN_SALDO", baseTableSchemaName: "NOMINA", constraintName: "FKF24D4145F33DD4C4", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_DIVISA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-251") {
		addForeignKeyConstraint(baseColumnNames: "CLIENTE_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFFA3A068E1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_CLIENTE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-252") {
		addForeignKeyConstraint(baseColumnNames: "DELEGACION_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFFEB0A6FC", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_DELEGACION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-253") {
		addForeignKeyConstraint(baseColumnNames: "DEPENDENCIA_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFFDD160858", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_DEPENDENCIA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-254") {
		addForeignKeyConstraint(baseColumnNames: "FORMA_DE_DISPERCION_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFFC8BFA758", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_FORMA_ENTREGA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-255") {
		addForeignKeyConstraint(baseColumnNames: "MOVITO_RESPUESTA_CR_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFF3C58D18F", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_CR_MOTIVO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-256") {
		addForeignKeyConstraint(baseColumnNames: "PROMOCION_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFFB74906F7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRO_PROMOCION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-257") {
		addForeignKeyConstraint(baseColumnNames: "SUCURSAL_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFF7251421C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_SUCURSAL", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-258") {
		addForeignKeyConstraint(baseColumnNames: "TIPO_EMPLEADO_DEP_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFF84C83195", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_TIPO_EMP", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-259") {
		addForeignKeyConstraint(baseColumnNames: "VENDEDOR_ID", baseTableName: "PRESTAMO", baseTableSchemaName: "NOMINA", constraintName: "FKB3EE3EFF6E81DE1D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "EMP_EMPLEADO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-260") {
		addForeignKeyConstraint(baseColumnNames: "ACCESORIO_ID", baseTableName: "PRESTAMO_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FKFEB54E78BAD4EDE7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_ACCESORIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-261") {
		addForeignKeyConstraint(baseColumnNames: "PERIODICIDAD_ID", baseTableName: "PRESTAMO_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FKFEB54E788C9C7CCD", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_PERIODICIDAD", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-262") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PRESTAMO_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FKFEB54E78EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-263") {
		addForeignKeyConstraint(baseColumnNames: "UNIDAD_ID", baseTableName: "PRESTAMO_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FKFEB54E78D093F64D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_UNIDAD", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-264") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PRESTAMO_CR_CARTERA", baseTableSchemaName: "NOMINA", constraintName: "FK48AC9FA4EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-265") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PRESTAMO_CR_COMPRADA", baseTableSchemaName: "NOMINA", constraintName: "FKA8C3A30BEBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-266") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PRESTAMO_CR_RESPUESTA", baseTableSchemaName: "NOMINA", constraintName: "FK4E793530EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-267") {
		addForeignKeyConstraint(baseColumnNames: "DOCUMENTO_ID", baseTableName: "PRESTAMO_DOCUMENTO", baseTableSchemaName: "NOMINA", constraintName: "FKB505197440BB3C67", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_DOCUMENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-268") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PRESTAMO_DOCUMENTO", baseTableSchemaName: "NOMINA", constraintName: "FKB5051974EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-269") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "PRESTAMO_PAGO", baseTableSchemaName: "NOMINA", constraintName: "FK15DB63D9EBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-270") {
		addForeignKeyConstraint(baseColumnNames: "METODO_CALCULO_ID", baseTableName: "PRO_PROMOCION", baseTableSchemaName: "NOMINA", constraintName: "FKCD2E3B42509AACCA", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_METODO_CALCULO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-271") {
		addForeignKeyConstraint(baseColumnNames: "PERIODICIDAD_PAGOS_ID", baseTableName: "PRO_PROMOCION", baseTableSchemaName: "NOMINA", constraintName: "FKCD2E3B42B2E3B1B2", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_PERIODICIDAD", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-272") {
		addForeignKeyConstraint(baseColumnNames: "PERIODICIDAD_TASA_ID", baseTableName: "PRO_PROMOCION", baseTableSchemaName: "NOMINA", constraintName: "FKCD2E3B42463D0105", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_PERIODICIDAD", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-273") {
		addForeignKeyConstraint(baseColumnNames: "ACCESORIO_ID", baseTableName: "PRO_PROMOCION_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FKED0512FBBAD4EDE7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_ACCESORIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-274") {
		addForeignKeyConstraint(baseColumnNames: "FORMA_APLICACION_ID", baseTableName: "PRO_PROMOCION_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FKED0512FB8E045422", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_FORMA_APLICACION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-275") {
		addForeignKeyConstraint(baseColumnNames: "PRO_PROMOCION_ID", baseTableName: "PRO_PROMOCION_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FKED0512FBDC993F49", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRO_PROMOCION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-276") {
		addForeignKeyConstraint(baseColumnNames: "LISTA_COBRO_DETALLE_ID", baseTableName: "PUBLICACION_DET", baseTableSchemaName: "NOMINA", constraintName: "FK71BF64B1BB84B518", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "LISTA_COBRO_DETALLE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-277") {
		addForeignKeyConstraint(baseColumnNames: "PUBLICACION_LOTE_ID", baseTableName: "PUBLICACION_DET", baseTableSchemaName: "NOMINA", constraintName: "FK71BF64B13BDE0F20", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PUBLICACION_LOTE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-278") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "PUBLICACION_LOTE", baseTableSchemaName: "NOMINA", constraintName: "FKC630FA56B6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-279") {
		addForeignKeyConstraint(baseColumnNames: "CUSTOMER_ID", baseTableName: "PURCHASE_ORDER", baseTableSchemaName: "NOMINA", constraintName: "FK71A56A90F2D21C9F", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CUSTOMER", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-280") {
		addForeignKeyConstraint(baseColumnNames: "ID_DEPENDENCIA", baseTableName: "REL_TIPOS_EMPLEADOS", baseTableSchemaName: "NOMINA", constraintName: "FK809704829E59F046", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_DEPENDENCIA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-281") {
		addForeignKeyConstraint(baseColumnNames: "ID_TIPO_EMPLEADO", baseTableName: "REL_TIPOS_EMPLEADOS", baseTableSchemaName: "NOMINA", constraintName: "FK80970482C0E92CB3", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_TIPO_EMP", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-282") {
		addForeignKeyConstraint(baseColumnNames: "PERSONA_ID", baseTableName: "RS_CLIENTE", baseTableSchemaName: "NOMINA", constraintName: "FK234D69FCD994BF21", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_PERSONA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-283") {
		addForeignKeyConstraint(baseColumnNames: "BANCO_ID", baseTableName: "RS_CLIENTE_CTA_BANCARIA", baseTableSchemaName: "NOMINA", constraintName: "FKC9C9B7EFD20C4987", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_BANCO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-284") {
		addForeignKeyConstraint(baseColumnNames: "CLIENTE_ID", baseTableName: "RS_CLIENTE_CTA_BANCARIA", baseTableSchemaName: "NOMINA", constraintName: "FKC9C9B7EFA3A068E1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_CLIENTE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-285") {
		addForeignKeyConstraint(baseColumnNames: "CLIENTE_ID", baseTableName: "RS_CLIENTE_EMPLEO", baseTableSchemaName: "NOMINA", constraintName: "FKED20A3F1A3A068E1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_CLIENTE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-286") {
		addForeignKeyConstraint(baseColumnNames: "DOMICILIO_ID", baseTableName: "RS_CLIENTE_EMPLEO", baseTableSchemaName: "NOMINA", constraintName: "FKED20A3F1F8B858B", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_GRAL_DOMICILIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-287") {
		addForeignKeyConstraint(baseColumnNames: "ENT_DEPENDENCIA_ID", baseTableName: "RS_CLIENTE_ENT_DEPENDENCIA", baseTableSchemaName: "NOMINA", constraintName: "FKF047BAEFA2269A2C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_DEPENDENCIA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-288") {
		addForeignKeyConstraint(baseColumnNames: "RS_CLIENTE_DEPENDENCIA_ID", baseTableName: "RS_CLIENTE_ENT_DEPENDENCIA", baseTableSchemaName: "NOMINA", constraintName: "FKF047BAEF8B338B78", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_CLIENTE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-289") {
		addForeignKeyConstraint(baseColumnNames: "CLIENTE_ID", baseTableName: "RS_CLIENTE_REFERENCIA", baseTableSchemaName: "NOMINA", constraintName: "FKBDB0C115A3A068E1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_CLIENTE", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-290") {
		addForeignKeyConstraint(baseColumnNames: "DESCRIPCION_TELEFONO_ID", baseTableName: "RS_CLIENTE_REFERENCIA", baseTableSchemaName: "NOMINA", constraintName: "FKBDB0C1152B02490C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_DESC_TELEFONO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-291") {
		addForeignKeyConstraint(baseColumnNames: "DELEGACION_MUNICIPIO_ID", baseTableName: "RS_GRAL_ASENTAMIENTO", baseTableSchemaName: "NOMINA", constraintName: "FKFC08AB53E3F9018", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_GRAL_DELEGACION_MUNICIPIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-292") {
		addForeignKeyConstraint(baseColumnNames: "ESTADO_ID", baseTableName: "RS_GRAL_DELEGACION_MUNICIPIO", baseTableSchemaName: "NOMINA", constraintName: "FKFEEDB3D235D313A9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_GRAL_ESTADO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-293") {
		addForeignKeyConstraint(baseColumnNames: "PERSONA_ID", baseTableName: "RS_GRAL_DOMICILIO", baseTableSchemaName: "NOMINA", constraintName: "FK456F607AD994BF21", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_PERSONA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-294") {
		addForeignKeyConstraint(baseColumnNames: "RS_GRAL_ASENTAMIENTO_ID", baseTableName: "RS_GRAL_DOMICILIO", baseTableSchemaName: "NOMINA", constraintName: "FK456F607AECEA4C7E", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_GRAL_ASENTAMIENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-295") {
		addForeignKeyConstraint(baseColumnNames: "SUCURSAL_ID", baseTableName: "RS_GRAL_DOMICILIO", baseTableSchemaName: "NOMINA", constraintName: "FK456F607A7251421C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_SUCURSAL", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-296") {
		addForeignKeyConstraint(baseColumnNames: "REGION_ID", baseTableName: "RS_GRAL_ESTADO", baseTableSchemaName: "NOMINA", constraintName: "FK9A8592711B17AE1C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_REGION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-297") {
		addForeignKeyConstraint(baseColumnNames: "DESCRIPCION_TELEFONO_ID", baseTableName: "RS_GRAL_TELEFONO", baseTableSchemaName: "NOMINA", constraintName: "FK3397AD3F2B02490C", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_DESC_TELEFONO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-298") {
		addForeignKeyConstraint(baseColumnNames: "PERSONA_ID", baseTableName: "RS_GRAL_TELEFONO", baseTableSchemaName: "NOMINA", constraintName: "FK3397AD3FD994BF21", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_PERSONA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-299") {
		addForeignKeyConstraint(baseColumnNames: "ENTIDAD_NACIMIENTO_ID", baseTableName: "RS_PERSONA", baseTableSchemaName: "NOMINA", constraintName: "FKC791B3AE2A7AE540", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_GRAL_ESTADO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-300") {
		addForeignKeyConstraint(baseColumnNames: "ESCOLARIDAD_ID", baseTableName: "RS_PERSONA", baseTableSchemaName: "NOMINA", constraintName: "FKC791B3AEDA420E07", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_ESCOLARIDAD", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-301") {
		addForeignKeyConstraint(baseColumnNames: "IDENTIFICACION_OFICIAL_ID", baseTableName: "RS_PERSONA", baseTableSchemaName: "NOMINA", constraintName: "FKC791B3AE6C5C4158", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_DOCUMENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-302") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "RS_PERSONA", baseTableSchemaName: "NOMINA", constraintName: "FKC791B3AEB6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-303") {
		addForeignKeyConstraint(baseColumnNames: "RS_PERSONA_ID", baseTableName: "RS_PERSONA_TIPOS_PERSONA", baseTableSchemaName: "NOMINA", constraintName: "FK4F8BD85BC59943DF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "RS_PERSONA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-304") {
		addForeignKeyConstraint(baseColumnNames: "SIM_CAT_TIPO_PERSONA_ID", baseTableName: "RS_PERSONA_TIPOS_PERSONA", baseTableSchemaName: "NOMINA", constraintName: "FK4F8BD85BAF96006D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_TIPO_PERSONA", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-305") {
		addForeignKeyConstraint(baseColumnNames: "CONCEPTO_ID", baseTableName: "SIM_CAT_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FK9CA779279F810BC6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PFIN_CAT_CONCEPTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-306") {
		addForeignKeyConstraint(baseColumnNames: "TIPO_ACCESORIO_ID", baseTableName: "SIM_CAT_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FK9CA779275204A1BE", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_TIPO_ACCESORIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-307") {
		addForeignKeyConstraint(baseColumnNames: "TIPO_DOCUMENTO_ID", baseTableName: "SIM_CAT_DOCUMENTO", baseTableSchemaName: "NOMINA", constraintName: "FK52F74423D7EAF03E", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_TIPO_DOCUMENTO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-308") {
		addForeignKeyConstraint(baseColumnNames: "ACCESORIO_ID", baseTableName: "TABLA_AMORTIZACION_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FK941E57EBAD4EDE7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SIM_CAT_ACCESORIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-309") {
		addForeignKeyConstraint(baseColumnNames: "TABLA_AMORTIZACION_ID", baseTableName: "TABLA_AMORTIZACION_ACCESORIO", baseTableSchemaName: "NOMINA", constraintName: "FK941E57E7DD2B68A", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "TABLA_AMORTIZACION_REGISTRO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-310") {
		addForeignKeyConstraint(baseColumnNames: "LISTA_COBRO_ID", baseTableName: "TABLA_AMORTIZACION_REGISTRO", baseTableSchemaName: "NOMINA", constraintName: "FKFC4201ADFAC0148D", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "LISTA_COBRO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-311") {
		addForeignKeyConstraint(baseColumnNames: "PRESTAMO_ID", baseTableName: "TABLA_AMORTIZACION_REGISTRO", baseTableSchemaName: "NOMINA", constraintName: "FKFC4201ADEBEEDE56", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PRESTAMO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-312") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "USUARIO_ACCESO", baseTableSchemaName: "NOMINA", constraintName: "FK3E116F91B6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-313") {
		addForeignKeyConstraint(baseColumnNames: "ENT_REGION_ID", baseTableName: "USUARIO_ACCESO_ENT_REGION", baseTableSchemaName: "NOMINA", constraintName: "FK32A1FE36A20047C8", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_REGION", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-314") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ACCESO_REGIONES_ID", baseTableName: "USUARIO_ACCESO_ENT_REGION", baseTableSchemaName: "NOMINA", constraintName: "FK32A1FE364431D774", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO_ACCESO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-315") {
		addForeignKeyConstraint(baseColumnNames: "ENT_SUCURSAL_ID", baseTableName: "USUARIO_ACCESO_ENT_SUCURSAL", baseTableSchemaName: "NOMINA", constraintName: "FKC2375D42E17A20C8", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ENT_SUCURSAL", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-316") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ACCESO_SUCURSALES_ID", baseTableName: "USUARIO_ACCESO_ENT_SUCURSAL", baseTableSchemaName: "NOMINA", constraintName: "FKC2375D42349CBAE8", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO_ACCESO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-317") {
		addForeignKeyConstraint(baseColumnNames: "ROL_ID", baseTableName: "USUARIO_ROL", baseTableSchemaName: "NOMINA", constraintName: "FK3118953E8FC52F76", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ROL", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-318") {
		addForeignKeyConstraint(baseColumnNames: "USUARIO_ID", baseTableName: "USUARIO_ROL", baseTableSchemaName: "NOMINA", constraintName: "FK3118953EB6C25116", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USUARIO", referencedTableSchemaName: "NOMINA", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360355282933-334") {
		createSequence(schemaName: "NOMINA", sequenceName: "HIBERNATE_SEQUENCE")
	}
}
