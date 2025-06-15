package com.hmsapp_test.service;

import com.hmsapp_test.entity.City;
import com.hmsapp_test.payload.CityDto;
import com.hmsapp_test.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    public CityDto mapToDto(City city){
        CityDto dto = modelMapper.map(city , CityDto.class);
        return dto;
    }

    public City mapToEntiy(CityDto cityDto){
        City city = modelMapper.map(cityDto, City.class);
        return city;
    }

    public CityDto addCity(CityDto cityDto) {
        City city = mapToEntiy(cityDto);
        City save = cityRepository.save(city);
        CityDto dto = mapToDto(save);
        return dto;
    }

    public void deleteCity(long id) {
        cityRepository.deleteById(id);
    }

    public CityDto updateCity(long id, CityDto cityDto) {
        City city = mapToEntiy(cityDto);
        city.setId(id);
        City save = cityRepository.save(city);
        CityDto dto = mapToDto(save);
        return dto;
    }

    public List<CityDto> getAllCity() {
        List<City> cities = cityRepository.findAll();
        List<CityDto> dto = cities.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }
}

