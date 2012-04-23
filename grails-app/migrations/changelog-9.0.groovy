databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1335586302315-1") {
		addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "login_count", constraintName: "FK61E8B0B9E0F9401D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", referencesUniqueColumn: "false")
	}
}
