databaseChangeLog = {

	changeSet(author: "miguel (generated)", id: "1360341948340-1") {
		createTable(tableName: "ACT_GE_BYTEARRAY") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657467", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "NAME_", type: "NVARCHAR2(255)")

			column(name: "DEPLOYMENT_ID_", type: "NVARCHAR2(64)")

			column(name: "BYTES_", type: "BLOB")

			column(name: "GENERATED_", type: "NUMBER(1,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-2") {
		createTable(tableName: "ACT_GE_PROPERTY") {
			column(name: "NAME_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657465", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "VALUE_", type: "NVARCHAR2(300)")

			column(name: "REV_", type: "INTEGER")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-3") {
		createTable(tableName: "ACT_HI_ACTINST") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657516", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "PROC_DEF_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "EXECUTION_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "ACT_ID_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "ACT_NAME_", type: "NVARCHAR2(255)")

			column(name: "ACT_TYPE_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "ASSIGNEE_", type: "NVARCHAR2(64)")

			column(name: "START_TIME_", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "END_TIME_", type: "TIMESTAMP(6)")

			column(name: "DURATION_", type: "NUMBER(19,0)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-4") {
		createTable(tableName: "ACT_HI_ATTACHMENT") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657531", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "USER_ID_", type: "NVARCHAR2(255)")

			column(name: "NAME_", type: "NVARCHAR2(255)")

			column(name: "DESCRIPTION_", type: "NVARCHAR2(2000)")

			column(name: "TYPE_", type: "NVARCHAR2(255)")

			column(name: "TASK_ID_", type: "NVARCHAR2(64)")

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "URL_", type: "NVARCHAR2(2000)")

			column(name: "CONTENT_ID_", type: "NVARCHAR2(64)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-5") {
		createTable(tableName: "ACT_HI_COMMENT") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657529", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "TYPE_", type: "NVARCHAR2(255)")

			column(name: "TIME_", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "USER_ID_", type: "NVARCHAR2(255)")

			column(name: "TASK_ID_", type: "NVARCHAR2(64)")

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "ACTION_", type: "NVARCHAR2(255)")

			column(name: "MESSAGE_", type: "NVARCHAR2(2000)")

			column(name: "FULL_MSG_", type: "BLOB")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-6") {
		createTable(tableName: "ACT_HI_DETAIL") {
			column(name: "ID_", type: "VARCHAR2(64 BYTE)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657526", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "TYPE_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "EXECUTION_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "TASK_ID_", type: "NVARCHAR2(64)")

			column(name: "ACT_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "NAME_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "VAR_TYPE_", type: "NVARCHAR2(64)")

			column(name: "REV_", type: "INTEGER")

			column(name: "TIME_", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "BYTEARRAY_ID_", type: "NVARCHAR2(64)")

			column(name: "DOUBLE_", type: "NUMBER(0,-127)")

			column(name: "LONG_", type: "NUMBER(19,0)")

			column(name: "TEXT_", type: "NVARCHAR2(2000)")

			column(name: "TEXT2_", type: "NVARCHAR2(2000)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-7") {
		createTable(tableName: "ACT_HI_PROCINST") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657507", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "BUSINESS_KEY_", type: "NVARCHAR2(255)")

			column(name: "PROC_DEF_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "START_TIME_", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "END_TIME_", type: "TIMESTAMP(6)")

			column(name: "DURATION_", type: "NUMBER(19,0)")

			column(name: "START_USER_ID_", type: "NVARCHAR2(255)")

			column(name: "START_ACT_ID_", type: "NVARCHAR2(255)")

			column(name: "END_ACT_ID_", type: "NVARCHAR2(255)")

			column(name: "SUPER_PROCESS_INSTANCE_ID_", type: "NVARCHAR2(64)")

			column(name: "DELETE_REASON_", type: "NVARCHAR2(2000)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-8") {
		createTable(tableName: "ACT_HI_TASKINST") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657519", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "PROC_DEF_ID_", type: "NVARCHAR2(64)")

			column(name: "TASK_DEF_KEY_", type: "NVARCHAR2(255)")

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "EXECUTION_ID_", type: "NVARCHAR2(64)")

			column(name: "PARENT_TASK_ID_", type: "NVARCHAR2(64)")

			column(name: "NAME_", type: "NVARCHAR2(255)")

			column(name: "DESCRIPTION_", type: "NVARCHAR2(2000)")

			column(name: "OWNER_", type: "NVARCHAR2(64)")

			column(name: "ASSIGNEE_", type: "NVARCHAR2(64)")

			column(name: "START_TIME_", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}

			column(name: "END_TIME_", type: "TIMESTAMP(6)")

			column(name: "DURATION_", type: "NUMBER(19,0)")

			column(name: "DELETE_REASON_", type: "NVARCHAR2(2000)")

			column(name: "PRIORITY_", type: "INTEGER")

			column(name: "DUE_DATE_", type: "TIMESTAMP(6)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-9") {
		createTable(tableName: "ACT_ID_GROUP") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657532", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "NAME_", type: "NVARCHAR2(255)")

			column(name: "TYPE_", type: "NVARCHAR2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-10") {
		createTable(tableName: "ACT_ID_INFO") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657535", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "USER_ID_", type: "NVARCHAR2(64)")

			column(name: "TYPE_", type: "NVARCHAR2(64)")

			column(name: "KEY_", type: "NVARCHAR2(255)")

			column(name: "VALUE_", type: "NVARCHAR2(255)")

			column(name: "PASSWORD_", type: "BLOB")

			column(name: "PARENT_ID_", type: "NVARCHAR2(255)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-11") {
		createTable(tableName: "ACT_ID_MEMBERSHIP") {
			column(name: "USER_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}

			column(name: "GROUP_ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-12") {
		createTable(tableName: "ACT_ID_USER") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657534", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "FIRST_", type: "NVARCHAR2(255)")

			column(name: "LAST_", type: "NVARCHAR2(255)")

			column(name: "EMAIL_", type: "NVARCHAR2(255)")

			column(name: "PWD_", type: "NVARCHAR2(255)")

			column(name: "PICTURE_ID_", type: "NVARCHAR2(64)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-13") {
		createTable(tableName: "ACT_RE_DEPLOYMENT") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657468", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "NAME_", type: "NVARCHAR2(255)")

			column(name: "DEPLOY_TIME_", type: "TIMESTAMP(6)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-14") {
		createTable(tableName: "ACT_RE_PROCDEF") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657479", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "CATEGORY_", type: "NVARCHAR2(255)")

			column(name: "NAME_", type: "NVARCHAR2(255)")

			column(name: "KEY_", type: "NVARCHAR2(255)")

			column(name: "VERSION_", type: "INTEGER")

			column(name: "DEPLOYMENT_ID_", type: "NVARCHAR2(64)")

			column(name: "RESOURCE_NAME_", type: "NVARCHAR2(2000)")

			column(name: "DGRM_RESOURCE_NAME_", type: "VARCHAR2(4000 BYTE)")

			column(name: "HAS_START_FORM_KEY_", type: "NUMBER(1,0)")

			column(name: "SUSPENSION_STATE_", type: "INTEGER")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-15") {
		createTable(tableName: "ACT_RU_EVENT_SUBSCR") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657489", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "EVENT_TYPE_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "EVENT_NAME_", type: "NVARCHAR2(255)")

			column(name: "EXECUTION_ID_", type: "NVARCHAR2(64)")

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "ACTIVITY_ID_", type: "NVARCHAR2(64)")

			column(name: "CONFIGURATION_", type: "NVARCHAR2(255)")

			column(name: "CREATED_", type: "TIMESTAMP(6)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-16") {
		createTable(tableName: "ACT_RU_EXECUTION") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657473", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "BUSINESS_KEY_", type: "NVARCHAR2(255)")

			column(name: "PARENT_ID_", type: "NVARCHAR2(64)")

			column(name: "PROC_DEF_ID_", type: "NVARCHAR2(64)")

			column(name: "SUPER_EXEC_", type: "NVARCHAR2(64)")

			column(name: "ACT_ID_", type: "NVARCHAR2(255)")

			column(name: "IS_ACTIVE_", type: "NUMBER(1,0)")

			column(name: "IS_CONCURRENT_", type: "NUMBER(1,0)")

			column(name: "IS_SCOPE_", type: "NUMBER(1,0)")

			column(name: "IS_EVENT_SCOPE_", type: "NUMBER(1,0)")

			column(name: "SUSPENSION_STATE_", type: "INTEGER")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-17") {
		createTable(tableName: "ACT_RU_IDENTITYLINK") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657481", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "GROUP_ID_", type: "NVARCHAR2(64)")

			column(name: "TYPE_", type: "NVARCHAR2(255)")

			column(name: "USER_ID_", type: "NVARCHAR2(64)")

			column(name: "TASK_ID_", type: "NVARCHAR2(64)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-18") {
		createTable(tableName: "ACT_RU_JOB") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657477", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "TYPE_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "LOCK_EXP_TIME_", type: "TIMESTAMP(6)")

			column(name: "LOCK_OWNER_", type: "NVARCHAR2(255)")

			column(name: "EXCLUSIVE_", type: "NUMBER(1,0)")

			column(name: "EXECUTION_ID_", type: "NVARCHAR2(64)")

			column(name: "PROCESS_INSTANCE_ID_", type: "NVARCHAR2(64)")

			column(name: "RETRIES_", type: "INTEGER")

			column(name: "EXCEPTION_STACK_ID_", type: "NVARCHAR2(64)")

			column(name: "EXCEPTION_MSG_", type: "NVARCHAR2(2000)")

			column(name: "DUEDATE_", type: "TIMESTAMP(6)")

			column(name: "REPEAT_", type: "NVARCHAR2(255)")

			column(name: "HANDLER_TYPE_", type: "NVARCHAR2(255)")

			column(name: "HANDLER_CFG_", type: "NVARCHAR2(2000)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-19") {
		createTable(tableName: "ACT_RU_TASK") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657480", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "EXECUTION_ID_", type: "NVARCHAR2(64)")

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "PROC_DEF_ID_", type: "NVARCHAR2(64)")

			column(name: "NAME_", type: "NVARCHAR2(255)")

			column(name: "PARENT_TASK_ID_", type: "NVARCHAR2(64)")

			column(name: "DESCRIPTION_", type: "NVARCHAR2(2000)")

			column(name: "TASK_DEF_KEY_", type: "NVARCHAR2(255)")

			column(name: "OWNER_", type: "NVARCHAR2(64)")

			column(name: "ASSIGNEE_", type: "NVARCHAR2(64)")

			column(name: "DELEGATION_", type: "NVARCHAR2(64)")

			column(name: "PRIORITY_", type: "INTEGER")

			column(name: "CREATE_TIME_", type: "TIMESTAMP(6)")

			column(name: "DUE_DATE_", type: "TIMESTAMP(6)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-20") {
		createTable(tableName: "ACT_RU_VARIABLE") {
			column(name: "ID_", type: "NVARCHAR2(64)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SYS_C00657485", primaryKeyTablespace: "SYSTEM")
			}

			column(name: "REV_", type: "INTEGER")

			column(name: "TYPE_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "NAME_", type: "NVARCHAR2(255)") {
				constraints(nullable: "false")
			}

			column(name: "EXECUTION_ID_", type: "NVARCHAR2(64)")

			column(name: "PROC_INST_ID_", type: "NVARCHAR2(64)")

			column(name: "TASK_ID_", type: "NVARCHAR2(64)")

			column(name: "BYTEARRAY_ID_", type: "NVARCHAR2(64)")

			column(name: "DOUBLE_", type: "NUMBER(0,-127)")

			column(name: "LONG_", type: "NUMBER(19,0)")

			column(name: "TEXT_", type: "NVARCHAR2(2000)")

			column(name: "TEXT2_", type: "NVARCHAR2(2000)")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-21") {
		addPrimaryKey(columnNames: "USER_ID_, GROUP_ID_", constraintName: "SYS_C00657533", tableName: "ACT_ID_MEMBERSHIP", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-22") {
		addUniqueConstraint(columnNames: "PROC_INST_ID_", constraintName: "SYS_C00657508", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "ACT_HI_PROCINST", tablespace: "SYSTEM")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-23") {
		addForeignKeyConstraint(baseColumnNames: "DEPLOYMENT_ID_", baseTableName: "ACT_GE_BYTEARRAY", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_BYTEARR_DEPL", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RE_DEPLOYMENT", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-24") {
		addForeignKeyConstraint(baseColumnNames: "GROUP_ID_", baseTableName: "ACT_ID_MEMBERSHIP", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_MEMB_GROUP", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_ID_GROUP", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-25") {
		addForeignKeyConstraint(baseColumnNames: "USER_ID_", baseTableName: "ACT_ID_MEMBERSHIP", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_MEMB_USER", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_ID_USER", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-26") {
		addForeignKeyConstraint(baseColumnNames: "EXECUTION_ID_", baseTableName: "ACT_RU_EVENT_SUBSCR", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_EVENT_EXEC", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-27") {
		addForeignKeyConstraint(baseColumnNames: "PARENT_ID_", baseTableName: "ACT_RU_EXECUTION", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_EXE_PARENT", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-28") {
		addForeignKeyConstraint(baseColumnNames: "PROC_INST_ID_", baseTableName: "ACT_RU_EXECUTION", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_EXE_PROCINST", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-29") {
		addForeignKeyConstraint(baseColumnNames: "SUPER_EXEC_", baseTableName: "ACT_RU_EXECUTION", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_EXE_SUPER", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-30") {
		addForeignKeyConstraint(baseColumnNames: "TASK_ID_", baseTableName: "ACT_RU_IDENTITYLINK", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_TSKASS_TASK", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_TASK", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-31") {
		addForeignKeyConstraint(baseColumnNames: "EXCEPTION_STACK_ID_", baseTableName: "ACT_RU_JOB", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_JOB_EXCEPTION", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_GE_BYTEARRAY", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-32") {
		addForeignKeyConstraint(baseColumnNames: "EXECUTION_ID_", baseTableName: "ACT_RU_TASK", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_TASK_EXE", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-33") {
		addForeignKeyConstraint(baseColumnNames: "PROC_DEF_ID_", baseTableName: "ACT_RU_TASK", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_TASK_PROCDEF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RE_PROCDEF", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-34") {
		addForeignKeyConstraint(baseColumnNames: "PROC_INST_ID_", baseTableName: "ACT_RU_TASK", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_TASK_PROCINST", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-35") {
		addForeignKeyConstraint(baseColumnNames: "BYTEARRAY_ID_", baseTableName: "ACT_RU_VARIABLE", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_VAR_BYTEARRAY", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_GE_BYTEARRAY", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-36") {
		addForeignKeyConstraint(baseColumnNames: "EXECUTION_ID_", baseTableName: "ACT_RU_VARIABLE", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_VAR_EXE", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-37") {
		addForeignKeyConstraint(baseColumnNames: "PROC_INST_ID_", baseTableName: "ACT_RU_VARIABLE", baseTableSchemaName: "NOMINAPROD", constraintName: "ACT_FK_VAR_PROCINST", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", referencedColumnNames: "ID_", referencedTableName: "ACT_RU_EXECUTION", referencedTableSchemaName: "NOMINAPROD", referencesUniqueColumn: "false")
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-38") {
		createIndex(indexName: "ACT_IDX_HI_ACT_INST_END", tableName: "ACT_HI_ACTINST", tablespace: "SYSTEM", unique: "false") {
			column(name: "END_TIME_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-39") {
		createIndex(indexName: "ACT_IDX_HI_ACT_INST_START", tableName: "ACT_HI_ACTINST", tablespace: "SYSTEM", unique: "false") {
			column(name: "START_TIME_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-40") {
		createIndex(indexName: "ACT_IDX_HI_DETAIL_ACT_INST", tableName: "ACT_HI_DETAIL", tablespace: "SYSTEM", unique: "false") {
			column(name: "ACT_INST_ID_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-41") {
		createIndex(indexName: "ACT_IDX_HI_DETAIL_NAME", tableName: "ACT_HI_DETAIL", tablespace: "SYSTEM", unique: "false") {
			column(name: "NAME_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-42") {
		createIndex(indexName: "ACT_IDX_HI_DETAIL_PROC_INST", tableName: "ACT_HI_DETAIL", tablespace: "SYSTEM", unique: "false") {
			column(name: "PROC_INST_ID_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-43") {
		createIndex(indexName: "ACT_IDX_HI_DETAIL_TIME", tableName: "ACT_HI_DETAIL", tablespace: "SYSTEM", unique: "false") {
			column(name: "TIME_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-44") {
		createIndex(indexName: "ACT_IDX_HI_PRO_INST_END", tableName: "ACT_HI_PROCINST", tablespace: "SYSTEM", unique: "false") {
			column(name: "END_TIME_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-45") {
		createIndex(indexName: "ACT_IDX_HI_PRO_I_BUSKEY", tableName: "ACT_HI_PROCINST", tablespace: "SYSTEM", unique: "false") {
			column(name: "BUSINESS_KEY_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-46") {
		createIndex(indexName: "ACT_UNIQ_HI_BUS_KEY", tableName: "ACT_HI_PROCINST", tablespace: "SYSTEM", unique: "true") {
			column(name: "SYS_NC00013$")

			column(name: "SYS_NC00014$")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-47") {
		createIndex(indexName: "ACT_IDX_EVENT_SUBSCR_CONFIG_", tableName: "ACT_RU_EVENT_SUBSCR", tablespace: "SYSTEM", unique: "false") {
			column(name: "CONFIGURATION_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-48") {
		createIndex(indexName: "ACT_IDX_EXEC_BUSKEY", tableName: "ACT_RU_EXECUTION", tablespace: "SYSTEM", unique: "false") {
			column(name: "BUSINESS_KEY_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-49") {
		createIndex(indexName: "ACT_UNIQ_RU_BUS_KEY", tableName: "ACT_RU_EXECUTION", tablespace: "SYSTEM", unique: "true") {
			column(name: "SYS_NC00014$")

			column(name: "SYS_NC00015$")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-50") {
		createIndex(indexName: "ACT_IDX_IDENT_LNK_GROUP", tableName: "ACT_RU_IDENTITYLINK", tablespace: "SYSTEM", unique: "false") {
			column(name: "GROUP_ID_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-51") {
		createIndex(indexName: "ACT_IDX_IDENT_LNK_USER", tableName: "ACT_RU_IDENTITYLINK", tablespace: "SYSTEM", unique: "false") {
			column(name: "USER_ID_")
		}
	}

	changeSet(author: "miguel (generated)", id: "1360341948340-52") {
		createIndex(indexName: "ACT_IDX_TASK_CREATE", tableName: "ACT_RU_TASK", tablespace: "SYSTEM", unique: "false") {
			column(name: "CREATE_TIME_")
		}
	}
}
