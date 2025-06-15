package com.hmsapp_test.service;

import com.hmsapp_test.entity.Country;
import com.hmsapp_test.payload.CountryDto;
import com.hmsapp_test.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private CountryRepository countryRepository;
    private ModelMapper modelMapper;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    public CountryDto mapToDto(Country country){
        CountryDto dto = modelMapper.map(country , CountryDto.class);
        return dto;
    }

    public Country mapToEntiy(CountryDto countryDto){
        Country country = modelMapper.map(countryDto, Country.class);
        return country;
    }

    public CountryDto addCountry(CountryDto countryDto) {
        Country country = mapToEntiy(countryDto);
        Country save = countryRepository.save(country);
        CountryDto dto = mapToDto(save);
        return dto;
    }

    public void deleteCountry(long id) {
        countryRepository.deleteById(id);
    }

    public CountryDto updateCountry(long id, CountryDto countryDto) {
        Country country = mapToEntiy(countryDto);
        country.setId(id);
        Country save = countryRepository.save(country);
        CountryDto dto = mapToDto(save);
        return dto;
    }

    public List<CountryDto> getAllCountry() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDto> dto = countries.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }
}