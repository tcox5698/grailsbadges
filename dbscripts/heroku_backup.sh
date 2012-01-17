#!/bin/bash
export DBWORK_DIR="./dbwork"
export CUR_DATE=`date "+%Y%m%d-%H:%M:%S"`
export TARGET_DB="$1"

if [ -f $DBWORK_DIR/$TARGET_DB.dump ] ; then
  mv $DBWORK_DIR/$TARGET_DB.dump $DBWORK_DIR/x$TARGET_DB.dump.$CUR_DATE
fi

heroku pgbackups:capture --app $TARGET_DB
curl -o $DBWORK_DIR/$TARGET_DB.dump `heroku pgbackups:url --app $TARGET_DB`

echo "$TARGET_DB heroku db dumped to $DBWORK_DIR/$TARGET_DB.dump"
