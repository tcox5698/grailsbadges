databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1326861573845-1") {
		createTable(tableName: "category") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categoryPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1326861573845-2") {
		createIndex(indexName: "name_unique_1326861573549", tableName: "category", unique: "true") {
			column(name: "name")
		}
	}
}
