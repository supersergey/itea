drop table if exists comment;
drop sequence if exists comment_id_seq;

create sequence comment_id_seq
    start with 5;

alter sequence comment_id_seq owner to postgres;

create table comment
(
    id      integer default nextval('comment_id_seq'::regclass) not null
        constraint comment_pk
            primary key,
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

insert into comment
values
    (1,'Перший коментар',3, 2),
    (2,'Другий коментар',3, 3),
    (3,'Третій коментар',1, 2),
    (4,'Четвертий коментар',2, 1);