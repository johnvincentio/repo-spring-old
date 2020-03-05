Using Postgres on postgreslocal VM

create table employee(
	id  SERIAL PRIMARY KEY,
	firstname	TEXT	NOT NULL,
	lastname	TEXT	NOT NULL,	
   	email		TEXT 	not null,
   	age         INT     NOT NULL,
   	salary         real,
	unique(email)
);

insert into employee (firstname,lastname,email,age,salary) 
values ('Emmanouil','Gkatziouras','gkatzioura@gmail.com',18,3000.23);
