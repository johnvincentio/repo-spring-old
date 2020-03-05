use springmvc_3;

drop table if exists employee;

create table employee (
	id bigint not null auto_increment, 
	name varchar(50) not null, 
	joining_date timestamp not null,
	salary double not null,
	ssn varchar(12) not null,
	primary key (id)
);
