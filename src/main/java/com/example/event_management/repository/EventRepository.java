package com.example.event_management.repository;

import com.example.event_management.model.entity.Attendee;
import com.example.event_management.model.entity.Event;
import com.example.event_management.model.dto.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {
    //-- select all event
    @Select("""
                select * from events
                limit #{limit}
                offset #{limit} * (#{offset} - 1)
            """)
    @Results(id = "eventMapping", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_Id",
                    one = @One(select = "com.example.event_management.repository.VenueRepository.findVenueById")
            ),
            @Result(property = "attendeeList", column = "event_id",
                    many = @Many(select = "findAllAttendeesById")
            )
    })
    List<Event> findAllEvents(Integer offset, Integer limit);

    //-- select attendee

    @Select("""
                SELECT * from attendees a
                inner join event_attendee ea
                on a.attendee_id = ea.attendee_id
                where ea.event_id = #{attendeeId};
            """)
    @Result(property = "attendeeId", column = "attendee_id")
    @Result(property = "attendeeName", column = "attendee_name")
    List<Attendee> findAllAttendeesById(Integer attendeeId);


    //-- get event by id
    @Select("select * from events where event_id = #{eventId}")
    @ResultMap("eventMapping")
    Event findEventById(Integer eventId);

    //-- insert event info
    @Select("""
            INSERT INTO events(event_name, event_date, venue_id)
            VALUES (#{event.eventName}, #{event.eventDate}, #{event.venueId})
            returning event_id;
            """)
    Integer insertEvent(@Param("event") EventRequest eventRequest);

    //-- insert attendee
    @Select("""
            INSERT INTO event_attendee(event_id, attendee_id)
            VALUES (#{eventId}, #{attendeeId})
            """)
    void insertAttendee(Integer eventId, Integer attendeeId);

    //-- update event info
    @Select("""
    update events
    Set event_name = #{event.eventName}, event_date = #{event.eventDate}, venue_id = #{event.venueId}
    where event_id = #{id}
    returning event_id;
""")
    Integer updateEventById(@Param("event") EventRequest eventRequest, Integer id);

    //-- delete attendee from event_attendee
    @Delete("delete from event_attendee where event_id = #{eventId}")
    void deleteAttendeeById(Integer eventId);

    //-- delete event from events
    @Delete("DELETE from events where event_id = #{eventId}")
    void deleteEventById(Integer eventId);

}
