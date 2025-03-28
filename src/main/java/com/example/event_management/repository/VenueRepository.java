package com.example.event_management.repository;

import com.example.event_management.model.Venue;
import com.example.event_management.model.request.VenueRequest;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface VenueRepository {
    // get all venue
    @Select("""
            select * from venues
            limit #{limit}
            offset #{limit} * (#{offset} - 1)
            """)
    @Results(id = "VenueMapping" , value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
    })
    List<Venue> findAllVenues(Integer offset,Integer limit);

    // get venue by id
    @ResultMap("VenueMapping")
    @Select("select * from venues where venue_id = #{venueId}")
    Venue findVenueById(Integer venueId);

    //-- insert venue
    @Select("""
    INSERT INTO venues(venue_name,location)
    values (#{venue.venueName},#{venue.location})
""")
    Venue insertVenue(@Param("venue") VenueRequest venueRequest);

}
