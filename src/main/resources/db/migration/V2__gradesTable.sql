create table grades (
    id varchar(255),
    score int,
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp,
    deleted_at datetime default null,
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;