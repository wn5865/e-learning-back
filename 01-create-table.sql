-- create database
DROP DATABASE IF EXISTS `udemy`;
CREATE DATABASE `udemy`;
USE `udemy`;

SET FOREIGN_KEY_CHECKS = FALSE;

DROP TABLE IF EXISTS `udemy`.`user`;
DROP TABLE IF EXISTS `udemy`.`enrollment`;
DROP TABLE IF EXISTS `udemy`.`cart`;
DROP TABLE IF EXISTS `udemy`.`wishlist`;
DROP TABLE IF EXISTS `udemy`.`course`;
DROP TABLE IF EXISTS `udemy`.`chapter`;
DROP TABLE IF EXISTS `udemy`.`lecture`;
DROP TABLE IF EXISTS `udemy`.`content`;
DROP TABLE IF EXISTS `udemy`.`resource`;
DROP TABLE IF EXISTS `udemy`.`review`;
DROP TABLE IF EXISTS `udemy`.`question`;

CREATE TABLE `udemy`.`user`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `created` TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (`id`),
    UNIQUE `uq_email` (`email`)
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`enrollment`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `user_id` BIGINT(20),
    `course_id` BIGINT(20),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_enrollment` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_course_enrollment` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`completion`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `user_id` BIGINT(20),
    `lecture_id` BIGINT(20),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_completion` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_lecture_completion` FOREIGN KEY (`lecture_id`)
        REFERENCES `lecture` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`cart`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `user_id` BIGINT(20),
    `course_id` BIGINT(20),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_cart` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON DELETE RESTRICT,
    CONSTRAINT `fk_course_cart` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE RESTRICT
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`wishlist`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `user_id` BIGINT(20),
    `course_id` BIGINT(20),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_user_wishlist` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON DELETE RESTRICT,
    CONSTRAINT `fk_course_wishlist` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE RESTRICT
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`course`
(
    `id`            BIGINT(20) AUTO_INCREMENT,
    `instructor_id` BIGINT(20),
    `category_id`   BIGINT(20),
    `level_id`      BIGINT(20),
    `image_id`      BINARY(16),

    `title` VARCHAR(100),
    `subtitle` VARCHAR(300),
    `description` TEXT,  # An HTML based detailed description of the course
    `objectives` VARCHAR(500),
    `requirement` VARCHAR(500),

    `price` INT(20) UNSIGNED,

    `created` TIMESTAMP DEFAULT NOW(),
    `last_updated` TIMESTAMP DEFAULT NOW() ON UPDATE NOW(),

    `num_students` INT(11) DEFAULT 0,
    `num_ratings` INT(11) DEFAULT 0,
    `total_rating` FLOAT DEFAULT 0,
    `total_video_duration` INT(11),
    `num_resources` INT(11),

    PRIMARY KEY(`id`),
    CONSTRAINT `fk_instructor_course` FOREIGN KEY (`instructor_id`)
        REFERENCES `user` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_category_course` FOREIGN KEY (`category_id`)
        REFERENCES `course_category` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_level_course` FOREIGN KEY (`level_id`)
        REFERENCES `level` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_image_course` FOREIGN KEY (`image_id`)
        REFERENCES `image` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`level`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `level_name` VARCHAR(50),
    PRIMARY KEY(`id`)
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`chapter`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `course_id` BIGINT(20),
    `sort_order` INT(11) NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `created` TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(`id`),
    CONSTRAINT `fk_course_chapter` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`lecture`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `chapter_id` BIGINT(20),
    `video_id` BINARY(16),
    `sort_order` INT(11) NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `created` TIMESTAMP DEFAULT NOW(),

    PRIMARY KEY(`id`),
    CONSTRAINT `fk_chapter_lecture` FOREIGN KEY (`chapter_id`)
        REFERENCES `chapter` (`id`)
        ON UPDATE CASCADE
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`video`
(
    `id` BINARY(16) DEFAULT (UUID_TO_BIN(UUID())),
    `lecture_id` BIGINT(20),
    `name` VARCHAR(100) NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    `url` VARCHAR(200),
    `data` LONGBLOB,
    `size` BIGINT(20),
    `duration` BIGINT(20),
    `created` TIMESTAMP,

    PRIMARY KEY(`id`),
    CONSTRAINT `fk_lecture_video` FOREIGN KEY (`lecture_id`)
        REFERENCES `lecture` (`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`image`
(
    `id` BINARY(16) DEFAULT (UUID_TO_BIN(UUID())),
    `name` VARCHAR(100) NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    `url`  VARCHAR(255),
    `data` MEDIUMBLOB,
    `size` BIGINT(20),
    `created` TIMESTAMP DEFAULT NOW(),

    PRIMARY KEY(`id`),
    UNIQUE (`url`)
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`resource`
(
    `id` BINARY(16) DEFAULT (UUID_TO_BIN(UUID())),
    `lecture_id` BIGINT(20),
    `url` VARCHAR(100),
    `created` TIMESTAMP DEFAULT NOW(),

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_lecture_resourse` FOREIGN KEY (`lecture_id`)
        REFERENCES `course` (`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`review`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `user_id` BIGINT(20),
    `course_id` BIGINT(20),
    `content` VARCHAR(500),
    `created` DATETIME DEFAULT NOW(),
    `last_updated` DATETIME DEFAULT NOW() ON UPDATE NOW(),
    `rating` INT(11),

    PRIMARY KEY(`id`),
    CONSTRAINT `fk_user_review` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `fk_course_review` FOREIGN KEY (`course_id`)
        REFERENCES `course` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`question`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `user_id` BIGINT(20),
    `lecture_id` BIGINT(20),
    `content` TEXT,

    PRIMARY KEY(`id`),
    CONSTRAINT `fk_user_question` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `fk_lecture_question` FOREIGN KEY (`lecture_id`)
        REFERENCES `lecture` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

CREATE TABLE `udemy`.`course_category`
(
    `id` BIGINT(20) AUTO_INCREMENT,
    `name` varchar(50),

    PRIMARY KEY(`id`),
    UNIQUE `uq_category_name` (`name`)
)
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;


SET FOREIGN_KEY_CHECKS = TRUE;
