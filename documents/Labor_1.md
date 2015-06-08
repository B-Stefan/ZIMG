# Labor 1

## Aufgabe 1: Projektidee

Bei ZIMG handelt es sich um ein Imageboard wo Studenten der HS Bremen Bilder von ihren Auslandsaufenthalt hochladen können. Das Imageboard wird angereicht über Kommentare, Schlagwörter und Favoriten. Einzelnen Bilder können durch Upvotes in ihrer Beliebtheit gesteigert werden.

[Die fünf Requirements können in der Datei Requirements.md gefunden werden.](Requirements.md)

## Aufgabe 2: Vorstellung

Die Projektidee wurde abgenommen.

## Aufgabe 3: Installieren von MySQL-Tools

Als MySQL Editor verwenden wir den freien Editor [Sequel Pro](http://www.sequelpro.com/).

## Aufgabe 4: Konfigurieren einer MySQL-Datenbank

Als lokale Datenbank-Instanz kommt eine Vagrant System zum Einsatz.

1. Install Vagrant and VirtualBox
2. execute 'vagrant destroy && vagrant up'
3. set up mysql connection (localhost:3306; user: dbUser; password: dbPass; db:zimg)

## Aufgabe 5: Erstellen eines ER-Diagramms zur Projektidee

![ZIMG ER-Diagramm](./ZIMG_ER-Diagram.pdf)

## Aufgabe 6: Verbinden zu Datenbank auf Hochschul-Server

```
fabian:ZIMG/ $ mysql --host=195.37.176.178 --user=dbweb_user_12 --password=woaXUKYsWP --port=11336  dbwebanw_sose15_12
```