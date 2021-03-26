insert into user (username, password) values ('user1', 'password');

insert into task (id, severity, task_name, start_date, end_date, description, email, username) 
	   values ('111', 'HIGH', 'first task', CURRENT_DATE(), CURRENT_DATE(), 'description 1', 'email1@email.com', 'user1');
insert into task (id, severity, task_name, start_date, end_date, description, email, username) 
	   values ('444', 'LOW', 'special task', CURRENT_DATE(), CURRENT_DATE(), 'special description', 'email1@email.com', 'user1');
	   
	   
insert into user (username, password) values ('user2', 'password');

insert into task (id, severity, task_name, start_date, end_date, description, email, username) 
	   values ('222', 'MEDIUM', 'second task', CURRENT_DATE(), CURRENT_DATE(), 'description 2', 'email2@email.com', 'user2');

insert into user (username, password) values ('user3', 'password');

insert into task (id, severity, task_name, start_date, end_date, description, email, username) 
	   values ('333', 'LOW', 'third task', CURRENT_DATE(), CURRENT_DATE(), 'description 3', 'email3@email.com', 'user3');
