#!/bin/bash
exec "java" "-cp" "./runtime/*:./libs/*" "kaddy.KaddyBot" "-d" $1