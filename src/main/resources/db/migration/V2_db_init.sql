drop table if exists post;
drop table if exists "user";
drop table if exists comment;
drop sequence if exists user_id_seq;
drop sequence if exists post_id_seq;
drop sequence if exists comment_id_seq;

create sequence user_id_seq
    start with 5;

alter sequence user_id_seq owner to postgres;

create sequence post_id_seq
    start with 5;

alter sequence post_id_seq owner to postgres;

create sequence comment_id_seq
    start with 5;

alter sequence comment_id_seq owner to postgres;

create table "user"
(
    id         integer default nextval('user_id_seq'::regclass) not null
        constraint user_id_pk
            primary key,
    first_name varchar(512)                                     not null,
    last_name  varchar(512)                                     not null
);

alter table "user"
    owner to postgres;

alter sequence user_id_seq owned by "user".id;

create table post
(
    id      integer default nextval('post_id_seq'::regclass) not null
        constraint post_pk
            primary key,
    title   varchar(512) not null,
    body    text         not null,
    user_id integer      not null
        constraint post_user_id_fk
            references "user"
);

alter table post
    owner to postgres;

alter sequence post_id_seq owned by post.id;

create table comment
(
    id      integer default nextval('post_id_seq'::regclass) not null
        constraint comment_pk
            primary key,
    title   varchar(512) not null,
    body    text         not null,
    post_id integer      not null
        constraint comment_post_id_fk
            references post,
    user_id integer      not null
        constraint comment_user_id_fk
            references "user"
);

alter table comment
    owner to postgres;

alter sequence comment_id_seq owned by comment.id;

insert into "user"
values (1, 'Joe', 'Biden'),
       (4, 'Adam', 'Charles'),
       (3, 'Adam', 'Smith'),
       (2, 'George', 'Bush');

insert into post
values
    (3,'ABC','XYZ',2),
    (2,'Мій другий пост','Всім ще більше вітань!',4),
    (1,'Мій перший пост','Всім привіт',3),
    (4,'Новий пост','Тіло нового поста',3);

insert into comment
values
    (1,'Перший комент','Тіло першого комента',3, 1),
    (2,'Другий комент','Тіло другого комента',3, 3),
    (3,'Третій комент','Тіло третього комента',1, 2),
    (4,'Четвертий комент','Тіло четвертого комента',2, 1);