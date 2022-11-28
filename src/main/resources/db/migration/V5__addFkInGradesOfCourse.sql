alter table grades add course_id varchar(255) after student_id;

alter table grades add foreign key (course_id) references course(id) on delete cascade;