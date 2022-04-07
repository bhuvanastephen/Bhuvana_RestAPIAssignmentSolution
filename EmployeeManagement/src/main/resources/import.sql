select * from employee.roles;
insert into roles values (1,'ADMIN');
insert into roles values (2,'USER');

select * from employee.users;
-- admin/admin ; bhuvana/bhuvana
insert into users values(1,'$2a$12$sPV7/Zk2FpmI8n59FWSlMuuOmXjSYMNTykJoj5llU68uSIPnhnACy','admin');
insert into users values(2,'$2a$12$hLvh0VgUrdclxrvpKTfVCecHVGwpIkgAphniGVu6Bh1C4BRUAtIbu','bhuvana');

select * from employee.users_roles;
insert into users_roles values(1,1);
insert into users_roles values(2,2);