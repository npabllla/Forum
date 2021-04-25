create table authorities(
    id        serial primary key,
    authority varchar(255)
);

create table users(
    id           serial primary key,
    email        varchar(255) unique,
    name         varchar(255),
    password     varchar(255),
    enabled      boolean,
    authority_id int references authorities (id)
);

create table posts(
    id          serial primary key,
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now(),
    user_id     int references users (id)
);

insert into authorities(authority) values ('ROLE_USER');
insert into authorities(authority) values ('ROLE_ADMIN');