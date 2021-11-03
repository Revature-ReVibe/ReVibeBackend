
Insert into account (accountid,username, name, password, email, profilepic) values (1,'firstUser', 'first', 'root', 'a@rev.com', 'https://image.shutterstock.com/mosaic_250/2598844/1554086789/stock-photo-close-up-portrait-of-yong-woman-casual-portrait-in-positive-view-big-smile-beautiful-model-posing-1554086789.jpg'),
(2,'secondUser', 'second', 'toor', 'second@gmail.co', 'https://t4.ftcdn.net/jpg/01/28/62/43/360_F_128624348_EAX9WMc20TJuy3NEBQnxPNm4TchRkN0o.jpg');
Insert into vibe (vibepic, vibemessage, accountid, parentvibe, vibetimestamp, vibelike) values ('picture', 'vibemessage', 1, null, null, 0);
Insert into vibe (vibepic, vibemessage, accountid, parentvibe, vibetimestamp, vibelike) values ('picture2', 'vibemessage2', 2, 1, null, 0);
Insert into liketable(vibeid,accountid) values (1, 1);
Insert into liketable(vibeid,accountid) values (2, 2);
Insert into liketable(vibeid,accountid) values (1, 2);

