databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1327865403688-1") {
		createTable(tableName: "registration_code") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_PK")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1327865403688-2") {
		addColumn(tableName: "person") {
			column(name: "email", type: "varchar(255)")
		}
		sql ("update person set email = username")
		sql ("update person set email = 'tcox56_98@yahoo.com' where email = 'prod@prod.com'")
	}
	
}
