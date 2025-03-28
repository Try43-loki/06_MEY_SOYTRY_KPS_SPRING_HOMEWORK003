create table venues(
                       venue_id serial primary key,
                       venue_name varchar(100) not null,
                       location varchar(100) not null
);

create table events (
                        event_id serial primary key,
                        event_name varchar(100) not null,
                        event_date varchar(100) not null ,
                        venue_id int references venues(venue_id) on delete cascade not null
);

create table event_attendant(
                                event_id int references events(event_id) on delete cascade not null,
                                attendant_id int references attendants(attendant_id) on delete cascade not null

);

create table attendants(
                           attendant_id serial primary key,
                           attendant_name varchar(100) not null ,
                           email varchar(150)
);

insert into venues(venue_name, location)
values ('Concert','Phnom Penh');

insert into events(event_name, event_date, venue_id)
values ('Sting', '10-01-2025',1);

insert into attendants(attendant_name, email)
values ('Try','try@gmail.com');

insert into event_attendant(event_id, attendant_id)
values (1,1);