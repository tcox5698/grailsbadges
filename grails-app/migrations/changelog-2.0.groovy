databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1326652397476-1") {
		addColumn(tableName: "person") {
			column(name: "favorite_color", type: "varchar(255)")
		}
	}
}
