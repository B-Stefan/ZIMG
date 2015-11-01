# ZIMG
An image application for HS-Bremen

##Development

### Set up Vagrant 

1. Install Vagrant and VirtualBox
2. execute 'vagrant destroy && vagrant up'
3. set up mysql connection (localhost:3306; user: dbUser; password: dbPass; db:zimg)

http://www.sivalabs.in/2014/03/springmvc4-spring-data-jpa.html

### Set up gradle 
1. Download gradle wrapper 
2. Use the 'jettyRun' task to start the server 

##Screenshots

![Image](./graphics/zimg-login.png?raw=true)
![Image](./graphics/zimg-top-10.gif?raw=true)
![Image](./graphics/zimg-image-detail.png?raw=true)
![Image](./graphics/zimg-top-10-tags.png?raw=true)

##Structure
The following diagrams show you the structure of the application. 

###Models-Class-Diagramm 

![Klassendiagramm Client](./documents/Documentation/Footage/ZIMG_ClassDiagram_Models.svg "Class diagram models ")

###Services-Class-Diagramm 

![Klassendiagramm Client](http://b-stefan.github.io/ZIMG/master/documents/Documentation/Footage/ZIMG_ClassDiagram_Servives.svg "Class diagram services ")
