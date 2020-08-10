create database if not exists beerservice character set utf8mb4 collate utf8mb4_unicode_ci;

create user if not exists 'beer_service'@'%' identified with mysql_native_password by 'password';

GRANT ALL PRIVILEGES on beerservice.* to 'beer_service'@'%';

flush PRIVILEGES;