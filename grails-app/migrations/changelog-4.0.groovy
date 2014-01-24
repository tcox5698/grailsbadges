databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1326689231513-1") {
		dropColumn(columnName: "favorite_color", tableName: "person")
	}
}
