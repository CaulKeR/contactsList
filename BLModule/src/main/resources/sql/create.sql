create database `contacts_list`
    character set 'utf8mb4'
    collate 'utf8mb4_general_ci';

create table `contacts_list`.`address`
(
    `id`        integer(10) unsigned not null auto_increment unique,
    `country`   varchar(60),
    `locality`  varchar(150),
    `street`    varchar(100),
    `house`     varchar(10),
    `apartment` integer(6),
    `postcode`  varchar(25),
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table `contacts_list`.`contact`
(
    `id`                integer(10) unsigned not null auto_increment unique,
    `first_name`        varchar(30)          not null,
    `surname`           varchar(30)          not null,
    `patronymic`        varchar(30),
    `birth_date`        date                 not null,
    `sex`               enum ('', 'male', 'female'),
    `nationality`       varchar(30),
    `family_status`     enum ('', 'single', 'divorced', 'married'),
    `website`           varchar(512),
    `email`             varchar(50),
    `current_workplace` varchar(100),
    `address_id`        integer(10) unsigned not null,
    `deleteDate`        date DEFAULT NULL,
    primary key (`id`),
    key `address_fk_idx` (`address_id`),
    constraint `address_fk` foreign key (`address_id`) references `address` (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table `contacts_list`.`attachment`
(
    `id`         integer(12) unsigned not null auto_increment unique,
    `file_name`  varchar(259)         not null,
    `userId`     integer(10) unsigned not null,
    `deleteDate` date         default null,
    `comment`    varchar(300) default '',
    primary key (`id`),
    key `contacts_fk_idx` (`userId`),
    constraint `contact_fk` foreign key (`userId`) references `contact` (`id`)
) engine = InnoDB
  default charset = utf8mb4;

create table `contacts_list`.`phone`
(
    `id`             integer(10) unsigned not null auto_increment unique,
    `contact_id`     integer(10) unsigned not null,
    `country_code`   integer(4)   default null,
    `operators_code` integer(11)  default null,
    `phone_number`   integer(10)          not null,
    `phone_type`     enum ('','home','mobile'),
    `comment`        varchar(300) default '',
    `deleteDate`     date         default null,
    primary key (`id`),
    key `contact_id` (`contact_id`),
    constraint `phone_ibfk_1` foreign key (`contact_id`) references `contact` (`id`)
) engine = InnoDB
  default charset = utf8mb4;