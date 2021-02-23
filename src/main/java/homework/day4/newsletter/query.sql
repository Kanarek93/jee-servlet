create schema Newsletter;

CREATE TABLE `formUsers` (
                           `id` int(60) NOT NULL AUTO_INCREMENT KEY,
                           `name` varchar(255) NOT NULL ,
                           `email` varchar(255) NOT NULL UNIQUE
);