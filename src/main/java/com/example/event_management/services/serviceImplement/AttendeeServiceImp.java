package com.example.event_management.services.serviceImplement;

import com.example.event_management.exception.UserNotFoundException;
import com.example.event_management.model.entity.Attendee;
import com.example.event_management.model.dto.request.AttendeeRequest;
import com.example.event_management.repository.AttendeeRepository;
import com.example.event_management.services.serviceInterface.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImp implements AttendeeService {


    //-- inject Attendee Repository
    private final AttendeeRepository attendeeRepository;
    public AttendeeServiceImp(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees(Integer offset,Integer limit) {
        return attendeeRepository.findAllAttendees(offset,limit);
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        Attendee venue = attendeeRepository.findAttendeeById(attendeeId);
        if(attendeeId < 0 ){
            throw new UserNotFoundException("Attendee ID must be greater than 0");
        }
        if(venue==null){
            throw new UserNotFoundException("Attendee ID " + attendeeId + " not found");
        }
        return venue;
    }

    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer attendeeId) {
        Attendee venue = attendeeRepository.findAttendeeById(attendeeId);
        if(venue==null){
            throw new UserNotFoundException("Attendee ID " + attendeeId + " not found");
        }
        return attendeeRepository.updateAttendeeById(attendeeRequest, attendeeId);
    }

    @Override
    public void deleteAttendeeById(Integer attendeeId) {
      if(  attendeeRepository.findAttendeeById(attendeeId) == null){
          throw new UserNotFoundException("Attendee ID " + attendeeId + " not found");
      }
        attendeeRepository.deleteAttendeeById(attendeeId);
    }


}
