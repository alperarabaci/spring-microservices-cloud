create database if not exists beerorderservice character set utf8mb4 collate utf8mb4_unicode_ci;

create user if not exists 'beer_order_service'@'%' identified with mysql_native_password by 'password';

GRANT ALL PRIVILEGES on beerorderservice.* to 'beer_order_service'@'%';

flush PRIVILEGES;