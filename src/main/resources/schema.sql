create table if not exists run(
    id int not null,
    title varchar(256) not null,
    start_on timestamp not null,
    complete_on timestamp not null,
    miles int not null,
    location varchar(10) not null,
    primary key(id)
);