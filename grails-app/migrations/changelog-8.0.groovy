databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1334890754273-1") {
		createTable(tableName: "unlocked_achievement") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "unlocked_achiPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "message_arguments", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "message_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "person_id", type: "int8") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1334890754273-2") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "unlocked_achievement", constraintName: "FK7843DBD3E0F9401D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
	}
}
