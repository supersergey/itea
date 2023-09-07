drop table if exists post;
drop table if exists "user";
drop sequence if exists user_id_seq;
drop sequence if exists post_id_seq;

create sequence user_id_seq
    start with 5;

alter sequence user_id_seq owner to postgres;

create sequence post_id_seq
    start with 5;

alter sequence post_id_seq owner to postgres;

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