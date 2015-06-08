#!/usr/bin/env bash
if [ ! -f /var/log/databasesetup ];
then
    sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password rootpass'
    sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password rootpass'
    sudo apt-get update
    sudo apt-get -y install mysql-server-5.5

    echo "Updating mysql configs in /etc/mysql/my.cnf."
	sudo sed -i "s/bind-address.*/bind-address = 0.0.0.0/" /etc/mysql/my.cnf
	echo "Updated mysql bind address in /etc/mysql/my.cnf to 0.0.0.0 to allow external connections."
	sudo service mysql stop
	sudo service mysql start

    echo "Create a user (dbUser) with the password (dbPass)"

    echo "CREATE USER 'dbUser'@'%' IDENTIFIED BY 'dbPass'" | mysql -uroot -prootpass
    echo "CREATE DATABASE zimg" | mysql -uroot -prootpass
    echo "GRANT ALL ON zimg.* TO 'dbUser'@'%'" | mysql -uroot -prootpass
    echo "GRANT ALL ON *.* TO 'dbUser'@'%'" | mysql -uroot -prootpass
    echo "flush privileges" | mysql -uroot -prootpass

    touch /var/log/databasesetup

    #Create tables
    echo "Create the database zimg from file /vagrant/documents/ZIMG_CREATE_TABLE.sql"
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_CREATE_TABLE.sql

    #Insert default data
    echo "Init the database with data from file /vagrant/documents/ZIMG_INSERT_DATA.sql"
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_INSERT_DATA.sql

    # Create views
    echo "Create the views from file /vagrant/documents/ZIMG_CREATE_VIEWS.sql"
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_CREATE_VIEWS.sql
else
    echo "Recreate the Database zimg from file /vagrant/documents/ZIMG_CREATE_TABLE.sql"
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_CREATE_TABLE.sql

    #Insert default data
    echo "Init the Database zimg from file /vagrant/documents/ZIMG_INSERT_DATA.sql"
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_INSERT_DATA.sql

    # Create views
    echo "Create the views from file /vagrant/documents/ZIMG_CREATE_VIEWS.sql"
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_CREATE_VIEWS.sql
fi

