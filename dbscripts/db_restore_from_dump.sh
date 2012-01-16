#!/bin/bash
export PGPASSWORD="password"
export DBWORK_DIR="./dbwork"
export CUR_DATE=`date "+%Y%m%d-%H:%M:%S"`
export TARGET_DB="$1"
export DUMP_FILE="$2"

if [ -f $DBWORK_DIR/$TARGET_DB.bak ] ; then
  mv $DBWORK_DIR/$TARGET_DB.bak $DBWORK_DIR/x$TARGET_DB.bak.$CUR_DATE
fi

if [ ! -f $DBWORK_DIR/$DUMP_FILE ] ; then
  echo "dump file $DBWORK_DIR/$DUMP_FILE does not exist.  Exiting"
  exit
fi

pg_dump -U postgres -f $DBWORK_DIR/$TARGET_DB.bak $TARGET_DB

echo "$TARGET_DB has been backed up"

./db_drop_create.sh $TARGET_DB

pg_restore --verbose --clean --no-acl --no-owner -h localhost -U postgres -d mbgrails_stage dbwork/$DUMP_FILE

echo "$TARGET_DB has been restored from dump $DUMP_FILE"