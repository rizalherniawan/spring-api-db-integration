alter table grades add student_id varchar(255) after score;

alter table grades add foreign key (student_id) references students(id) on delete cascade;