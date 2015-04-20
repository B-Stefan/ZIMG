#!/usr/bin/env bash
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password rootpass'
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password rootpass'
sudo apt-get update
sudo apt-get -y install mysql-server-5.5 php5-mysql apache2 php5

if [ ! -f /var/log/databasesetup ];
then
    echo "CREATE USER 'root'@'localhost' IDENTIFIED BY 'rootpass'" | mysql -uroot -prootpass
    echo "CREATE DATABASE rootDB" | mysql -uroot -prootpass
    echo "GRANT ALL ON *.* TO 'root'@'192.168.33.20'" | mysql -uroot -prootpass
    echo "flush privileges" | mysql -uroot -prootpass

    touch /var/log/databasesetup

    if [ -f /vagrant/data/initial.sql ];
    then
        mysql -uroot -prootpass wordpress < /vagrant/data/initial.sql
    fi
fi

if [ ! -h /var/www ];
then

    rm -rf /var/www sudo
    ln -s /vagrant/public /var/www

    a2enmod rewrite

    sed -i '/AllowOverride None/c AllowOverride All' /etc/apache2/sites-available/default

    service apache2 restart
fi
