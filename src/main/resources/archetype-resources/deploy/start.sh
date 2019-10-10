#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#!/usr/bin/env bash
echo 'START - Setting exe rights to ${artifactId}.jar'
sudo mv /home/ubuntu/${artifactId}/target/${artifactId}.jar /home/ubuntu/${artifactId}/${artifactId}.jar
sudo rm -rf /home/ubuntu/${artifactId}/target/
sudo chmod 777 /home/ubuntu/${artifactId}/${artifactId}.jar
echo 'START - Running application in background '
sudo nohup java -jar /home/ubuntu/${artifactId}/${artifactId}.jar > /dev/null 2>&1 &