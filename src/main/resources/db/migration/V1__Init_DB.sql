create table granted_user
(
    id              uuid      not null,
    created_at      timestamp not null,
    updated_at      timestamp,
    activation_code varchar(255),
    email           varchar(255) not null,
    is_active       boolean   not null,
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);

create table media_file
(
    id         uuid      not null,
    created_at timestamp not null,
    updated_at timestamp,
    data       oid,
    file_name  varchar(255),
    file_type  varchar(255),
    primary key (id)
);

create table message
(
    id         uuid      not null,
    created_at timestamp not null,
    updated_at timestamp,
    tag        varchar(255),
    text       varchar(2048),
    user_id    uuid,
    file_id    uuid,
    primary key (id)
);

create table user_role
(
    user_id uuid not null,
    roles   varchar(255)
);

alter table if exists message add constraint message_granted_usr_fk foreign key (user_id) references granted_user on delete cascade;
alter table if exists message add constraint message_media_file_fk foreign key (file_id) references media_file on delete cascade;
alter table if exists user_role add constraint role_granted_usr_fk foreign key (user_id) references granted_user;
