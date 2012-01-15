#!/bin/bash
export PGPASSWORD="password"
export DBWORK_DIR="./dbwork"
export CUR_DATE=`date "+%Y%m%d-%H:%M:%S"`
export TARGET_DB="$1"

if [ ! -f $DBWORK_DIR/mbstage.bak ] ; then
  echo "no backup file exists"
  exit 0
fi

psql -U postgres $TARGET_DB < db_disconnect_users.sql
dropdb -U postgres $TARGET_DB
createdb -U postgres $TARGET_DB
psql -U postgres $TARGET_DB < $DBWORK_DIR/mbstage.bak

echo "$TARGET_DB has been restored from mbstage.bak"
