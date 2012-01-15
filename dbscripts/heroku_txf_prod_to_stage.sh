#!/bin/bash

heroku pgbackups:capture --app meritprod
heroku pgbackups:restore DATABASE `heroku pgbackups:url --app meritprod` --app meritstage