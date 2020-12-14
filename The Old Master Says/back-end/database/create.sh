#!/bin/bash
BASEDIR=$(dirname $0)
DATABASE=translations
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
cat "$BASEDIR/schema.sql" "$BASEDIR/data.sql" | psql -U postgres -d $DATABASE -f -