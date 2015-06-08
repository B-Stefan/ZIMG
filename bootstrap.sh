#!/usr/bin/env bash
if [ ! -f /var/log/databasesetup ];
then
    sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password rootpass'
    sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password rootpass'
    sudo apt-get update
    sudo apt-get -y install mysql-server-5.5

    echo "Create a user (dbUser) with the password (dbPass)"

    echo "CREATE USER 'dbUser'@'localhost' IDENTIFIED BY 'dbPass'" | mysql -uroot -prootpass
    echo "CREATE DATABASE zimg" | mysql -uroot -prootpass
    echo "GRANT ALL ON zimg.* TO 'dbUser'@'localhost'" | mysql -uroot -prootpass
    echo "GRANT ALL ON *.* TO 'dbUser'@'localhost'" | mysql -uroot -prootpass
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

