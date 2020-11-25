Reverse proxy exercise

Simple reverse proxy with secuirty features:

Features:
1)	Reverse proxy relay and routing for all traffic
2)	Map to allowed back-end servers
3)	Simple static key based authentication using authorization header mimicking oauth type token
4)	Security header: XSS, XFrame, CSP, Cache-control etc.
5)	Security filtering: Javascript and SQL Injection
6)	Logging of responses and requests


Build:

mvn clean package



Configuration:

application.properties

zuul.routes.bWAPP.url=http://192.168.1.12/bWAPP

zuul.routes.bWAPP.sensitive-headers=Authorization

ribbon.eureka.enabled=false

server.port=8090


Run:
java -jar target/simpleproxyrouter-1.0-SNAPSHOT.jar


