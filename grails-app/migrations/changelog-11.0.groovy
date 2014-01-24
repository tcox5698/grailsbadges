databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1338089992700-1") {
		createTable(tableName: "unlocked_achievement_category") {
			column(name: "unlocked_achievement_categories_id", type: "int8")

			column(name: "category_id", type: "int8")
		}
	}

	changeSet(author: "Tom (generated)", id: "1338089992700-2") {
		addColumn(tableName: "unlocked_achievement") {
			column(name: "description", type: "varchar(255)")
		}
	}

	changeSet(author: "Tom (generated)", id: "1338089992700-3") {
		addColumn(tableName: "unlocked_achievement") {
			column(name: "name", type: "varchar(255)")
		}
	}

	changeSet(author: "Tom (generated)", id: "1338089992700-4") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "message_arguments", tableName: "unlocked_achievement")
	}

	changeSet(author: "Tom (generated)", id: "1338089992700-5") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "message_key", tableName: "unlocked_achievement")
	}

	changeSet(author: "Tom (generated)", id: "1338089992700-7") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "unlocked_achievement_category", constraintName: "FK57D2AFEAE54DDEBD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category", referencesUniqueColumn: "false")
	}

	changeSet(author: "Tom (generated)", id: "1338089992700-8") {
		addForeignKeyConstraint(baseColumnNames: "unlocked_achievement_categories_id", baseTableName: "unlocked_achievement_category", constraintName: "FK57D2AFEA9BF9F95B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "unlocked_achievement", referencesUniqueColumn: "false")
	}
}
