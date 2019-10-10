#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#!/usr/bin/env bash
while [ true ]
do
    if [ "$(curl -s -k  http://localhost:8080/${artifactId}/actuator/health)" = '{"status":"UP"}' ]
    then
        echo "VALIDATE - Server is up and running ! "
        exit 0
    else
        echo "VALIDATE - Checking http://localhost:${applicationPort}/${artifactId}/actuator/health endpoint"
        sleep 3s
    fi
done