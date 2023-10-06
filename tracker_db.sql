drop database SpaceIT_DB;
drop user SpaceIT;
create user SpaceIT with password 'SpaceITMPassword';
create database SpaceIT_DB with template= template0 owner = SpaceIT;
\connect SpaceIT_DB;
alter default privileges grant all on tables to SpaceIT;
alter default privileges grant all on sequences to SpaceIT;

create table spaceitm_users(
                               user_id integer primary key not null,
                               first_name varchar(20) not null,
                               last_name varchar(20) not null,
                               email varchar(30) not null,
                               password text not null
);

create table space_categories(
                                 category_id integer primary key not null,
                                 user_id integer not null,
                                 title varchar(20) not null,
                                 description varchar(100) not null
);

create sequence spaceitm_users_seq increment 1 start 1;