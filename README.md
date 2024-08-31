## Beschreibung

Fundbox24 ist als Projekt einer Studienarbeit aus dem Marketing entstanden und wurde im Rahmen des Studienmoduls "Agile Test- und Entwicklungsmethoden" als Gruppenarbeit teilumgesetzt. Ziel war die Entwicklung einer deutschlandweit einheitlichen Fundbüro-App, in der Nutzer gefundene Gegenstände einstellen oder Verlustanzeigen aufgeben können. 

Das Backend wurde mit Spring Boot entwickelt und nutzt eine MySQL-Datenbank, um Informationen wie Benutzerdaten und Daten zu Fundsachen und -orten zu speichern.

## Starten des Backend

Beide Dienste befinden sich jeweils in einem isolierten Docker-Container.

Falls Sie Docker nicht installiert haben, besuchen Sie 
die [offizielle Internetseite von Docker](https://docs.docker.com/desktop/install/mac-install/) 
und folgen Sie den Anweisungen.

Führen Sie folgenden Befehl aus, um das Backend zu starten:

```shell
docker compose up
```

Es kann etwas dauern, bis das Backend gestartet ist, weil das Docker Image für MySQL und alle 
Gradle-Abhängigkeiten für das Backend heruntergeladen werden müssen.

Wechseln Sie anschließend zu unserer App und melden Sie sich an. 😉
