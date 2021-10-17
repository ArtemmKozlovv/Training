USE `travel_agency_db`;

CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `surname` VARCHAR(200) NOT NULL,
    `login` VARCHAR(200) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `role` INTEGER NOT NULL,
    `count_of_tours` INTEGER NOT NULL,
    `id_tour` INTEGER,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `tour` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `country` VARCHAR(200) NOT NULL,
    `cost` BIGINT NOT NULL,
    `aboutTour` VARCHAR(1500) NOT NULL,
    `date` DATE NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE `request` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(200) NOT NULL,
    `phoneNumber` VARCHAR(200) NOT NULL,
    `name` VARCHAR(200) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

INSERT INTO `user` (`login`, `password`, `name`, `surname`, `role`, `count_of_tours`) VALUES ("admin","admin","admin","admin", 0, 0);

INSERT INTO `user` (`login`, `password`, `name`, `surname`, `role`, `count_of_tours`) VALUES ("agent","agent","agent","agent", 1, 0);

INSERT INTO `user` (`login`, `password`, `name`, `surname`, `role`, `count_of_tours`) VALUES ("user","user","user","user", 2, 2);

INSERT INTO `tour` (`name`, `country`, `cost`, `aboutTour`, `date`) VALUES ("Dive Inn Swiss Resort","Egipt","210000","qwer tyuio plkjh gfd sa","2022/10/23");