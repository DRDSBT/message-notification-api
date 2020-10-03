# message-notification-api


Hierbei handelt es sich um eine REST-API. 

Diese nimmt Nachrichten im JSON-Format und analysiert sie anhand des mitgelieferten Typs. Je nach Typ wird die Nachricht an ein von mir zuvor erstelltes
Slack-Channel weitergeleitet. Unterschieden werden 4 verschiedene Typen: info, warning, error und undefinierte Typen. 

Start der API:

Benötigte Technologie: Docker

1. Sobald Sie das Projekt geöffnet haben, können Sie mithilfe des Terminal-Befehls: "docker-compose up" die API starten.
(Über RUN oder DEBUG müssen Sie erst eine MongoDB mit den erforderlichen Ports erstellen. Dieser Schritt wird über docker-compose übersprungen. 
Sh. docker-compose.yaml)

2. Indem Sie eine weiteres Terminalfenster öffnen ( "+" new Session), können Sie die API mithilfe der folgenden Curl-Befehle testen:

POST-Befehle:
curl --header "Content-Type: application/json" -X POST --data "{'messageContent': 'Hallo wie geht es Dir', 'type': 'junk'}" localhost:8090/api/sendMessage

curl --header "Content-Type: application/json" -X POST --data "{'messageContent': 'Das ist eine Information', 'type': 'info'}" localhost:8090/api/sendMessage

curl --header "Content-Type: application/json" -X POST --data "{'messageContent': 'Vorsicht, Warnung!', 'type': 'warning'}" localhost:8090/api/sendMessage

curl --header "Content-Type: application/json" -X POST --data "{'messageContent': '404 ERROR', 'type': 'error'}" localhost:8090/api/sendMessage


GET-Befehle:
curl -X GET localhost:8090/api/getAllMessages

curl -X GET localhost:8090/api/getMessages/junk

curl -X GET localhost:8090/api/getMessages/info

curl -X GET localhost:8090/api/getMessages/warning

curl -X GET localhost:8090/api/getMessages/error

3. Falls Sie dich Nachrichten im Slack sehen wollen, klicken sie auf den folgenden Einladungslink:
https://join.slack.com/t/test-jci2351/shared_invite/zt-glaamylz-ab5UV2VK5pCex~y4nJej9A

4. Über http://localhost:8090/swagger-ui.html können Sie das Swagger-Interface verwenden und nachvollziehen, wie die API dokumentiert ist.

Folgende Technologien habe ich in diesem Projekt verwendet:

- Spring Boot
- Docker für den lokalen Aufruf
- CURL/Postman zum manuellen Aufruf und Testen der Terminalbefehle
- JUnit/Mockito
- Swagger2
- slf4j für das Logging und Unterscheidung des Log-Levels
- MongoDB als NoSql-Datenbank
- GITHUB =)

Ich hoffe, dass es soweit verständlich war! 


Freundliche Grüße
