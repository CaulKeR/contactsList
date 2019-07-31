CREATE DATABASE `contacts_list`
    CHARACTER SET 'utf8mb4'
    COLLATE 'utf8mb4_general_ci';
	
CREATE TABLE `contacts_list`.`contact` (
  `id` INTEGER(10) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `first_name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `patronymic` varchar(30) NOT NULL,
  `birth_date` date NOT NULL,
  `sex` enum('male', 'female') NOT NULL,
  `nationality` varchar(30) NOT NULL,
  `family_status` enum('single', 'divorced', 'married'),
  `website` varchar(512),
  `email` varchar(50),
  `current_workplace` varchar(100),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

insert into contact(first_name, surname, patronymic, birth_date, sex, nationality, family_status, website, email) values ("Kirill", "Karpuk", "Aleksandrovich", "1999/09/22", "male", "Belarus", "single", "https://vk.com/kirillkarpuk", "kirillkarpuk0@gmail.com");
insert into contact(first_name, surname, patronymic, birth_date, sex, nationality, family_status, website, email, сurrent_workplace) values ("Denis", "Levitsky", "Nicolaevich", "1998/11/14", "male", "Ukraine", "married", "https://vk.com/id175903941", "denyalevitski@gmail.com", "ПриватБанк");
insert into contact(first_name, surname, patronymic, birth_date, sex, nationality, family_status, website, email, сurrent_workplace) values ("Nina", "Averina", "Viktorovna", "1996/12/01", "female", "Russia", "divorced", "https://vk.com/id142463263", "maroonracoon@gmail.com", "ТЦ \"Ладога\"");

CREATE TABLE `contacts_list`.`phone` (
    `id` INTEGER(10) UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `contact_id` integer(10) not null,
    `country_code` integer(4),
    `operators_code` integer(11),
    `phone_number` integer(10) NOT NULL,
    `phone_type` enum('home', 'mobile'),
    `comment` varchar(300) default '',
    `deleteDate` date default null,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`contact_id`)  REFERENCES `contacts_list`.`contact` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;