CREATE DATABASE `canaldb`;

USE canaldb;
CREATE TABLE `user`
(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);