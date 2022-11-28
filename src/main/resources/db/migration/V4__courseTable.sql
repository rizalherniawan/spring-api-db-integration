create table course (
    id varchar(255),
    subject varchar(255),
    code varchar(255),
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp,
    deleted_at datetime default null,
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

