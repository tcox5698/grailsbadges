databaseChangeLog = {

	changeSet(author: "Tom (generated)", id: "1335596302315-1") {
		sql ("insert into sec_role (id, version, authority) values (1,0,'ROLE_ADMIN')")
		sql ("insert into sec_user_sec_role (sec_role_id, sec_user_id) select 1, p.id from person p where p.username = 'tcox56_98@yahoo.com'")
	}
}

	