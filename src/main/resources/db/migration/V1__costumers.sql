create table costumers (id bigint not null auto_increment,
name varchar(255) not null,
primary key(id));

create table books (id bigint not null auto_increment,
                    title varchar(255) not null ,
                    author varchar(255) not null,
                    removable bool,
                    costumer_id bigint,
                    primary key(id),
                    foreign key (costumer_id) references costumers(id));