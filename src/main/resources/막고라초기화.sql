use dbp;

create or replace table member (
id int auto_increment primary key,
loginId varchar(30) unique not null,
password varchar(30) not null,
name varchar(30) not null,
nickname varchar(30) unique not null,
emailAddress varchar(30) not null
);

create or replace table post(
post_id int auto_increment,
uid long,
post_title varchar(100),
post_content varchar(1000),
area varchar(100),
phone varchar(20),
category varchar(10),
primary key(post_id)
)engine=InnoDB default charset = utf8;


select * from member;
select * from post;