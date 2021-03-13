package park.service;

import org.springframework.web.bind.annotation.*;
import park.domain.Park;
import park.exception.ParkNotFoundException;

import java.util.List;

public interface ParkService {

    public List<Park> all();

    public Park one( final Long code);


    Park addPark(final Park newPark);

    public Park replacePark( final Park newPark, final Long parkCode);

}
