databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1326688765992-1") {
		createIndex(indexName: "email_address_unique_1326688765901", tableName: "person", unique: "true") {
			column(name: "email_address")
		}
	}

	changeSet(author: "Tom (generated)", id: "1326688765992-2") {
		createIndex(indexName: "name_unique_1326688765902", tableName: "person", unique: "true") {
			column(name: "name")
		}
	}
}
