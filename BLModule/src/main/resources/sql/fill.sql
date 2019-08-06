use `contacts_list`;
insert into address(country, locality, street, house, apartment, postcode)
values ('Беларусь', 'Minsk', 'Могилевская', '34/2', null, '220014');
insert into contact(first_name, surname, patronymic, birth_date, sex, nationality, family_status, website, email,
                    address_id)
values ("Kirill", "Karpuk", "Aleksandrovich", "1999/09/22", "male", "Belarus", "single", "https://vk.com/kirillkarpuk",
        "kirillkarpuk0@gmail.com", 1);
insert into address(country, locality, street, house, apartment, postcode)
values ('Украина', '', '', '', null, '');
insert into contact(first_name, surname, patronymic, birth_date, sex, nationality, family_status, website, email,
                    current_workplace, address_id)
values ("Denis", "Levitsky", "Nicolaevich", "1998/11/14", "male", "Ukraine", "married", "https://vk.com/id175903941",
        "denyalevitski@gmail.com", "ПриватБанк", 2);
insert into address(country, locality, street, house, apartment, postcode)
values ('Россия', 'Москва', '', '', null, '');
insert into contact(first_name, surname, patronymic, birth_date, sex, nationality, family_status, website, email,
                    current_workplace, address_id)
values ("Nina", "Averina", "Viktorovna", "1996/12/01", "female", "Russia", "divorced", "https://vk.com/id142463263",
        "maroonracoon@gmail.com", "ТЦ \"Ладога\"", 3);