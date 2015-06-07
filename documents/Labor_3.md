# Labor 3


## Aufgabe 1: Fremdschlüssel-Optimierung

Aufgrund einer einheitlichen Struktur und unter dem Gesichtspunkt der Erweiterbarkeit 
wurde für allen Entitäten ein eindeutiges Attribut *id* hinzugefügt. Dieser ist jeweils mit der eigenscheift *auto increment* und *not null* versehen womit sichergestellt wird, dass dieses Attribut auch für jeden Eintrag eindeutig ist. 

Alternativ hätte man z.B. für *favorite* die Kombination aus den Attribute *userId* und *imageId* verwenden können. 

Für die meisten Fremdschlüssel wurde die Bedingung *not null* hinzugefügt, siehe hierzu die Datei ZIMG_ALTER_TABLE.sql 
 
## Aufgabe 2: Redundanzen und Aufzählungstypen

Aufgrund der bisherigen Struktur mussten hierbei keine Änderungen am bestehenden Relation-Diagramm gemacht werden. 


## Aufgabe 3: Normalisierung
Wir konnten keine voll funktionalen oder transitive Abhängigkeiten in unserem relations Diagramm finden, da wir keine Abhängigkeiten unter den Attributen ausmachen konnten.  