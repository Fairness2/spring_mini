create table student
(
    id int auto_increment,
    name varchar(255) null,
    surname varchar(255) null,
    patronymic varchar(255) null,
    age tinyint unsigned null,
    constraint student_pk
        primary key (id)
);