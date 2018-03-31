insert into user (id, login, password, email, photo) values
    (1, 'User1', 'pass', 'email1', NULL),
    (2, 'User2', 'pass', 'email2', NULL);

insert into language (id, name) values
    (1, 'Russian'),
    (2, 'English');

insert into book (id, title, pages_count, description, image, language_id) values
    (1, 'Title1', 10, NULL, NULL, 1),
    (2, 'Title2', 20, NULL, NULL, 2);

insert into author (id, first_name, last_name, description, photo) values
    (1, 'FirstName1', 'LastName1', NULL, NULL),
    (2, 'FirstName2', 'LastName2', NULL, NULL);

insert into category (id, name) values
    (1, 'Pop Science'),
    (2, 'Classic'),
    (3, 'Thriller');

insert into book_user (user_id, book_id) values
    (1, 1),
    (1, 2),
    (2, 1);

insert into book_author (author_id, book_id) values
    (1, 1),
    (1, 2),
    (2, 2);

insert into book_category (category_id, book_id) values
    (1, 1),
    (2, 1),
    (2, 2);