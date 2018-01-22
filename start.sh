#!/bin/bash
cp "./kaddy-daemon/build/libs/kaddy-daemon.jar" "./kaddy-daemon.jar"
exec "java" "-jar" "./kaddy-daemon.jar" $@