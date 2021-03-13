package park.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park.domain.Park;
import park.exception.ParkNotFoundException;
import park.repository.ParkRepository;
import park.service.ParkService;


/**
 * park controller.
 */
@RestController
@RequestMapping("/parks")
public class ParkController {


    @Autowired
    private ParkService parkService;



    @GetMapping("/")
    public List<Park> all() {
        return parkService.all();
    }

    @GetMapping("/parks/{parkCode}")
    public Park one(@PathVariable Long code) {

        return parkService.one(code);
    }

    @PostMapping("/")
    public ResponseEntity<Park>  addPark(@RequestBody Park newPark) {
        HttpStatus status = HttpStatus.CREATED;
        Park saved = parkService.addPark(newPark);
        return new ResponseEntity<>(saved, status);
    }

    @PutMapping("/{parkCode}")
    public Park replacePark(@RequestBody Park newPark, @PathVariable final Long parkCode) {

            return parkService.replacePark(newPark,parkCode);
        }
    }


