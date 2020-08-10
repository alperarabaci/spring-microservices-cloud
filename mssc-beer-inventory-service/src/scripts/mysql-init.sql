create database if not exists beerinventoryservice character set utf8mb4 collate utf8mb4_unicode_ci;

create user if not exists 'beer_inventory_service'@'%' identified with mysql_native_password by 'password';

GRANT ALL PRIVILEGES on beerinventoryservice.* to 'beer_inventory_service'@'%';

flush PRIVILEGES;