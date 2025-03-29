package com.example.event_management.repository;

import com.example.event_management.model.entity.Attendee;
import com.example.event_management.model.dto.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    // get all attendee
    @Select("""
            select * from attendees
            limit #{limit}
            offset #{limit} * (#{offset} - 1)
            """)
    @Results(id = "AttendeeMapping", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    List<Attendee> findAllAttendees(Integer offset, Integer limit);

    // get attendee by id
    @ResultMap("AttendeeMapping")
    @Select("select * from attendees where attendee_id = #{attendeeId}")
    Attendee findAttendeeById(Integer attendeeId);

    //-- insert attendee
    @Select("""
                INSERT INTO attendees(attendee_name,email)
                values (#{attendee.attendeeName},#{attendee.email})
                returning *
            """)
    @ResultMap("AttendeeMapping")
    Attendee insertAttendee(@Param("attendee") AttendeeRequest attendeeRequest);

    // update attendee
    @Select("""
                Update attendees
                SET attendee_name = #{attendee.attendeeName},email = #{attendee.email}
                where attendee_id = #{attendeeId}
                returning *
            """)
    @ResultMap("AttendeeMapping")
    Attendee updateAttendeeById(@Param("attendee") AttendeeRequest attendeeRequest, Integer attendeeId);

    // delete attendee
    @Delete("delete from attendees where attendee_id = #{attendeeId}")
    void deleteAttendeeById(Integer attendeeId);
}
