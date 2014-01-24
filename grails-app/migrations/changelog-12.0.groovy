databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1338257483438-1") {
		createTable(tableName: "skill_level") {
			column(name: "id", type: "int8") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "skill_levelPK")
			}

			column(name: "version", type: "int8") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "multiplier", type: "int4") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}

			column(name: "rank", type: "int4") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Tom (generated)", id: "1338257483438-2") {
		addColumn(tableName: "unlocked_achievement") {
			column(name: "skill_level_id", type: "int8")
		}
	}

	changeSet(author: "Tom (generated)", id: "1338257483438-3") {
		createIndex(indexName: "name_unique_1338257483017", tableName: "skill_level", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Tom (generated)", id: "1338257483438-4") {
		addForeignKeyConstraint(baseColumnNames: "skill_level_id", baseTableName: "unlocked_achievement", constraintName: "FK7843DBD37BAB269A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "skill_level", referencesUniqueColumn: "false")
	}
	
	changeSet(author: "Tom (generated)", id: "1335596302315-1") {
		sql ("insert into skill_level (id, version, multiplier, name, rank, description) values (1,0,1,'Learner',1,'Read it, saw it, attended conference, discussed it, lurked on it')")
		sql ("insert into skill_level (id, version, multiplier, name, rank, description) values (2,0,1,'Beginner',2,'Checked out code, wrote hello world, paired with senior, logged in')")
		sql ("insert into skill_level (id, version, multiplier, name, rank, description) values (3,0,2,'Practitioner',3,'Collaborated, checked in code, tested, reported')")
		sql ("insert into skill_level (id, version, multiplier, name, rank, description) values (4,0,3,'Expert',4,'Checked in code of certified complexity')")
		sql ("insert into skill_level (id, version, multiplier, name, rank, description) values (5,0,5,'Leader',5,'Served as senior pair, led design discussion, ')")
		sql ("insert into skill_level (id, version, multiplier, name, rank, description) values (6,0,8,'Authority',6,'Published on the topic')")				
	}	
}
