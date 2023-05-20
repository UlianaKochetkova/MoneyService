#FROM grafana/grafana-oss:9.5-ubuntu
FROM grafana/grafana
#ARG GF_UID="472"
#ARG GF_GID="472"
USER root
ADD ./provisioning /etc/grafana/provisioning
#RUN usermod -aG docker 472
#RUN adduser 472 docker
RUN apk add --no-cache shadow && \
    addgroup --gid 472 grafana && \
    gpasswd -d grafana root && \
    usermod -g grafana grafana && \
    apk del shadow

RUN chown -R root:root /etc/grafana && \
chmod -R a+r /etc/grafana && \
chown -R grafana:grafana /var/lib/grafana && \
chown -R grafana:grafana /usr/share/grafana

USER grafana