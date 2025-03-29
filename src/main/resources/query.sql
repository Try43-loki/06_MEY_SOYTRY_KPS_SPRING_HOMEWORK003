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

create table event_attendee(
                                event_id int references events(event_id) on delete cascade not null,
                                attendant_id int references attendees(attendant_id) on delete cascade not null

);

create table attendees(
                           attendant_id serial primary key,
                           attendant_name varchar(100) not null ,
                           email varchar(150)
);

ALTER TABLE attendees RENAME COLUMN attendant_name TO attendee_name;

insert into venues(venue_name, location)
values ('Concert','Phnom Penh');

insert into events(event_name, event_date, venue_id)
values ('Sting', '10-01-2025',1);

insert into attendees(attendee_name, email)
values ('Dara','Dara@gmail.com');

insert into event_attendee(event_id, attendee_id)
values (2,10);

SELECT * from attendees a
inner join event_attendee ea
on a.attendee_id = ea.attendee_id
where ea.event_id = 1;

delete from event_attendee where event_id = 2;

SELECT * from venues where venue_id = 10;


