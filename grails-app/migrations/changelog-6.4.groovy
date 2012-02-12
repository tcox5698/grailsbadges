databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1329070098755-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "password", tableName: "person")
	}
}
