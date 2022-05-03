drop table if exists customers;

create table customers (
                           id serial primary key,
                           first_name varchar(255),
                           last_name varchar(255)
);

insert into customers (first_name, last_name) values ('Harry', 'Potter');
insert into customers (first_name, last_name) values ('Severus', 'Snape');
