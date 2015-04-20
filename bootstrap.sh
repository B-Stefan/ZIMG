#!/usr/bin/env bash
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password rootpass'
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password rootpass'
sudo apt-get update
sudo apt-get -y install mysql-server-5.5

if [ ! -f /var/log/databasesetup ];
then

    echo "CREATE USER 'dbUser'@'localhost' IDENTIFIED BY 'dbPass'" | mysql -uroot -prootpass
    echo "CREATE DATABASE zimg" | mysql -uroot -prootpass
    echo "GRANT ALL ON *.* TO 'root'@'localhost'" | mysql -uroot -prootpass
    echo "flush privileges" | mysql -uroot -prootpass

    touch /var/log/databasesetup

    if [ -f /vagrant/data/initial.sql ];
    then
        mysql -uroot -prootpass wordpress < /vagrant/data/initial.sql
    fi
fi
