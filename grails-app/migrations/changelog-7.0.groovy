databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1332120892395-1") {
		createTable(tableName: "login_count") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "login_countPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "count_value", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "person_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}
}
