#!/bin/bash
export PGPASSWORD="password"
export DBWORK_DIR="./dbwork"
export CUR_DATE=`date "+%Y%m%d-%H:%M:%S"`

if [ -f $DBWORK_DIR/mbstage.bak ] ; then
  mv $DBWORK_DIR/mbstage.bak $DBWORK_DIR/xmbstage.bak.$CUR_DATE
fi

pg_dump -U postgres -f $DBWORK_DIR/mbstage.bak mbgrails_stage

echo "mbgrails_stage has been backed up"