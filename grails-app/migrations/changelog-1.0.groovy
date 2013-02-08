databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1360357535489-1") {
		createTable(tableName: "address") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "addressPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "contact_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "line1", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "line2", type: "varchar2(255)")

			column(name: "state", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "zip", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-2") {
		createTable(tableName: "call_center") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "call_centerPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cerrar_registro", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "comentarios", type: "varchar2(255)")

			column(name: "fecha_llamada", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_cobrado", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "se_contacto", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-3") {
		createTable(tableName: "contact") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "contactPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "estatus", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "phone", type: "varchar2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-4") {
		createTable(tableName: "customer") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "customerPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "balance", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "credit_limit", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-5") {
		createTable(tableName: "customer_audit") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "customer_audiPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "balance", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "created_on", type: "timestamp")

			column(name: "credit_limit", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "customer_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-6") {
		createTable(tableName: "dummy_cobranza") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "dummy_cobranzPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "detalle_registro_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "field1", type: "double") {
				constraints(nullable: "false")
			}

			column(name: "field2", type: "double") {
				constraints(nullable: "false")
			}

			column(name: "field3", type: "varchar2(255)")

			column(name: "field4", type: "varchar2(255)")

			column(name: "field5", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "field6", type: "varchar2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-7") {
		createTable(tableName: "dummy_credito") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "dummy_creditoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "credito_ok", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "id_cliente", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "id_credito", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "ingresos", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "monto_prestamo", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-8") {
		createTable(tableName: "dummy_persona") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "dummy_personaPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "apellido", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "calle", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "codigo_postal", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-9") {
		createTable(tableName: "emp_empleado") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "emp_empleadoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_promotor", type: "varchar2(25)")

			column(name: "es_vigente", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_ingreso", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "numero_nomina", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "persona_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "puesto_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "sucursal_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "tipo_empleado", type: "varchar2(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-10") {
		createTable(tableName: "emp_puesto") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "emp_puestoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_puesto", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "depende_de_id", type: "number")

			column(name: "descripcion_puesto", type: "varchar2(150)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_puesto", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-11") {
		createTable(tableName: "ent_delegacion") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ent_delegacioPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_delegacion", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_delegacion", type: "varchar2(150)")

			column(name: "nombre_delegacion", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "sucursal_id", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-12") {
		createTable(tableName: "ent_dependencia") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ent_dependencPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_dependencia", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_dependencia", type: "varchar2(150)")

			column(name: "distribuidor", type: "varchar2(4)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_dependencia", type: "varchar2(100)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-13") {
		createTable(tableName: "ent_dependencia_promocion") {
			column(name: "pro_promocion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "ent_dependencia_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-14") {
		createTable(tableName: "ent_oficina") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ent_oficinaPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_oficina", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_oficina", type: "varchar2(150)")

			column(name: "nombre_oficina", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "sucursal_id", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-15") {
		createTable(tableName: "ent_region") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ent_regionPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_region", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_region", type: "varchar2(150)")

			column(name: "nombre_region", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-16") {
		createTable(tableName: "ent_sucursal") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ent_sucursalPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_sucursal", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_sucursal", type: "varchar2(150)")

			column(name: "estado_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nombre_sucursal", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_sucursal", type: "varchar2(15)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-17") {
		createTable(tableName: "fecha_evento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "fecha_eventoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "dependencia_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "evento_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "timestamp") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-18") {
		createTable(tableName: "line_item") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "line_itemPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "product_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "product_price", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "purchase_order_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "qty_ordered", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-19") {
		createTable(tableName: "lista_cobro") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "lista_cobroPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "anio", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "dependencia_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "estatus_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_fin", type: "timestamp")

			column(name: "fecha_inicio", type: "timestamp")

			column(name: "numero_pago", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "parcialidades", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "periodicidad_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-20") {
		createTable(tableName: "lista_cobro_detalle") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "lista_cobro_dPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "amortizacion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "estatus", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "lista_cobro_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "pago_id", type: "number")

			column(name: "tipo_empleado_dep_id", type: "number")

			column(name: "usuario_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-21") {
		createTable(tableName: "lista_cobro_proceso") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "lista_cobro_pPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "comentarios", type: "varchar2(255)")

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "estatus_lista_cobro_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_aplicacion", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_medio", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp")

			column(name: "lista_cobro_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-22") {
		createTable(tableName: "pfin_cat_afecta_operacion") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_cat_afecPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_afecta", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "comentarios", type: "varchar2(150)")

			column(name: "descripcion_afecta", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-23") {
		createTable(tableName: "pfin_cat_concepto") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_cat_concPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_concepto", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_corta", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_larga", type: "varchar2(150)") {
				constraints(nullable: "false")
			}

			column(name: "situacion", type: "varchar2(8)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-24") {
		createTable(tableName: "pfin_cat_dia_festivo") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_cat_dia_PK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_dia_festivo", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_dia_festivo", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "pais_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-25") {
		createTable(tableName: "pfin_cat_operacion") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_cat_operPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "afecta_id", type: "number")

			column(name: "clave_afecta_saldo", type: "varchar2(10)") {
				constraints(nullable: "false")
			}

			column(name: "clave_operacion", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_corta", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_larga", type: "varchar2(150)") {
				constraints(nullable: "false")
			}

			column(name: "situacion", type: "varchar2(8)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-26") {
		createTable(tableName: "pfin_cat_operacion_concepto") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_cat_oper_conPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_afecta", type: "varchar2(10)") {
				constraints(nullable: "false")
			}

			column(name: "concepto_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "operacion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "situacion", type: "varchar2(8)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-27") {
		createTable(tableName: "pfin_cat_parametro") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_cat_paraPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_medio", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "consecutivo_publicacion", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_medio", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "numero_digitos_despliega", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "opera_dia_festivo", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "opera_domingo", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "opera_sabado", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "pruebas_cliente_ws_cr", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-28") {
		createTable(tableName: "pfin_cuenta") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_cuentaPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "comentario", type: "varchar2(255)")

			column(name: "situacion", type: "varchar2(8)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_cuenta", type: "varchar2(7)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-29") {
		createTable(tableName: "pfin_divisa") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_divisaPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_divisa", type: "varchar2(10)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_divisa", type: "varchar2(20)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-30") {
		createTable(tableName: "pfin_movimiento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_movimienPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cancela_transaccion_id", type: "number")

			column(name: "cuenta_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "divisa_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_aplicacion", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_liquidacion", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_operacion", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_registro", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "importe_neto", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "log_host", type: "varchar2(255)")

			column(name: "log_ip_direccion", type: "varchar2(255)")

			column(name: "log_usuario", type: "varchar2(255)")

			column(name: "nota", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_pago_amortizacion", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "operacion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "pfin_pre_movimiento_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_pago_id", type: "number")

			column(name: "referencia", type: "number")

			column(name: "situacion_movimiento", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-31") {
		createTable(tableName: "pfin_movimiento_det") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_movimien_detPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "concepto_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "importe_concepto", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "movimiento_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nota", type: "varchar2(80)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-32") {
		createTable(tableName: "pfin_pre_movimiento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_pre_moviPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "divisa_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_aplicacion", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_liquidacion", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_operacion", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_registro", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "importe_neto", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "log_host", type: "varchar2(255)")

			column(name: "log_ip_direccion", type: "varchar2(255)")

			column(name: "log_usuario", type: "varchar2(255)")

			column(name: "nota", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_pago_amortizacion", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "operacion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "number")

			column(name: "situacion_pre_movimiento", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-33") {
		createTable(tableName: "pfin_pre_movimiento_det") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_pre_movi_detPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "concepto_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "importe_concepto", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "nota", type: "varchar2(80)") {
				constraints(nullable: "false")
			}

			column(name: "pre_movimiento_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-34") {
		createTable(tableName: "pfin_saldo") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pfin_saldoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cuenta_id", type: "number")

			column(name: "divisa_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_foto", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "saldo", type: "number(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-35") {
		createTable(tableName: "prestamo") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "prestamoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "approval_status", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "aprobado", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "comentarios", type: "varchar2(255)")

			column(name: "consecutivo_cr", type: "varchar2(255)")

			column(name: "correo_solicitante", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "deducciones_mensuales", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "delegacion_id", type: "number")

			column(name: "dependencia_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "documentos_correctos", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "estatus_solicitud", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "explicacion_devolucion", type: "varchar2(255)")

			column(name: "fecha_cobro", type: "timestamp")

			column(name: "fecha_solicitud", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "folio_solicitud", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "forma_de_dispercion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "incluir_en_listas_cobro", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp")

			column(name: "monto_solicitado", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "movito_respuesta_cr_id", type: "number")

			column(name: "percepciones_mensuales", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "promocion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "reenviar_solicitud", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "sucursal_id", type: "number")

			column(name: "tipo_empleado_dep_id", type: "number")

			column(name: "usuario_mesa_control", type: "varchar2(255)")

			column(name: "vendedor_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-36") {
		createTable(tableName: "prestamo_accesorio") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "prestamo_accePK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "accesorio_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "periodicidad_id", type: "number")

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "unidad_id", type: "number")

			column(name: "valor", type: "number(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-37") {
		createTable(tableName: "prestamo_cr_cartera") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "prestamo_cr_carPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "ape_mat", type: "varchar2(255)")

			column(name: "ape_pat", type: "varchar2(255)")

			column(name: "atg_domicilio", type: "double")

			column(name: "atg_trabajo", type: "double")

			column(name: "calle_dom", type: "varchar2(255)")

			column(name: "calle_trab", type: "varchar2(255)")

			column(name: "cen_trabajo", type: "varchar2(255)")

			column(name: "clave_cesion", type: "varchar2(255)")

			column(name: "clave_cia", type: "varchar2(255)")

			column(name: "clave_cliente", type: "varchar2(255)")

			column(name: "clave_sucursal", type: "varchar2(255)")

			column(name: "cod_postal_dom", type: "double")

			column(name: "cod_postal_tra", type: "double")

			column(name: "colonia_dom", type: "varchar2(255)")

			column(name: "colonia_tra", type: "varchar2(255)")

			column(name: "consecutivo", type: "varchar2(255)")

			column(name: "cve_estado_dom", type: "double")

			column(name: "cve_estado_tra", type: "double")

			column(name: "cve_municipio_dom", type: "double")

			column(name: "cve_municipio_tra", type: "double")

			column(name: "cve_supervisor", type: "double")

			column(name: "deducciones", type: "double")

			column(name: "edo_civil", type: "varchar2(255)")

			column(name: "estatus_dap", type: "number")

			column(name: "estatus_solicitud", type: "number")

			column(name: "fec_registro", type: "timestamp")

			column(name: "fecha_de_compra", type: "timestamp")

			column(name: "fecha_dispersion", type: "varchar2(255)")

			column(name: "fecha_nac", type: "timestamp")

			column(name: "ide_presupuestal", type: "varchar2(255)")

			column(name: "imp_credito_sol", type: "double")

			column(name: "imp_descuento", type: "double")

			column(name: "importe_cedido", type: "double")

			column(name: "ingreso_bruto", type: "double")

			column(name: "ingreso_neto", type: "double")

			column(name: "int_descuento", type: "double")

			column(name: "localidad_dom", type: "varchar2(255)")

			column(name: "localidad_tra", type: "varchar2(255)")

			column(name: "nom_conyuge", type: "varchar2(255)")

			column(name: "nombre", type: "varchar2(255)")

			column(name: "num_agente", type: "double")

			column(name: "num_ext_dom", type: "varchar2(255)")

			column(name: "num_ext_tra", type: "varchar2(255)")

			column(name: "num_int_dom", type: "varchar2(255)")

			column(name: "num_int_tra", type: "varchar2(255)")

			column(name: "num_parcialidades", type: "number")

			column(name: "num_personal", type: "double")

			column(name: "numero_operacion", type: "varchar2(255)")

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "promocion", type: "varchar2(255)")

			column(name: "ref1ape_mat", type: "varchar2(255)")

			column(name: "ref1ape_pat", type: "varchar2(255)")

			column(name: "ref1domicilio", type: "varchar2(255)")

			column(name: "ref1nombre", type: "varchar2(255)")

			column(name: "ref1telefono", type: "varchar2(255)")

			column(name: "ref2ape_mat", type: "varchar2(255)")

			column(name: "ref2ape_pat", type: "varchar2(255)")

			column(name: "ref2domicilio", type: "varchar2(255)")

			column(name: "ref2nombre", type: "varchar2(255)")

			column(name: "ref2telefono", type: "varchar2(255)")

			column(name: "ref3ape_mat", type: "varchar2(255)")

			column(name: "ref3ape_pat", type: "varchar2(255)")

			column(name: "ref3domicilio", type: "varchar2(255)")

			column(name: "ref3nombre", type: "varchar2(255)")

			column(name: "ref3telefono", type: "varchar2(255)")

			column(name: "rfc_cliente", type: "varchar2(255)")

			column(name: "tel_domicilio", type: "varchar2(255)")

			column(name: "tel_trabajo", type: "varchar2(255)")

			column(name: "tip_propiedad", type: "varchar2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-38") {
		createTable(tableName: "prestamo_cr_comprada") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "prestamo_cr_comPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cesion", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clasificador", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "clave_cia", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "clave_sucursal", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_compra", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_prox_pago", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "importe_cedido", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_descuento", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_intereses", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "iva_capital", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "iva_diferido", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "iva_intereses", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "neto_pagado", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "nombre", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_operacion", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_solicitud", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "pago_tienda", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "primer_credito", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "reserva", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_promocion", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-39") {
		createTable(tableName: "prestamo_cr_respuesta") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "prestamo_cr_rPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_distribuidor", type: "varchar2(255)")

			column(name: "clave_tienda", type: "varchar2(255)")

			column(name: "consecutivo", type: "varchar2(255)")

			column(name: "estatus", type: "varchar2(255)")

			column(name: "fecha_recepcion", type: "timestamp")

			column(name: "fecha_respuesta", type: "timestamp")

			column(name: "folio", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "monto_autorizado", type: "number(19,2)")

			column(name: "monto_solicitado", type: "number(19,2)")

			column(name: "motivo", type: "varchar2(255)")

			column(name: "nombre", type: "varchar2(255)")

			column(name: "nombre_sucursal", type: "varchar2(255)")

			column(name: "numero_cliente", type: "varchar2(255)")

			column(name: "observaciones", type: "varchar2(255)")

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "promocion", type: "varchar2(255)")

			column(name: "referencia", type: "varchar2(255)")

			column(name: "vendedor", type: "varchar2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-40") {
		createTable(tableName: "prestamo_documento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "prestamo_docuPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "documento_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nombre_archivo", type: "varchar2(100)") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-41") {
		createTable(tableName: "prestamo_pago") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "prestamo_pagoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_pago", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "importe_pago", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-42") {
		createTable(tableName: "pro_promocion") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pro_promocionPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_promocion", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_fin_vigencia", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_inicio_vigencia", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "iva", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "metodo_calculo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nombre_promocion", type: "varchar2(100)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_pagos", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "periodicidad_pagos_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "periodicidad_tasa_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "tasa_de_interes", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "vigencia_indefinida", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-43") {
		createTable(tableName: "pro_promocion_accesorio") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "pro_promocion_accPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "accesorio_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "forma_aplicacion_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "orden", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "pro_promocion_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-44") {
		createTable(tableName: "product") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "productPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "price", type: "number(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-45") {
		createTable(tableName: "publicacion_det") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "publicacion_dPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_cia", type: "varchar2(5)") {
				constraints(nullable: "false")
			}

			column(name: "clave_sucursal", type: "varchar2(5)") {
				constraints(nullable: "false")
			}

			column(name: "concepto", type: "varchar2(3)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_pago_anio", type: "varchar2(4)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_pago_dia", type: "varchar2(2)") {
				constraints(nullable: "false")
			}

			column(name: "fecha_pago_mes", type: "varchar2(2)") {
				constraints(nullable: "false")
			}

			column(name: "id_cr", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "importe_moratorios", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_pago", type: "number(9,2)") {
				constraints(nullable: "false")
			}

			column(name: "lista_cobro_detalle_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "numero_cliente", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "numero_operacion", type: "varchar2(26)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar2(10)") {
				constraints(nullable: "false")
			}

			column(name: "publicacion_lote_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "referencia", type: "varchar2(26)") {
				constraints(nullable: "false")
			}

			column(name: "respuesta_cr", type: "varchar2(255)")

			column(name: "tipo_pago", type: "varchar2(3)") {
				constraints(nullable: "false")
			}

			column(name: "usuario", type: "varchar2(10)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-46") {
		createTable(tableName: "publicacion_lote") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "publicacion_lPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha_medio", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_registro", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "importe_lote", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-47") {
		createTable(tableName: "purchase_order") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "purchase_ordePK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "amount_total", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "customer_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "notes", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "paid", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "ready", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-48") {
		createTable(tableName: "registration_code") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_PK")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-49") {
		createTable(tableName: "REL_TIPOS_EMPLEADOS") {
			column(name: "ID_DEPENDENCIA", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "ID_TIPO_EMPLEADO", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-50") {
		createTable(tableName: "requestmap") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "requestmapPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "config_attribute", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-51") {
		createTable(tableName: "rol") {
			column(name: "id", type: "varchar2(255)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-52") {
		createTable(tableName: "rs_cliente") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_clientePK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "apellido_materno", type: "varchar2(255)")

			column(name: "apellido_paterno", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_nomina", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "persona_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "primer_nombre", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar2(255)")

			column(name: "segundo_nombre", type: "varchar2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-53") {
		createTable(tableName: "rs_cliente_cta_bancaria") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_cliente_ctPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "banco_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clabe", type: "varchar2(25)") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "deposito_prestamo", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "numero_de_cuenta", type: "varchar2(25)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-54") {
		createTable(tableName: "rs_cliente_empleo") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_cliente_emPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "area", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "domicilio_id", type: "number")

			column(name: "fecha_ingreso", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "ingreso_mensual", type: "number(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-55") {
		createTable(tableName: "rs_cliente_ent_dependencia") {
			column(name: "rs_cliente_dependencia_id", type: "number")

			column(name: "ent_dependencia_id", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-56") {
		createTable(tableName: "rs_cliente_referencia") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_cliente_rePK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cliente_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_telefono_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nombre_completo", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "telefono", type: "varchar2(15)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-57") {
		createTable(tableName: "rs_gral_asentamiento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_gral_asentPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "codigo_postal", type: "varchar2(5)") {
				constraints(nullable: "false")
			}

			column(name: "delegacion_municipio_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nombre_asentamiento", type: "varchar2(100)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_asentamiento", type: "varchar2(100)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-58") {
		createTable(tableName: "rs_gral_delegacion_municipio") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_gral_delegPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "estado_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "nombre_ciudad", type: "varchar2(50)")

			column(name: "nombre_delegacion_municipio", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-59") {
		createTable(tableName: "rs_gral_domicilio") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_gral_domicPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "anios_residencia", type: "number")

			column(name: "calle", type: "varchar2(100)") {
				constraints(nullable: "false")
			}

			column(name: "comentarios", type: "varchar2(300)")

			column(name: "entre_calles", type: "varchar2(300)")

			column(name: "es_fiscal", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "numero_exterior", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "numero_interior", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "persona_id", type: "number")

			column(name: "rs_gral_asentamiento_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "sucursal_id", type: "number")

			column(name: "tipo_vivienda", type: "varchar2(10)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-60") {
		createTable(tableName: "rs_gral_estado") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_gral_estadPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "alias_estado", type: "varchar2(255)")

			column(name: "cve_estado", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_estado", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "region_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-61") {
		createTable(tableName: "rs_gral_telefono") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_gral_telefPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_telefono_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "persona_id", type: "number")

			column(name: "telefono", type: "varchar2(15)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-62") {
		createTable(tableName: "rs_persona") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rs_personaPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "apellido_materno", type: "varchar2(25)")

			column(name: "apellido_paterno", type: "varchar2(25)") {
				constraints(nullable: "false")
			}

			column(name: "curp", type: "varchar2(255)")

			column(name: "email", type: "varchar2(255)")

			column(name: "entidad_nacimiento_id", type: "number")

			column(name: "escolaridad_id", type: "number")

			column(name: "estado_civil", type: "varchar2(28)")

			column(name: "fecha_nacimiento", type: "timestamp")

			column(name: "identificacion_oficial_id", type: "number")

			column(name: "nombre_alterno", type: "varchar2(50)")

			column(name: "numero_identificacion_oficial", type: "varchar2(255)")

			column(name: "numero_imss", type: "varchar2(50)")

			column(name: "primer_nombre", type: "varchar2(25)") {
				constraints(nullable: "false")
			}

			column(name: "rfc", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "segundo_nombre", type: "varchar2(25)")

			column(name: "sexo", type: "varchar2(9)")

			column(name: "usuario_id", type: "varchar2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-63") {
		createTable(tableName: "rs_persona_tipos_persona") {
			column(name: "rs_persona_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "sim_cat_tipo_persona_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-64") {
		createTable(tableName: "sim_cat_accesorio") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_accesPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "concepto_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "tipo_accesorio_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-65") {
		createTable(tableName: "sim_cat_banco") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_bancoPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_banco", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_banco", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-66") {
		createTable(tableName: "sim_cat_cr_motivo") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_cr_moPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "codigo", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar2(80)") {
				constraints(nullable: "false")
			}

			column(name: "proceso", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-67") {
		createTable(tableName: "sim_cat_desc_telefono") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_desc_PK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_descripcion_telefono", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_descripcion_telefono", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-68") {
		createTable(tableName: "sim_cat_documento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_documPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_documento", type: "varchar2(40)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion", type: "varchar2(300)")

			column(name: "nombre_documento", type: "varchar2(80)") {
				constraints(nullable: "false")
			}

			column(name: "tipo_documento_id", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-69") {
		createTable(tableName: "sim_cat_escolaridad") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_escolPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_escolaridad", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_escolaridad", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-70") {
		createTable(tableName: "sim_cat_evento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_eventPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "evento", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-71") {
		createTable(tableName: "sim_cat_forma_aplicacion") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_forma_apliPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_forma_aplicacion", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_forma_aplicacion", type: "varchar2(60)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-72") {
		createTable(tableName: "sim_cat_forma_entrega") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_forma_entPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_forma_entrega", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_forma_entrega", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-73") {
		createTable(tableName: "sim_cat_lista_cobro_estatus") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_listaPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_lista_estatus", type: "varchar2(40)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_lista_estatus", type: "varchar2(150)")

			column(name: "nombre_lista_estatus", type: "varchar2(90)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-74") {
		createTable(tableName: "sim_cat_metodo_calculo") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_metodPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_metodo_calculo", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_metodo_calculo", type: "varchar2(300)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_metodo_calculo", type: "varchar2(150)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-75") {
		createTable(tableName: "sim_cat_pais") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_paisPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_pais", type: "varchar2(10)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_pais", type: "varchar2(30)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-76") {
		createTable(tableName: "sim_cat_periodicidad") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_perioPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "cantidad_pagos", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_periodicidad", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_periodicidad", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "numero_dias", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-77") {
		createTable(tableName: "sim_cat_tipo_accesorio") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_tipo_accPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_tipo_accesorio", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_tipo_accesorio", type: "varchar2(80)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-78") {
		createTable(tableName: "sim_cat_tipo_asentamiento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_tipo_asePK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_tipo_asentamiento", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_tipo_asentamiento", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-79") {
		createTable(tableName: "sim_cat_tipo_documento") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_tipo_docPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_tipo_documento", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_tipo_documento", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-80") {
		createTable(tableName: "sim_cat_tipo_emp") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_tipo_empPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_tipo_empleado_dep", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_tipo_empleado_dep", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-81") {
		createTable(tableName: "sim_cat_tipo_persona") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_tipo_perPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_tipo_persona", type: "varchar2(15)") {
				constraints(nullable: "false")
			}

			column(name: "descripcion_tipo_persona", type: "varchar2(150)")

			column(name: "nombre_tipo_persona", type: "varchar2(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-82") {
		createTable(tableName: "sim_cat_unidad") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sim_cat_unidaPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "clave_unidad", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_unidad", type: "varchar2(20)") {
				constraints(nullable: "false")
			}

			column(name: "valor", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-83") {
		createTable(tableName: "solicitud_credito") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "solicitud_crePK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "approval_remark", type: "varchar2(255)")

			column(name: "approval_status", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "cantidad_solicitada", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp")

			column(name: "motivo", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "nombre_solicitante", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "reenviar_solicitud", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-84") {
		createTable(tableName: "solicitud_prestamo") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "solicitud_prePK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "approval_status", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "correo_solicitante", type: "varchar2(255)")

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "explicacion_credito", type: "varchar2(255)")

			column(name: "explicacion_solicitud", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp")

			column(name: "nombre_solicitante", type: "varchar2(50)") {
				constraints(nullable: "false")
			}

			column(name: "prestamo", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_autorizado", type: "number")

			column(name: "sueldo_mensual", type: "number(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-85") {
		createTable(tableName: "tabla_amortizacion_accesorio") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tabla_amortiz_accPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "accesorio_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "importe_accesorio", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_accesorio_pagado", type: "number(19,2)")

			column(name: "importe_iva_accesorio", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "importe_iva_accesorio_pagado", type: "number(19,2)")

			column(name: "tabla_amortizacion_id", type: "number") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-86") {
		createTable(tableName: "tabla_amortizacion_registro") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tabla_amortiz_regPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "fecha", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fecha_pago_ultimo", type: "timestamp")

			column(name: "fecha_valor_calculado", type: "timestamp")

			column(name: "imp_capital", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "imp_capital_pagado", type: "number(19,2)")

			column(name: "imp_interes", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "imp_interes_pagado", type: "number(19,2)")

			column(name: "imp_iva_interes", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "imp_iva_interes_pagado", type: "number(19,2)")

			column(name: "imp_pago", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "imp_pago_pagado", type: "number(19,2)")

			column(name: "imp_saldo_final", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "imp_saldo_inicial", type: "number(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "lista_cobro_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "numero_pago", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "pagado", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "pago_puntual", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "prestamo_id", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "tasa_interes", type: "number(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-87") {
		createTable(tableName: "usuario") {
			column(name: "id", type: "varchar2(255)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "usuarioPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar2(255)")

			column(name: "enabled", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar2(255)")

			column(name: "is_password_encoded", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "varchar2(255)")

			column(name: "password", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-88") {
		createTable(tableName: "usuario_acceso") {
			column(name: "id", type: "number") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "usuario_accesPK")
			}

			column(name: "version", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "acceso_todo", type: "number") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-89") {
		createTable(tableName: "usuario_acceso_ent_region") {
			column(name: "usuario_acceso_regiones_id", type: "number")

			column(name: "ent_region_id", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-90") {
		createTable(tableName: "usuario_acceso_ent_sucursal") {
			column(name: "usuario_acceso_sucursales_id", type: "number")

			column(name: "ent_sucursal_id", type: "number")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-91") {
		createTable(tableName: "usuario_rol") {
			column(name: "rol_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}

			column(name: "usuario_id", type: "varchar2(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-92") {
		addPrimaryKey(columnNames: "ent_dependencia_id, pro_promocion_id", tableName: "ent_dependencia_promocion")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-93") {
		addPrimaryKey(columnNames: "rs_persona_id, sim_cat_tipo_persona_id", tableName: "rs_persona_tipos_persona")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-94") {
		addPrimaryKey(columnNames: "rol_id, usuario_id", constraintName: "usuario_rolPK", tableName: "usuario_rol")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-95") {
		addForeignKeyConstraint(baseColumnNames: "contact_id", baseTableName: "address", constraintName: "FKBB979BF45B4C908F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contact", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-96") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "call_center", constraintName: "FKD4458956EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-97") {
		addForeignKeyConstraint(baseColumnNames: "customer_id", baseTableName: "customer_audit", constraintName: "FK8617A03AF2D21C9F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "customer", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-98") {
		addForeignKeyConstraint(baseColumnNames: "detalle_registro_id", baseTableName: "dummy_cobranza", constraintName: "FK2E3B1D87BAE40654", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tabla_amortizacion_registro", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-99") {
		addForeignKeyConstraint(baseColumnNames: "persona_id", baseTableName: "emp_empleado", constraintName: "FK9B1B0A62D994BF21", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_persona", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-100") {
		addForeignKeyConstraint(baseColumnNames: "puesto_id", baseTableName: "emp_empleado", constraintName: "FK9B1B0A623C489099", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "emp_puesto", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-101") {
		addForeignKeyConstraint(baseColumnNames: "sucursal_id", baseTableName: "emp_empleado", constraintName: "FK9B1B0A627251421C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_sucursal", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-102") {
		addForeignKeyConstraint(baseColumnNames: "depende_de_id", baseTableName: "emp_puesto", constraintName: "FK5BCFAE85C4116620", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "emp_puesto", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-103") {
		addForeignKeyConstraint(baseColumnNames: "sucursal_id", baseTableName: "ent_delegacion", constraintName: "FKA8F78EAD7251421C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_sucursal", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-104") {
		addForeignKeyConstraint(baseColumnNames: "ent_dependencia_id", baseTableName: "ent_dependencia_promocion", constraintName: "FK5E21D987A2269A2C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_dependencia", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-105") {
		addForeignKeyConstraint(baseColumnNames: "pro_promocion_id", baseTableName: "ent_dependencia_promocion", constraintName: "FK5E21D987DC993F49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pro_promocion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-106") {
		addForeignKeyConstraint(baseColumnNames: "sucursal_id", baseTableName: "ent_oficina", constraintName: "FK153351B77251421C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_sucursal", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-107") {
		addForeignKeyConstraint(baseColumnNames: "estado_id", baseTableName: "ent_sucursal", constraintName: "FK40FDCF5435D313A9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_gral_estado", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-108") {
		addForeignKeyConstraint(baseColumnNames: "dependencia_id", baseTableName: "fecha_evento", constraintName: "FK34002017DD160858", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_dependencia", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-109") {
		addForeignKeyConstraint(baseColumnNames: "evento_id", baseTableName: "fecha_evento", constraintName: "FK34002017EF003D8D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_evento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-110") {
		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "line_item", constraintName: "FK94ED5C7E3F156F15", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-111") {
		addForeignKeyConstraint(baseColumnNames: "purchase_order_id", baseTableName: "line_item", constraintName: "FK94ED5C7E4A5DA772", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "purchase_order", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-112") {
		addForeignKeyConstraint(baseColumnNames: "dependencia_id", baseTableName: "lista_cobro", constraintName: "FK6260E277DD160858", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_dependencia", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-113") {
		addForeignKeyConstraint(baseColumnNames: "estatus_id", baseTableName: "lista_cobro", constraintName: "FK6260E277FA699F7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_lista_cobro_estatus", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-114") {
		addForeignKeyConstraint(baseColumnNames: "periodicidad_id", baseTableName: "lista_cobro", constraintName: "FK6260E2778C9C7CCD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_periodicidad", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-115") {
		addForeignKeyConstraint(baseColumnNames: "amortizacion_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAF15EDF8FF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tabla_amortizacion_registro", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-116") {
		addForeignKeyConstraint(baseColumnNames: "lista_cobro_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAFFAC0148D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lista_cobro", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-117") {
		addForeignKeyConstraint(baseColumnNames: "pago_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAF51B73DB5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo_pago", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-118") {
		addForeignKeyConstraint(baseColumnNames: "tipo_empleado_dep_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAF84C83195", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_tipo_emp", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-119") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "lista_cobro_detalle", constraintName: "FK41971FAFB6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-120") {
		addForeignKeyConstraint(baseColumnNames: "estatus_lista_cobro_id", baseTableName: "lista_cobro_proceso", constraintName: "FKD24B40A3CDB238BF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_lista_cobro_estatus", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-121") {
		addForeignKeyConstraint(baseColumnNames: "lista_cobro_id", baseTableName: "lista_cobro_proceso", constraintName: "FKD24B40A3FAC0148D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lista_cobro", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-122") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "lista_cobro_proceso", constraintName: "FKD24B40A3B6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-123") {
		addForeignKeyConstraint(baseColumnNames: "pais_id", baseTableName: "pfin_cat_dia_festivo", constraintName: "FK6666E732880C008D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_pais", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-124") {
		addForeignKeyConstraint(baseColumnNames: "afecta_id", baseTableName: "pfin_cat_operacion", constraintName: "FKBEDB1E4BDEEBD66", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_afecta_operacion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-125") {
		addForeignKeyConstraint(baseColumnNames: "concepto_id", baseTableName: "pfin_cat_operacion_concepto", constraintName: "FKD6EFCA7B9F810BC6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_concepto", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-126") {
		addForeignKeyConstraint(baseColumnNames: "operacion_id", baseTableName: "pfin_cat_operacion_concepto", constraintName: "FKD6EFCA7B91AA9CCE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_operacion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-127") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "pfin_cuenta", constraintName: "FK3D242B6CA3A068E1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-128") {
		addForeignKeyConstraint(baseColumnNames: "cancela_transaccion_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB94CC36D42", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_movimiento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-129") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB9C1C07D84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-130") {
		addForeignKeyConstraint(baseColumnNames: "divisa_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB9F33DD4C4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_divisa", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-131") {
		addForeignKeyConstraint(baseColumnNames: "operacion_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB991AA9CCE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_operacion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-132") {
		addForeignKeyConstraint(baseColumnNames: "pfin_pre_movimiento_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB955469E53", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_pre_movimiento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-133") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB9EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-134") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_pago_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB9741B475", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo_pago", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-135") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "pfin_movimiento", constraintName: "FK526F9DB9B6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-136") {
		addForeignKeyConstraint(baseColumnNames: "concepto_id", baseTableName: "pfin_movimiento_det", constraintName: "FK31B4CC8D9F810BC6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_concepto", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-137") {
		addForeignKeyConstraint(baseColumnNames: "movimiento_id", baseTableName: "pfin_movimiento_det", constraintName: "FK31B4CC8D66C78964", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_movimiento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-138") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "pfin_pre_movimiento", constraintName: "FK99E81AD5C1C07D84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-139") {
		addForeignKeyConstraint(baseColumnNames: "divisa_id", baseTableName: "pfin_pre_movimiento", constraintName: "FK99E81AD5F33DD4C4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_divisa", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-140") {
		addForeignKeyConstraint(baseColumnNames: "operacion_id", baseTableName: "pfin_pre_movimiento", constraintName: "FK99E81AD591AA9CCE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_operacion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-141") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "pfin_pre_movimiento", constraintName: "FK99E81AD5EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-142") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "pfin_pre_movimiento", constraintName: "FK99E81AD5B6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-143") {
		addForeignKeyConstraint(baseColumnNames: "concepto_id", baseTableName: "pfin_pre_movimiento_det", constraintName: "FKE0315BA99F810BC6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_concepto", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-144") {
		addForeignKeyConstraint(baseColumnNames: "pre_movimiento_id", baseTableName: "pfin_pre_movimiento_det", constraintName: "FKE0315BA923194FF7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_pre_movimiento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-145") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "pfin_saldo", constraintName: "FKF24D4145C1C07D84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cuenta", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-146") {
		addForeignKeyConstraint(baseColumnNames: "divisa_id", baseTableName: "pfin_saldo", constraintName: "FKF24D4145F33DD4C4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_divisa", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-147") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFFA3A068E1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-148") {
		addForeignKeyConstraint(baseColumnNames: "delegacion_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFFEB0A6FC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_delegacion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-149") {
		addForeignKeyConstraint(baseColumnNames: "dependencia_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFFDD160858", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_dependencia", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-150") {
		addForeignKeyConstraint(baseColumnNames: "forma_de_dispercion_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFFC8BFA758", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_forma_entrega", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-151") {
		addForeignKeyConstraint(baseColumnNames: "movito_respuesta_cr_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFF3C58D18F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_cr_motivo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-152") {
		addForeignKeyConstraint(baseColumnNames: "promocion_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFFB74906F7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pro_promocion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-153") {
		addForeignKeyConstraint(baseColumnNames: "sucursal_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFF7251421C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_sucursal", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-154") {
		addForeignKeyConstraint(baseColumnNames: "tipo_empleado_dep_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFF84C83195", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_tipo_emp", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-155") {
		addForeignKeyConstraint(baseColumnNames: "vendedor_id", baseTableName: "prestamo", constraintName: "FKB3EE3EFF6E81DE1D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "emp_empleado", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-156") {
		addForeignKeyConstraint(baseColumnNames: "accesorio_id", baseTableName: "prestamo_accesorio", constraintName: "FKFEB54E78BAD4EDE7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_accesorio", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-157") {
		addForeignKeyConstraint(baseColumnNames: "periodicidad_id", baseTableName: "prestamo_accesorio", constraintName: "FKFEB54E788C9C7CCD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_periodicidad", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-158") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "prestamo_accesorio", constraintName: "FKFEB54E78EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-159") {
		addForeignKeyConstraint(baseColumnNames: "unidad_id", baseTableName: "prestamo_accesorio", constraintName: "FKFEB54E78D093F64D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_unidad", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-160") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "prestamo_cr_cartera", constraintName: "FK48AC9FA4EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-161") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "prestamo_cr_comprada", constraintName: "FKA8C3A30BEBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-162") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "prestamo_cr_respuesta", constraintName: "FK4E793530EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-163") {
		addForeignKeyConstraint(baseColumnNames: "documento_id", baseTableName: "prestamo_documento", constraintName: "FKB505197440BB3C67", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_documento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-164") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "prestamo_documento", constraintName: "FKB5051974EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-165") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "prestamo_pago", constraintName: "FK15DB63D9EBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-166") {
		addForeignKeyConstraint(baseColumnNames: "metodo_calculo_id", baseTableName: "pro_promocion", constraintName: "FKCD2E3B42509AACCA", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_metodo_calculo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-167") {
		addForeignKeyConstraint(baseColumnNames: "periodicidad_pagos_id", baseTableName: "pro_promocion", constraintName: "FKCD2E3B42B2E3B1B2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_periodicidad", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-168") {
		addForeignKeyConstraint(baseColumnNames: "periodicidad_tasa_id", baseTableName: "pro_promocion", constraintName: "FKCD2E3B42463D0105", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_periodicidad", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-169") {
		addForeignKeyConstraint(baseColumnNames: "accesorio_id", baseTableName: "pro_promocion_accesorio", constraintName: "FKED0512FBBAD4EDE7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_accesorio", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-170") {
		addForeignKeyConstraint(baseColumnNames: "forma_aplicacion_id", baseTableName: "pro_promocion_accesorio", constraintName: "FKED0512FB8E045422", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_forma_aplicacion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-171") {
		addForeignKeyConstraint(baseColumnNames: "pro_promocion_id", baseTableName: "pro_promocion_accesorio", constraintName: "FKED0512FBDC993F49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pro_promocion", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-172") {
		addForeignKeyConstraint(baseColumnNames: "lista_cobro_detalle_id", baseTableName: "publicacion_det", constraintName: "FK71BF64B1BB84B518", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lista_cobro_detalle", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-173") {
		addForeignKeyConstraint(baseColumnNames: "publicacion_lote_id", baseTableName: "publicacion_det", constraintName: "FK71BF64B13BDE0F20", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "publicacion_lote", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-174") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "publicacion_lote", constraintName: "FKC630FA56B6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-175") {
		addForeignKeyConstraint(baseColumnNames: "customer_id", baseTableName: "purchase_order", constraintName: "FK71A56A90F2D21C9F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "customer", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-176") {
		addForeignKeyConstraint(baseColumnNames: "ID_DEPENDENCIA", baseTableName: "REL_TIPOS_EMPLEADOS", constraintName: "FK809704829E59F046", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_dependencia", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-177") {
		addForeignKeyConstraint(baseColumnNames: "ID_TIPO_EMPLEADO", baseTableName: "REL_TIPOS_EMPLEADOS", constraintName: "FK80970482C0E92CB3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_tipo_emp", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-178") {
		addForeignKeyConstraint(baseColumnNames: "persona_id", baseTableName: "rs_cliente", constraintName: "FK234D69FCD994BF21", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_persona", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-179") {
		addForeignKeyConstraint(baseColumnNames: "banco_id", baseTableName: "rs_cliente_cta_bancaria", constraintName: "FKC9C9B7EFD20C4987", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_banco", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-180") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "rs_cliente_cta_bancaria", constraintName: "FKC9C9B7EFA3A068E1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-181") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "rs_cliente_empleo", constraintName: "FKED20A3F1A3A068E1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-182") {
		addForeignKeyConstraint(baseColumnNames: "domicilio_id", baseTableName: "rs_cliente_empleo", constraintName: "FKED20A3F1F8B858B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_gral_domicilio", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-183") {
		addForeignKeyConstraint(baseColumnNames: "ent_dependencia_id", baseTableName: "rs_cliente_ent_dependencia", constraintName: "FKF047BAEFA2269A2C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_dependencia", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-184") {
		addForeignKeyConstraint(baseColumnNames: "rs_cliente_dependencia_id", baseTableName: "rs_cliente_ent_dependencia", constraintName: "FKF047BAEF8B338B78", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-185") {
		addForeignKeyConstraint(baseColumnNames: "cliente_id", baseTableName: "rs_cliente_referencia", constraintName: "FKBDB0C115A3A068E1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_cliente", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-186") {
		addForeignKeyConstraint(baseColumnNames: "descripcion_telefono_id", baseTableName: "rs_cliente_referencia", constraintName: "FKBDB0C1152B02490C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_desc_telefono", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-187") {
		addForeignKeyConstraint(baseColumnNames: "delegacion_municipio_id", baseTableName: "rs_gral_asentamiento", constraintName: "FKFC08AB53E3F9018", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_gral_delegacion_municipio", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-188") {
		addForeignKeyConstraint(baseColumnNames: "estado_id", baseTableName: "rs_gral_delegacion_municipio", constraintName: "FKFEEDB3D235D313A9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_gral_estado", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-189") {
		addForeignKeyConstraint(baseColumnNames: "persona_id", baseTableName: "rs_gral_domicilio", constraintName: "FK456F607AD994BF21", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_persona", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-190") {
		addForeignKeyConstraint(baseColumnNames: "rs_gral_asentamiento_id", baseTableName: "rs_gral_domicilio", constraintName: "FK456F607AECEA4C7E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_gral_asentamiento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-191") {
		addForeignKeyConstraint(baseColumnNames: "sucursal_id", baseTableName: "rs_gral_domicilio", constraintName: "FK456F607A7251421C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_sucursal", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-192") {
		addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "rs_gral_estado", constraintName: "FK9A8592711B17AE1C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_region", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-193") {
		addForeignKeyConstraint(baseColumnNames: "descripcion_telefono_id", baseTableName: "rs_gral_telefono", constraintName: "FK3397AD3F2B02490C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_desc_telefono", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-194") {
		addForeignKeyConstraint(baseColumnNames: "persona_id", baseTableName: "rs_gral_telefono", constraintName: "FK3397AD3FD994BF21", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_persona", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-195") {
		addForeignKeyConstraint(baseColumnNames: "entidad_nacimiento_id", baseTableName: "rs_persona", constraintName: "FKC791B3AE2A7AE540", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_gral_estado", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-196") {
		addForeignKeyConstraint(baseColumnNames: "escolaridad_id", baseTableName: "rs_persona", constraintName: "FKC791B3AEDA420E07", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_escolaridad", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-197") {
		addForeignKeyConstraint(baseColumnNames: "identificacion_oficial_id", baseTableName: "rs_persona", constraintName: "FKC791B3AE6C5C4158", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_documento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-198") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "rs_persona", constraintName: "FKC791B3AEB6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-199") {
		addForeignKeyConstraint(baseColumnNames: "rs_persona_id", baseTableName: "rs_persona_tipos_persona", constraintName: "FK4F8BD85BC59943DF", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rs_persona", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-200") {
		addForeignKeyConstraint(baseColumnNames: "sim_cat_tipo_persona_id", baseTableName: "rs_persona_tipos_persona", constraintName: "FK4F8BD85BAF96006D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_tipo_persona", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-201") {
		addForeignKeyConstraint(baseColumnNames: "concepto_id", baseTableName: "sim_cat_accesorio", constraintName: "FK9CA779279F810BC6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "pfin_cat_concepto", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-202") {
		addForeignKeyConstraint(baseColumnNames: "tipo_accesorio_id", baseTableName: "sim_cat_accesorio", constraintName: "FK9CA779275204A1BE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_tipo_accesorio", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-203") {
		addForeignKeyConstraint(baseColumnNames: "tipo_documento_id", baseTableName: "sim_cat_documento", constraintName: "FK52F74423D7EAF03E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_tipo_documento", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-204") {
		addForeignKeyConstraint(baseColumnNames: "accesorio_id", baseTableName: "tabla_amortizacion_accesorio", constraintName: "FK941E57EBAD4EDE7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sim_cat_accesorio", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-205") {
		addForeignKeyConstraint(baseColumnNames: "tabla_amortizacion_id", baseTableName: "tabla_amortizacion_accesorio", constraintName: "FK941E57E7DD2B68A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tabla_amortizacion_registro", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-206") {
		addForeignKeyConstraint(baseColumnNames: "lista_cobro_id", baseTableName: "tabla_amortizacion_registro", constraintName: "FKFC4201ADFAC0148D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lista_cobro", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-207") {
		addForeignKeyConstraint(baseColumnNames: "prestamo_id", baseTableName: "tabla_amortizacion_registro", constraintName: "FKFC4201ADEBEEDE56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "prestamo", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-208") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "usuario_acceso", constraintName: "FK3E116F91B6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-209") {
		addForeignKeyConstraint(baseColumnNames: "ent_region_id", baseTableName: "usuario_acceso_ent_region", constraintName: "FK32A1FE36A20047C8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_region", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-210") {
		addForeignKeyConstraint(baseColumnNames: "usuario_acceso_regiones_id", baseTableName: "usuario_acceso_ent_region", constraintName: "FK32A1FE364431D774", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario_acceso", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-211") {
		addForeignKeyConstraint(baseColumnNames: "ent_sucursal_id", baseTableName: "usuario_acceso_ent_sucursal", constraintName: "FKC2375D42E17A20C8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ent_sucursal", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-212") {
		addForeignKeyConstraint(baseColumnNames: "usuario_acceso_sucursales_id", baseTableName: "usuario_acceso_ent_sucursal", constraintName: "FKC2375D42349CBAE8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario_acceso", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-213") {
		addForeignKeyConstraint(baseColumnNames: "rol_id", baseTableName: "usuario_rol", constraintName: "FK3118953E8FC52F76", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rol", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-214") {
		addForeignKeyConstraint(baseColumnNames: "usuario_id", baseTableName: "usuario_rol", constraintName: "FK3118953EB6C25116", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "usuario", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-215") {
		createIndex(indexName: "clave_promotor_unique", tableName: "emp_empleado", unique: "true") {
			column(name: "clave_promotor")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-216") {
		createIndex(indexName: "persona_idemp_unique", tableName: "emp_empleado", unique: "true") {
			column(name: "persona_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-217") {
		createIndex(indexName: "clave_puesto_unique", tableName: "emp_puesto", unique: "true") {
			column(name: "clave_puesto")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-218") {
		createIndex(indexName: "nombre_puesto_unique", tableName: "emp_puesto", unique: "true") {
			column(name: "nombre_puesto")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-219") {
		createIndex(indexName: "clave_delegacion_unique", tableName: "ent_delegacion", unique: "true") {
			column(name: "clave_delegacion")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-220") {
		createIndex(indexName: "nombre_delegacion_unique", tableName: "ent_delegacion", unique: "true") {
			column(name: "nombre_delegacion")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-221") {
		createIndex(indexName: "clave_dependencia_unique", tableName: "ent_dependencia", unique: "true") {
			column(name: "clave_dependencia")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-222") {
		createIndex(indexName: "clave_oficina_unique", tableName: "ent_oficina", unique: "true") {
			column(name: "clave_oficina")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-223") {
		createIndex(indexName: "nombre_oficina_unique", tableName: "ent_oficina", unique: "true") {
			column(name: "nombre_oficina")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-224") {
		createIndex(indexName: "clave_region_unique", tableName: "ent_region", unique: "true") {
			column(name: "clave_region")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-225") {
		createIndex(indexName: "nombre_region_unique", tableName: "ent_region", unique: "true") {
			column(name: "nombre_region")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-226") {
		createIndex(indexName: "clave_sucursal_unique", tableName: "ent_sucursal", unique: "true") {
			column(name: "clave_sucursal")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-227") {
		createIndex(indexName: "nombre_sucursal_unique", tableName: "ent_sucursal", unique: "true") {
			column(name: "nombre_sucursal")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-228") {
		createIndex(indexName: "clave_afecta_unique", tableName: "pfin_cat_afecta_operacion", unique: "true") {
			column(name: "clave_afecta")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-229") {
		createIndex(indexName: "clave_concepto_unique", tableName: "pfin_cat_concepto", unique: "true") {
			column(name: "clave_concepto")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-230") {
		createIndex(indexName: "dia_festivo_unique", tableName: "pfin_cat_dia_festivo", unique: "true") {
			column(name: "descripcion_dia_festivo")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-231") {
		createIndex(indexName: "clave_operacion_unique", tableName: "pfin_cat_operacion", unique: "true") {
			column(name: "clave_operacion")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-232") {
		createIndex(indexName: "clave_medio_unique", tableName: "pfin_cat_parametro", unique: "true") {
			column(name: "clave_medio")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-233") {
		createIndex(indexName: "clave_divisa_unique", tableName: "pfin_divisa", unique: "true") {
			column(name: "clave_divisa")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-234") {
		createIndex(indexName: "divisa_unique", tableName: "pfin_divisa", unique: "true") {
			column(name: "descripcion_divisa")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-235") {
		createIndex(indexName: "pfin_pre_mvto_unique", tableName: "pfin_movimiento", unique: "true") {
			column(name: "pfin_pre_movimiento_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-236") {
		createIndex(indexName: "folio_solicitud_unique", tableName: "prestamo", unique: "true") {
			column(name: "folio_solicitud")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-237") {
		createIndex(indexName: "prestamo_id_unique", tableName: "prestamo_cr_comprada", unique: "true") {
			column(name: "prestamo_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-238") {
		createIndex(indexName: "cve_promocion_unique", tableName: "pro_promocion", unique: "true") {
			column(name: "clave_promocion")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-239") {
		createIndex(indexName: "id_cr_unique", tableName: "publicacion_det", unique: "true") {
			column(name: "id_cr")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-240") {
		createIndex(indexName: "l_cobro_det_unique", tableName: "publicacion_det", unique: "true") {
			column(name: "lista_cobro_detalle_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-241") {
		createIndex(indexName: "url_unique", tableName: "requestmap", unique: "true") {
			column(name: "url")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-242") {
		createIndex(indexName: "authority_unique", tableName: "rol", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-243") {
		createIndex(indexName: "persona_idcli_unique", tableName: "rs_cliente", unique: "true") {
			column(name: "persona_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-244") {
		createIndex(indexName: "clabe_unique", tableName: "rs_cliente_cta_bancaria", unique: "true") {
			column(name: "clabe")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-245") {
		createIndex(indexName: "area_unique", tableName: "rs_cliente_empleo", unique: "true") {
			column(name: "area")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-246") {
		createIndex(indexName: "cve_estado_unique", tableName: "rs_gral_estado", unique: "true") {
			column(name: "cve_estado")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-247") {
		createIndex(indexName: "nombre_estado_unique", tableName: "rs_gral_estado", unique: "true") {
			column(name: "nombre_estado")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-248") {
		createIndex(indexName: "email_unique", tableName: "rs_persona", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-249") {
		createIndex(indexName: "usuario_idper_unique", tableName: "rs_persona", unique: "true") {
			column(name: "usuario_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-250") {
		createIndex(indexName: "concepto_id_unique", tableName: "sim_cat_accesorio", unique: "true") {
			column(name: "concepto_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-251") {
		createIndex(indexName: "clave_banco_unique", tableName: "sim_cat_banco", unique: "true") {
			column(name: "clave_banco")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-252") {
		createIndex(indexName: "nom_banco_unique", tableName: "sim_cat_banco", unique: "true") {
			column(name: "nombre_banco")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-253") {
		createIndex(indexName: "cve_desc_tel_unique", tableName: "sim_cat_desc_telefono", unique: "true") {
			column(name: "clave_descripcion_telefono")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-254") {
		createIndex(indexName: "nom_desc_tel_unique", tableName: "sim_cat_desc_telefono", unique: "true") {
			column(name: "nombre_descripcion_telefono")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-255") {
		createIndex(indexName: "cve_doc_unique", tableName: "sim_cat_documento", unique: "true") {
			column(name: "clave_documento")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-256") {
		createIndex(indexName: "cve_esc_unique", tableName: "sim_cat_escolaridad", unique: "true") {
			column(name: "clave_escolaridad")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-257") {
		createIndex(indexName: "nom_escolaridad_unique", tableName: "sim_cat_escolaridad", unique: "true") {
			column(name: "nombre_escolaridad")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-258") {
		createIndex(indexName: "cve_forma_apli_unique", tableName: "sim_cat_forma_aplicacion", unique: "true") {
			column(name: "clave_forma_aplicacion")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-259") {
		createIndex(indexName: "nom_forma_apli_unique", tableName: "sim_cat_forma_aplicacion", unique: "true") {
			column(name: "nombre_forma_aplicacion")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-260") {
		createIndex(indexName: "cve_fma_entrega_unique", tableName: "sim_cat_forma_entrega", unique: "true") {
			column(name: "clave_forma_entrega")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-261") {
		createIndex(indexName: "nom_fma_entrega_unique", tableName: "sim_cat_forma_entrega", unique: "true") {
			column(name: "nombre_forma_entrega")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-262") {
		createIndex(indexName: "cve_lis_estatus_unique", tableName: "sim_cat_lista_cobro_estatus", unique: "true") {
			column(name: "clave_lista_estatus")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-263") {
		createIndex(indexName: "nom_lis_estatus_unique", tableName: "sim_cat_lista_cobro_estatus", unique: "true") {
			column(name: "nombre_lista_estatus")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-264") {
		createIndex(indexName: "cve_met_calculo_unique", tableName: "sim_cat_metodo_calculo", unique: "true") {
			column(name: "clave_metodo_calculo")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-265") {
		createIndex(indexName: "nom_met_calculo_unique", tableName: "sim_cat_metodo_calculo", unique: "true") {
			column(name: "nombre_metodo_calculo")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-266") {
		createIndex(indexName: "clave_pais_unique", tableName: "sim_cat_pais", unique: "true") {
			column(name: "clave_pais")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-267") {
		createIndex(indexName: "nombre_pais_unique", tableName: "sim_cat_pais", unique: "true") {
			column(name: "nombre_pais")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-268") {
		createIndex(indexName: "cve_periodicidad_unique", tableName: "sim_cat_periodicidad", unique: "true") {
			column(name: "clave_periodicidad")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-269") {
		createIndex(indexName: "nom_periodicidad_unique", tableName: "sim_cat_periodicidad", unique: "true") {
			column(name: "nombre_periodicidad")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-270") {
		createIndex(indexName: "cve_tipo_acc_unique", tableName: "sim_cat_tipo_accesorio", unique: "true") {
			column(name: "clave_tipo_accesorio")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-271") {
		createIndex(indexName: "nom_tipo_acc_unique", tableName: "sim_cat_tipo_accesorio", unique: "true") {
			column(name: "nombre_tipo_accesorio")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-272") {
		createIndex(indexName: "cve_tipo_asent_unique", tableName: "sim_cat_tipo_asentamiento", unique: "true") {
			column(name: "clave_tipo_asentamiento")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-273") {
		createIndex(indexName: "nom_tipo_asent_unique", tableName: "sim_cat_tipo_asentamiento", unique: "true") {
			column(name: "nombre_tipo_asentamiento")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-274") {
		createIndex(indexName: "cve_tipo_doc_unique", tableName: "sim_cat_tipo_documento", unique: "true") {
			column(name: "clave_tipo_documento")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-275") {
		createIndex(indexName: "nom_tipo_doc_unique", tableName: "sim_cat_tipo_documento", unique: "true") {
			column(name: "nombre_tipo_documento")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-276") {
		createIndex(indexName: "cve_tipo_emp_dep_unique", tableName: "sim_cat_tipo_emp", unique: "true") {
			column(name: "clave_tipo_empleado_dep")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-277") {
		createIndex(indexName: "nom_tipo_emp_dep_unique", tableName: "sim_cat_tipo_emp", unique: "true") {
			column(name: "nombre_tipo_empleado_dep")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-278") {
		createIndex(indexName: "cve_tipo_persona_unique", tableName: "sim_cat_tipo_persona", unique: "true") {
			column(name: "clave_tipo_persona")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-279") {
		createIndex(indexName: "nom_tipo_persona_unique", tableName: "sim_cat_tipo_persona", unique: "true") {
			column(name: "nombre_tipo_persona")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-280") {
		createIndex(indexName: "username_unique", tableName: "usuario", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-281") {
		createIndex(indexName: "usuario_idusu_unique", tableName: "usuario_acceso", unique: "true") {
			column(name: "usuario_id")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360357535489-282") {
		createSequence(sequenceName: "hibernate_sequence")
	}
}
