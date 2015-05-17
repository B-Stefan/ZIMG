#Constraints
Nachfolgend die Einschränkungen für ZIMG, die beim Anlegen/Update oder beim Löschen zu beachten sind: 


##Comment

###Anlegen/Update
* Wenn eine Relation Comment angelegt wird, muss das Attribut ID mit einem eindeutigen Wert für die Relation gefüllt werden.
* Wenn eine Relation Comment angelegt wird, muss das Attribut userId einen gültigen Wert enthalten. 

##User 

###Anlegen/Update
* Wenn eine Relation User angelegt wird, muss das Attribut ID mit einem eindeutigen Wert für die Relation gefüllt werden.
* Wenn eine Relation User angelegt wird, muss das Attribut admin standardmäßig auf false gesetz werden.
* Wenn eine RelationUser der angelegt wird, muss das Attribut createdAt auf die aktuellen Datum(DD.MM.YYYY HH:mm:SS) gesetzt werden. 

###Löschen 

##Image

###Anlegen/Update
* Wenn eine Relation Image angelegt wird, muss das Attribut ID mit einem eindeutigen Wert für die Relation gefüllt werden.
* Wenn eine Relation Image angelegt wird, muss eine gültige User.Id in das Attribut uploaderId eingetragen werden.
* Wenn eine Relation Image angelegt/geupdatet wird, muss das Attribut fileName auf einen Namen gesetzt werden, der im lokalen File-Systems des Servers exisitert.  

###Löschen

##Upvote

###Anlegen/Update 
* Wenn eine Relation Upvote angelegt wird, muss das Attribut ID mit einem eindeutigen Wert für die Relation gefüllt werden.
* Wenn eine Relation Upvote  angelegt wird, muss das Attribut createdAt auf die aktuellen Datum(DD.MM.YYYY HH:mm:SS) gesetzt werden. 


###Löschen

##Tag

###Anlegen/Update 
* Wenn eine Relation Tag angelegt wird, muss das Attribut ID mit einem eindeutigen Wert für die Relation gefüllt werden.
* Wenn eine Relation Tag  angelegt wird, muss das Attribut createdAt auf die aktuellen Datum(DD.MM.YYYY HH:mm:SS) gesetzt werden.

###Löschen


##Tag2Iamge 

### Anlegen/Update 
* Wenn eine Relation Tag2Image angelegt wird, muss das Attribut ID mit einem eindeutigen Wert für die Relation gefüllt werden.
* Wenn eine Relation Tag2Image angelegt/geupdatet wird, muss das Attribut tagId eine Id enthalten der einer Relation Tag.id zuzuordnen ist. 
* Wenn eine Relation Tag2Image angelegt/geupdatet wird, muss das Attribut imageId eine Id enthalten der einer Relation Image.id zuzuordnen ist.
* Wenn eine Relation Tag2Image angelegt wird, muss das Attribut createdAt auf die aktuellen Datum(DD.MM.YYYY HH:mm:SS) gesetzt werden.


###Löschen