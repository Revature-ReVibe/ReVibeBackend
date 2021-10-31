
Insert into account (accountid,username, name, password, email) values (1,'firstUser', 'first', 'root', 'a@rev.com'),
(2,'secondUser', 'second', 'toor', 'second@gmail.co');
Insert into vibe (vibepic, vibemessage, accountid, parentvibe, vibetimestamp, vibelike) values ('picture', 'vibemessage', 1, null, null, 0);
Insert into vibe (vibepic, vibemessage, accountid, parentvibe, vibetimestamp, vibelike) values ('picture2', 'vibemessage2', 2, 1, null, 0);
Insert into liketable(vibeid,accountid) values (1, 1);
Insert into liketable(vibeid,accountid) values (2, 2);
Insert into liketable(vibeid,accountid) values (1, 2);

