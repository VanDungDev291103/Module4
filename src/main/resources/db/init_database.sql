create database student_management;

use student_management;

create table student(
                        id int auto_increment primary key,
                        name varchar(50) not null,
                        score double
);

insert into student (name, score) VALUE ('Lâm',9.6);
insert into student (name, score) VALUE ('truyền',9.5);
insert into student (name, score) VALUE ('nghĩa',4);

update student set name = 'thong' , score = 7 where id = 3;

select id,name,score from student;