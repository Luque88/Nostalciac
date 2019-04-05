FROM airhacks/glassfish
COPY ./target/nostalciac2.0.war ${DEPLOYMENT_DIR}
