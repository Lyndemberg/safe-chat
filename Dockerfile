FROM payara/server-full
COPY target/safe-chat-1.0.war $DEPLOY_DIR
