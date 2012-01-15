#!/bin/bash
export PGPASSWORD="password"
export DBWORK_DIR="./dbwork"
export CUR_DATE=`date "+%Y%m%d-%H:%M:%S"`
export TARGET_DB="$1"

if [ -f $DBWORK_DIR/$TARGET_DB.bak ] ; then
  mv $DBWORK_DIR/$TARGET_DB.bak $DBWORK_DIR/x$TARGET_DB.bak.$CUR_DATE
fi

pg_dump -U postgres -f $DBWORK_DIR/$TARGET_DB.bak $TARGET_DB

psql -U postgres $TARGET_DB < db_disconnect_users.sql
dropdb -U postgres $TARGET_DB
createdb -U postgres $TARGET_DB

echo "$TARGET_DB has been backed up, dropped, and re-created empty."
