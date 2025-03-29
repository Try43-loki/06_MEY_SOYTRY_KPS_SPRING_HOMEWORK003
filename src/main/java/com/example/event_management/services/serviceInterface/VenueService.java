package com.example.event_management.services.serviceInterface;

import com.example.event_management.model.entity.Venue;
import com.example.event_management.model.dto.request.VenueRequest;

import java.util.List;

public interface VenueService {

    List<Venue> getAllVenues(Integer offset,Integer limit);
    Venue getVenueById(Integer venueId);
    Venue addVenue(VenueRequest venueRequest);
    Venue updateVenueById(VenueRequest venueRequest, Integer venueId);
    void deleteVenueById(Integer venueId);

}
