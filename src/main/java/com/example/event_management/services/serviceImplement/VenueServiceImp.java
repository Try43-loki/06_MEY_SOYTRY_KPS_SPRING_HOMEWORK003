package com.example.event_management.services.serviceImplement;

import com.example.event_management.exception.UserNotFoundException;
import com.example.event_management.model.Venue;
import com.example.event_management.model.request.VenueRequest;
import com.example.event_management.repository.VenueRepository;
import com.example.event_management.services.serviceInterface.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImp implements VenueService {


    //-- inject Venue Repository
    private final VenueRepository venueRepository;
    public VenueServiceImp(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues(Integer offset,Integer limit) {
        return venueRepository.findAllVenues(offset,limit);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        Venue venue = venueRepository.findVenueById(venueId);
        if(venueId < 0 ){
            throw new UserNotFoundException("Venue ID must be greater than 0");
        }
        if(venue==null){
            throw new UserNotFoundException("Venue ID " + venueId + " not found");
        }
        return venue;
    }

    @Override
    public Venue addVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }


}
