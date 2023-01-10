create table roles (
    id varchar(255),
    roles enum('ROLE_ADMIN, ROLE_USER'),
    user_id varchar(255),
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp,
    deleted_at datetime default null,
    primary key (id),
    foreign key (user_id) references users(id)
)