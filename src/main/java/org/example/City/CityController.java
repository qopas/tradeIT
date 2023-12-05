package org.example.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    public CityRepository cityRepository;
    @GetMapping()
    public ResponseEntity<List<City>> getCities(){
        return new ResponseEntity<>(cityRepository.findAll(), HttpStatus.OK);
    }
}
