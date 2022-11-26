#!/usr/bin/env sh
pandoc --defaults="config/release.yml" $1 --output $2
