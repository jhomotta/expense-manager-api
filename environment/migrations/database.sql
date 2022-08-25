CREATE DATABASE IF NOT EXISTS `expensetracker`;

USE expensetracker;
-- auto-generated definition
create table tbl_users
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) not null,
    email      varchar(255) not null,
    password   varchar(500) not null,
    age        bigint       not null,
    created_at datetime     not null,
    update_at  datetime     not null
);

create table tbl_expenses
(
    id             int auto_increment
        primary key,
    expense_name   varchar(255) not null,
    description    varchar(255) not null,
    expense_amount double(5, 2) not null,
    category       varchar(255) not null,
    date           date         not null,
    created_at     datetime     not null,
    update_at      datetime     not null,
    user_id        bigint          not null
);


INSERT INTO expensetracker.tbl_users (name, email, password, age, created_at, update_at)
VALUES
('Jhon', 'jhon@motta.com', '$2a$10$17YK/uPy3bQKBwE5cKvC1uYqC00/7zQpn0lX3hl3e1GhP6FYG1OT2', 13, '2022-07-30 13:40:04', '2022-07-30 13:40:04'),
('Cheno', 'cheno@motta.com', '$2a$10$cFStAu1P5235noEHYjrfGeeNSHWhj8oI5gTbqSSLIrtAHd8F8zzAK', 13, '2022-07-30 13:42:00', '2022-07-30 13:42:00'),
('milo', 'milo@motta.com', '$2a$10$B68EDX35bkRwhrySvUrAEesyLvY5OrGK/z0e/0bEamKx7EdAfKKRe', 13, '2022-07-30 13:42:19', '2022-07-30 13:42:19');

INSERT INTO tbl_expenses(expense_name, description, expense_amount, category, date, created_at, update_at, user_id)
values
('Water bill', 'water bill', 900.00, 'Bills', '2021-10-11', now(), now(), (select id from tbl_users where email = 'jhon@motta.com')),
('Electricity bill', 'electricity bill', 500.00, 'Other', '2021-10-13', now(), now(), (select id from tbl_users where email = 'jhon@motta.com')),
('Gas bill', 'gas bill', 600.00, 'Bills', '2021-10-14', now(), now(), (select id from tbl_users where email = 'jhon@motta.com')),
('Network bill', 'network bill', 300.00, 'Other', '2021-10-15', now(), now(), (select id from tbl_users where email = 'jhon@motta.com')),
('Cell bill', 'cell bill', 600.00, 'Bills', '2021-10-20', now(), now(), (select id from tbl_users where email = 'jhon@motta.com'));