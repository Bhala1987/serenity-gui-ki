##FROM maven:3.8.3-openjdk-17
#FROM openjdk:17-jdk-slim
#COPY pom.xml /test/
#COPY src /test/src/
#WORKDIR /test
#
##================================================
## Customize sources for apt-get
##================================================
#RUN  echo "deb http://archive.ubuntu.com/ubuntu vivid main universe\n" > /etc/apt/sources.list \
#  && echo "deb http://archive.ubuntu.com/ubuntu vivid-updates main universe\n" >> /etc/apt/sources.list
#
#RUN apt-get update -qqy \
#  && apt-get -qqy --no-install-recommends install software-properties-common \
#  && add-apt-repository -y ppa:git-core/ppa \
#
##===============
## Google Chrome
##===============
#RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
#  && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
#  && apt-get update -qqy \
#  && apt-get -qqy install \
#    google-chrome-stable \
#  && rm /etc/apt/sources.list.d/google-chrome.list \
#  && rm -rf /var/lib/apt/lists/*
#
##==================
## Chrome webdriver
##==================
#ENV CHROME_DRIVER_VERSION 2.20
#RUN wget --no-verbose -O /tmp/chromedriver_linux64.zip http://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
#  && rm -rf /opt/selenium/chromedriver \
#  && unzip /tmp/chromedriver_linux64.zip -d /opt/selenium \
#  && rm /tmp/chromedriver_linux64.zip \
#  && mv /opt/selenium/chromedriver /opt/selenium/chromedriver-$CHROME_DRIVER_VERSION \
#  && chmod 755 /opt/selenium/chromedriver-$CHROME_DRIVER_VERSION \
#  && ln -fs /opt/selenium/chromedriver-$CHROME_DRIVER_VERSION /usr/bin/chromedriver
#
#
#
#RUN mkdir /tmp/.X11-unix
#RUN chmod -R go-rwx /tmp/* /tmp/.[!.]*
#RUN chmod 1777 /tmp/.X11-unix
#RUN chmod 1777 /tmp
#
#
#
#RUN mvn -f /test/pom.xml clean verify
#
#WORKDIR /test



FROM openjdk:17-jdk-slim
COPY pom.xml /app/
COPY src /app/src/
WORKDIR /app
RUN apt-get update
RUN apt-get install -y maven
RUN apt-get install -y wget
RUN apt-get install -y gnupg
RUN apt-get install -y gnupg1
RUN apt-get install -y gnupg2
RUN apt-get install -y unzip
RUN apt-get install -y curl

#===============
# Google Chrome
#===============
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
  && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
  && apt-get update -qqy \
  && apt-get -qqy install \
    google-chrome-stable \
  && rm /etc/apt/sources.list.d/google-chrome.list \
  && rm -rf /var/lib/apt/lists/*

#==================
# Chrome webdriver
#==================
ENV CHROME_DRIVER_VERSION=114.0.5735.90
RUN echo $CHROME_DRIVER_VERSION
RUN wget --no-verbose -O /tmp/chromedriver_linux64.zip http://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
  && rm -rf /opt/selenium/chromedriver \
  && unzip /tmp/chromedriver_linux64.zip -d /opt/selenium \
  && rm /tmp/chromedriver_linux64.zip \
  && mv /opt/selenium/chromedriver /opt/selenium/chromedriver-$CHROME_DRIVER_VERSION \
  && chmod 755 /opt/selenium/chromedriver-$CHROME_DRIVER_VERSION \
  && ln -fs /opt/selenium/chromedriver-$CHROME_DRIVER_VERSION /usr/bin/chromedriver

RUN mvn clean package
EXPOSE 8080
CMD ["mvn", "clean", "verify"]

