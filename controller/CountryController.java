package com.hmsapp_test.controller;

import com.hmsapp_test.payload.CountryDto;
import com.hmsapp_test.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<CountryDto> addCountry(
            @RequestBody CountryDto countryDto){
        CountryDto dto = countryService.addCountry(countryDto);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }

    //   api/v1/country?id=1
    @DeleteMapping
    public ResponseEntity<String> deleteCountry(
            @RequestParam("id") long id){
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Country deleted", HttpStatus.OK);
    }

    //    api/v1/country?id=2
    @PutMapping
    public ResponseEntity<CountryDto> updateCountry(
            @RequestParam("id") long id ,
            @RequestBody CountryDto countryDto){
        CountryDto dto = countryService.updateCountry(id, countryDto);
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountry(){
        List<CountryDto> dto = countryService.getAllCountry();
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }
}
