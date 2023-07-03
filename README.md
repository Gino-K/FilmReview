# Projekt FilmReview

Die Webapplikation FilmReview ermöglicht es Benutzern, Filme zu bewerten. Die Anwendung ist rollenbasiert und bietet verschiedene Rollen: Admin und Gast.

## Funktionen

### Admin-Rolle

Als Administrator besitzt man folgende Berechtigungen:

- Filme hinzufügen(Bild soll .png sein), anzeigen, bearbeiten und löschen (CRUD-Operationen)
- Bewertungen abrufen und löschen, es ist möglich im User Bereich auch bewertungen zu geben und auch zu bearbeiten (CRUD-Operationen)
- Weitere Administratoren hinzufügen
- In die Benutzeransicht wechseln

### Gast-Rolle

Als Gast besitzt man folgende Berechtigungen:

- Filme aufrufen und bewerten
- Eigene Bewertungen anzeigen, bearbeiten und löschen (CRUD-Operationen)


### Allgemeine Funktionen

Unabhängig von den Rollen haben Benutzer Zugriff auf folgende Funktionen:

- Registrierungsfunktion, um sich als neuer Benutzer zu registrieren
- Zugriff auf die Landingpage ("/")
- Zugriff auf die Login-Seite ("/login")
- Zugriff auf die Registrierungsseite ("/registrieren")

## Verwendung

Um das Projekt FilmReview auf Ihrem lokalen Entwicklungsserver auszuführen, befolgen Sie bitte die folgenden Schritte:

1. Klone das Repository:

git clone https://github.com/Gino-K/filmreview.git

2. Navigiere zum Projektverzeichnis:

cd filmreview

3. Öffne die Datei application.properties im Verzeichnis src/main/resources.

4. Trage die erforderlichen Datenbankinformationen ein:

spring.datasource.url=jdbc:mysql://localhost:3306/deine-datenbank  
spring.datasource.username=dein-username  
spring.datasource.password=dein-passwort  
Ersetze deine-datenbank, dein-username und dein-passwort durch die entsprechenden Werte für deine MySQL-Datenbank.

5. Starte die Anwendung:

./mvnw spring-boot:run

6. Öffne deinen Webbrowser und besuche die Adresse http://localhost:8080.
   
7. Verwende den Standard-Admin-Benutzernamen und das Standard-Passwort, um dich als Administrator anzumelden:

Benutzername: admin
Passwort: admin

8. Fertig vorbereitet um loszulegen.

## Datenbank-Diagramm
![Datenbank-Diagramm](https://i.imgur.com/2rJSPrD.png)

## Technologien

Das Projekt FilmReview verwendet folgende Technologien:

- Java Spring Boot - Webframework für die Entwicklung von Java-Anwendungen
- Spring Web - Modul für die Erstellung von Webanwendungen mit Spring
- Spring Security - Framework für die Authentifizierung und Autorisierung in Spring-Anwendungen
- Spring Data - Framework zur Vereinfachung des Datenbankzugriffs in Spring-Anwendungen
- Lombok - Java-Bibliothek zur Vereinfachung der Codeerstellung
- HTML - Markup-Sprache für die Strukturierung von Webseiten
- Bootstrap - CSS-Framework für die Gestaltung von Webseiten
- CSS - Stylesheet-Sprache für das Aussehen von Webseiten
- MySQL - Relationale Datenbank für die Speicherung von Daten

## Autor
- [Gino Kettler](https://github.com/Gino-K) - Entwickler
