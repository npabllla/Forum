create table authorities(
    id        serial primary key,
    authority varchar(255)
);

create table users(
    id           serial primary key,
    email        varchar(255) unique,
    username         varchar(255),
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

create table comments(
    id serial primary key,
    content varchar(255),
    created timestamp,
    user_id int references users(id),
    post_id int references posts(id)
);

create table answers(
    id serial primary key,
    content varchar(255),
    created timestamp,
    user_id int references users(id)
);

create table comments_answers(
    comment_id int references comments(id),
    answers_id int references answers(id)
);