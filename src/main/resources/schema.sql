CREATE table account(
accountid int unsigned not null auto_increment Primary key,
username varchar(50) not null unique,
name varchar(100) not null,
password varchar(50) not null,
email varchar(150) not NULL Unique,
profilepic varchar(800));

CREATE TABLE vibe(
vibeid int unsigned not null auto_increment Primary key,
vibepic varchar(800),
vibemessage varchar(300),
accountid int unsigned not null,
parentvibe int,
vibelike int unsigned not null,
vibetimestamp TIMESTAMP,
Foreign key (accountid) references account(accountid));

CREATE TABLE liketable(
likeid int unsigned not null auto_increment Primary key,
vibeid int unsigned not null,
accountid int unsigned not null,
Foreign key (vibeid) references vibe(vibeid),
Foreign key (accountid) references account(accountid)
);
