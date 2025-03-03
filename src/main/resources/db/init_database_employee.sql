create database employee_management;

use employee_management;

create table department(
                           id int auto_increment primary key,
                           name varchar(100) not null
);

create table employee(
                         id int auto_increment primary key,
                         name varchar(50) not null,
                         gender enum('male','female','other') not null,
                         salary double not null,
                         phone varchar(15),
                         departmentId int,
                         foreign key (departmentId) references department(id)
);


insert into department (id, name) VALUE (1,'kế toán');
insert into department (id, name) VALUE (2,'sale marketing');
insert into department (id, name) VALUE (3,'IT');
insert into department (id, name) VALUE (4,'quản lý');

insert into employee (id, name, gender, salary, phone, departmentId) VALUE (1,'tuấn','male',20000,'0987654321',1);
insert into employee (id, name, gender, salary, phone, departmentId) VALUE (2,'hoa','female',30000,'0988888888',2);
insert into employee (id, name, gender, salary, phone, departmentId) VALUE (3,'khang','male',40000,'0987777777',3);
insert into employee (id, name, gender, salary, phone, departmentId) VALUE (4,'ánh','female',10000,'0987666666',4);

select * from employee;