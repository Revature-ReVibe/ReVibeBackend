
Insert into account (username, name, password, email) values ('firstUser', 'first', 'root', 'a@rev.com'),
('secondUser', 'second', 'toor', 'second@gmail.co');
Insert into vibe (vibepic, vibemessage, accountid, parentvibe, vibetimestamp) values ('picture', 'vibemessage', 1, null, null);
Insert into vibe (vibepic, vibemessage, accountid, parentvibe, vibetimestamp) values ('picture2', 'vibemessage2', 2, 1, null);
Insert into liketable(vibeid,accountid) values (1, 1);

