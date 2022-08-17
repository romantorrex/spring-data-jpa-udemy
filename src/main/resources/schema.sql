create table product
(
    id          int PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(20),
    description VARCHAR(256),
    price       DECIMAL(8, 2)
);

create table customer
(
    id    int PRIMARY KEY AUTO_INCREMENT,
    name  varchar(256) NOT NULL,
    email varchar(256)
);

create table phone_number
(
    id          int PRIMARY KEY AUTO_INCREMENT,
    customer_id int,
    number      varchar(20),
    type        varchar(20),
    FOREIGN KEY (customer_id)
        REFERENCES customer (id)
);

create table student
(
    id    int PRIMARY KEY AUTO_INCREMENT,
    fname varchar(128),
    lname varchar(128),
    score int
);

create table payment
(
    id           int primary key auto_increment,
    pmode        char(2),
    amount       decimal(8, 3),
    card_number  varchar(20),
    check_number varchar(20)
);

create table invoice
(
    id             int primary key auto_increment,
    amount         decimal(8, 3),
    invoice_number varchar(10)
);

create table credit_note
(
    id                 int primary key auto_increment,
    amount             decimal(8, 3),
    credit_note_number varchar(10)
);

create table vehicle
(
    id    int primary key auto_increment,
    brand varchar(20)
);

create table bike
(
    id        int,
    bike_type varchar(2),
    foreign key (id) references vehicle (id)
);

create table compound_employee
(
    id      int,
    name    varchar(20),
    street  varchar(30),
    city    varchar(20),
    state   varchar(20),
    zipcode varchar(20),
    country varchar(20)
);

-- Tables for many to many relationship
create table programmer
(
    id     int PRIMARY KEY AUTO_INCREMENT,
    name   varchar(20),
    salary decimal(8, 3)
);

create table project
(
    id   int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20)
);

create table programmers_projects
(
    programmer_id int,
    project_id    int,
    FOREIGN KEY (programmer_id)
        REFERENCES programmer (id),
    FOREIGN KEY (project_id)
        REFERENCES project (id)
);

-- Tables for one-to-one relationships
create table worker
(
  worker_id int PRIMARY KEY,
  first_name varchar(64),
  last_name varchar(64)
);

create table salary
(
  ssn varchar(12) PRIMARY KEY,
  salary_level int,
  worker_id int,
  FOREIGN KEY (worker_id)
    REFERENCES worker (worker_id)
);

create table parking_space
(
  id int PRIMARY KEY,
  location varchar(256),
  worker_id int
);
