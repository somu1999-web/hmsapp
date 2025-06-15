package com.hmsapp_test.controller;

import com.hmsapp_test.payload.CityDto;
import com.hmsapp_test.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/addCity")
    public ResponseEntity<CityDto> addCity(
            @RequestBody CityDto cityDto) {
        CityDto dto = cityService.addCity(cityDto);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCity(
            @RequestParam("id") long id) {
        cityService.deleteCity(id);
        return new ResponseEntity<>("City deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CityDto> updateCity(
            @RequestParam("id") long id ,
            @RequestBody CityDto cityDto) {
        CityDto updatedCity = cityService.updateCity(id, cityDto);
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCity(){
        List<CityDto> dto = cityService.getAllCity();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

