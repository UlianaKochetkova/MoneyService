#FROM grafana/grafana-oss:9.5-ubuntu
FROM grafana/grafana
USER root
ADD ./provisioning /etc/grafana/provisioning
#RUN usermod -aG docker 472
RUN adduser 472 docker