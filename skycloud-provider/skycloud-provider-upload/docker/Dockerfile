FROM java:8

MAINTAINER sky

ENV BOOT_ARGS=""

ADD skycloud-upload-1.0.0-SNAPSHOT.jar /home/ms-upload.jar

WORKDIR /home

CMD java ${BOOT_ARGS} -jar ms-upload.jar

EXPOSE  8103