create table employee (id  bigserial not null, address varchar(255), city varchar(60) not null, email varchar(60) not null, first_name varchar(60), last_name varchar(60) not null, phone varchar(20) not null, primary key (id));
alter table if exists employee add constraint UK_fopic1oh5oln2khj8eat6ino0 unique (email);
alter table if exists employee add constraint UK_buf2qp04xpwfp5qq355706h4a unique (phone);
