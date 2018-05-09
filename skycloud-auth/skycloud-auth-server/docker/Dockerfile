FROM java:8

MAINTAINER sky

ENV BOOT_ARGS=""

ADD skycloud-auth-server-1.0.0-SNAPSHOT.jar /home/ms-auth-server.jar

WORKDIR /home

CMD java ${BOOT_ARGS} -jar ms-auth-server.jar

EXPOSE  8101