insert into authorities(authority) values ('ROLE_USER');
insert into authorities(authority) values ('ROLE_ADMIN');

insert into users (enabled, username, email, password, authority_id)
values (true, 'admin', 'admin', '$2a$10$bw/LSxqnWYvkc7T7P29Sn.RKCrP6LRWK9qfyiF/QnbVgiaBpMCu9a', 2);