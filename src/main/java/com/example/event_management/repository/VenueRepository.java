package com.example.event_management.repository;

import com.example.event_management.model.entity.Venue;
import com.example.event_management.model.dto.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    // get all venue
    @Select("""
            select * from venues
            limit #{limit}
            offset #{limit} * (#{offset} - 1)
            """)
    @Results(id = "VenueMapping", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
    })
    List<Venue> findAllVenues(Integer offset, Integer limit);

    // get venue by id
    @ResultMap("VenueMapping")
    @Select("select * from venues where venue_id = #{venueId}")
    Venue findVenueById(Integer venueId);

    //-- insert venue
    @Select("""
                INSERT INTO venues(venue_name,location)
                values (#{venue.venueName},#{venue.location})
                returning *
            """)
    @ResultMap("VenueMapping")
    Venue insertVenue(@Param("venue") VenueRequest venueRequest);

    // update venue
    @Select("""
                Update venues
                SET venue_name = #{venue.venueName},location = #{venue.location}
                where venue_id = #{venueId}
                returning *
            """)
    @ResultMap("VenueMapping")
    Venue updateVenueById(@Param("venue") VenueRequest venueRequest, Integer venueId);

    // delete venue
    @Delete("delete from venues where venue_id = #{venueId}")
    void deleteVenueById(Integer venueId);
}
