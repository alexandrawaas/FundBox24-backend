# FundBox24 Backend

Das Backend wurde mit Spring Boot entwickelt und nutzt eine MySQL-Datenbank, um Informationen wie Benutzerdaten und Daten zu Fundsachen und -orten zu speichern.

Beide Dienste befinden sich jeweils in einem isolierten Docker-Container.

Falls Sie Docker nicht installiert haben, besuchen Sie 
die [offizielle Internetseite von Docker](https://docs.docker.com/desktop/install/mac-install/) 
und folgen Sie den Anweisungen.

## Backend starten

Führen Sie folgenden Befehl aus, um das Backend zu starten:

```shell
docker compose up
```

Es kann etwas dauern, bis das Backend gestartet ist, weil das Docker Image für MySQL und alle 
Gradle-Abhängigkeiten für das Backend heruntergeladen werden müssen.

Wechseln Sie anschließend zu unserer App und melden Sie sich an. 😉