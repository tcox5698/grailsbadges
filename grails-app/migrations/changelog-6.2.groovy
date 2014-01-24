databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1327209288198-1") {
		createTable(tableName: "sec_role") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sec_rolePK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-2") {
		createTable(tableName: "sec_user_sec_role") {
			column(name: "sec_role_id", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "sec_user_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-3") {
		addColumn(tableName: "person") {
			column(name: "account_expired", type: "bool", defaultValueBoolean:"false") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-4") {
		addColumn(tableName: "person") {
			column(name: "account_locked", type: "bool",defaultValueBoolean:"false") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-5") {
		addColumn(tableName: "person") {
			column(name: "class", type: "varchar(255)",defaultValue:"com.davai.merit.Person") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-6") {
		addColumn(tableName: "person") {
			column(name: "enabled", type: "bool",defaultValueBoolean:"true") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-7") {
		addColumn(tableName: "person") {
			column(name: "password_expired", type: "bool",defaultValueBoolean:"false") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-15") {
		dropIndex(indexName: "email_address_unique_1326688765901", tableName: "person")
	}

	changeSet(author: "Tom (generated)", id: "1327179102936-8") {
		renameColumn(tableName: "person", oldColumnName:"email_address", newColumnName:"username")
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-9") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "name", tableName: "person")
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-10") {
		addPrimaryKey(columnNames: "sec_role_id, sec_user_id", constraintName: "sec_user_sec_PK", tableName: "sec_user_sec_role")
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-11") {
		createIndex(indexName: "username_unique_1327209288080", tableName: "person", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-12") {
		createIndex(indexName: "authority_unique_1327209288081", tableName: "sec_role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-13") {
		addForeignKeyConstraint(baseColumnNames: "sec_role_id", baseTableName: "sec_user_sec_role", constraintName: "FK6630E2A434852C0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sec_role", referencesUniqueColumn: "false")
	}

	changeSet(author: "Tom (generated)", id: "1327209288198-14") {
		addForeignKeyConstraint(baseColumnNames: "sec_user_id", baseTableName: "sec_user_sec_role", constraintName: "FK6630E2AE87316A0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
	}
}
