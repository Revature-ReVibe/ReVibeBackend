CREATE table account(
accountid int unsigned not null auto_increment Primary key,
username varchar(50) not null unique,
name varchar(100) not null,
password varchar(50) not null,
email varchar(150) not null,
profilepic varchar(200));

CREATE TABLE vibe(
vibeid int unsigned not null auto_increment Primary key,
vibepic varchar(500),
vibemessage varchar(300),
vibelike int unsigned,
accountid int unsigned not null,
parentvibe int,
vibetimestamp TIMESTAMP,
Foreign key (accountid) references account(accountid));

CREATE TABLE liketable(
likeid int unsigned not null auto_increment Primary key,
vibeid int unsigned not null,
accountid int unsigned not null,
Foreign key (vibeid) references vibe(vibeid),
Foreign key (accountid) references account(accountid)
);
