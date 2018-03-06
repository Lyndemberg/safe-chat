FROM payara/server-full
COPY target/safechat.war $DEPLOY_DIR
