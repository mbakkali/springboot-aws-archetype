#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#!/usr/bin/env bash
cd /home/ubuntu
echo "BEFORE-INSTALL - Deleting ${artifactId} repository ..."
sudo rm -rf ${artifactId}
echo "BEFORE-INSTALL - Deleted ${artifactId} repository with success"
echo "BEFORE-INSTALL - Deleting codedeploy *-cleanup file ..."
sudo rm -rf /opt/codedeploy-agent/deployment-root/deployment-instructions/*-cleanup
echo "BEFORE-INSTALL - Deleted codedeploy *-cleanup file with success"