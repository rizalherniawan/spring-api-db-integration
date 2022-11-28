create table students (
    id varchar(255),
    name varchar(255),
    birth_date date,
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp,
    deleted_at datetime default null,
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;