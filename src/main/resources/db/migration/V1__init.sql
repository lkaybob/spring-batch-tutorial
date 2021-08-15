create table pay
(
    id           bigint unsigned not null auto_increment primary key,
    amount       bigint unsigned not null,
    tx_name      varchar(1000)   not null,
    tx_date_time timestamp       not null default current_timestamp()
);

create table member
(
    seq       bigint unsigned not null auto_increment primary key,
    name      varchar(1000)   not null,
    address   varchar(1000)   not null,
    member_no bigint          unsigned not null
);

create table transaction
(
    seq        bigint unsigned not null auto_increment primary key,
    amount     bigint unsigned not null,
    created_at timestamp default current_timestamp(),
    member_no  bigint unsigned not null,
    foreign key (member_no) references member (seq)
);

create table transaction_all
(
    seq        bigint unsigned not null auto_increment primary key ,
    name       varchar(1000)   not null,
    address    varchar(1000)   not null,
    amount     bigint unsigned not null,
    created_at timestamp       not null default current_timestamp()
);

INSERT INTO spring_batch.member (seq, name, address) VALUES (1, 'David', 'Seoul');
INSERT INTO spring_batch.member (seq, name, address) VALUES (2, 'Kevin', 'Tokyo');
INSERT INTO spring_batch.member (seq, name, address) VALUES (3, 'Panda', 'Beijing');
INSERT INTO spring_batch.member (seq, name, address) VALUES (4, 'Daniel', 'Los Angeles');

INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (1, 'David', 'Seoul', 10000, '2021-08-15 01:44:28');
INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (2, 'David', 'Seoul', 2000, '2021-08-15 01:44:28');
INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (3, 'Panda', 'Beijing', 1500, '2021-08-15 01:44:28');
INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (4, 'Kevin', 'Tokyo', 1000, '2021-08-15 01:44:28');
INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (5, 'David', 'Seoul', 7000, '2021-08-15 01:44:28');
INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (6, 'Daniel', 'Los Angeles', 3000, '2021-08-15 01:44:28');
INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (7, 'Hugo', 'London', 5000, '2021-08-15 01:44:28');
INSERT INTO spring_batch.transaction_all (seq, name, address, amount, created_at) VALUES (8, 'Jeniffer', 'New York', 4000, '2021-08-15 01:44:28');