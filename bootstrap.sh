#!/usr/bin/env bash
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password rootpass'
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password rootpass'
sudo apt-get update
sudo apt-get -y install mysql-server-5.5

if [ ! -f /var/log/databasesetup ];
then

    echo "CREATE USER 'dbUser'@'localhost' IDENTIFIED BY 'dbPass'" | mysql -uroot -prootpass
    echo "CREATE DATABASE zimg" | mysql -uroot -prootpass
    echo "GRANT ALL ON zimg.* TO 'dbUser'@'localhost'" | mysql -uroot -prootpass
    echo "GRANT ALL ON *.* TO 'dbUser'@'localhost'" | mysql -uroot -prootpass
    echo "flush privileges" | mysql -uroot -prootpass

    touch /var/log/databasesetup

    #Create tables
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_CREATE_TABLE.sql

    #Insert default data
    mysql -udbUser -pdbPass zimg < /vagrant/documents/ZIMG_INSERT_DATA.sql
fi

