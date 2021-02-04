insert into course(id, fullname, created_date, last_updated_time) 
values (10001, 'English', sysdate(), sysdate());

insert into passport(id, name)
values (30001, 'EKCF12345');
insert into passport(id, name)
values (30002, 'EKCF7890');
insert into passport(id, name)
values (30003, 'EKCF13579');

insert into student(id, name, passport_id)
values (20001, 'Dipa', 30001);
insert into student(id, name, passport_id)
values (20002, 'Chitra', 30002);
insert into student(id, name, passport_id)
values (20003, 'Dipu', 30003);

insert into review(id, description, rating)
values (30001, null, 'wow');
insert into review(id, description, rating)
values (30002, null, 'amazing');
insert into review(id, description, rating)
values (30003, null, 'love it');