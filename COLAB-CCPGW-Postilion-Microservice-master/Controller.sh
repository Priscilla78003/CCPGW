export JAVA_HOME=/usr/local/java/jdk1.8.0_211

export PATH=$PATH:$JAVA_HOME/bin

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/lib

java -cp .:'lib/*':ccpgw-communication-server-1.0.0.jar com.truteq.ccpgw.communication.server.main.runController

