#!/bin/bash

heroku pgbackups:capture --app $1
