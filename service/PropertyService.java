package com.hmsapp_test.service;

import com.hmsapp_test.entity.City;
import com.hmsapp_test.entity.Country;
import com.hmsapp_test.entity.Property;
import com.hmsapp_test.payload.PropertyDto;
import com.hmsapp_test.repository.CityRepository;
import com.hmsapp_test.repository.CountryRepository;
import com.hmsapp_test.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyService(PropertyRepository propertyRepository, ModelMapper modelMapper, CountryRepository countryRepository, CityRepository cityRepository) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }
    public PropertyDto addProperty(PropertyDto propertyDto, long countryId, long cityId) {
        Country country = countryRepository.findById(countryId).get();
        City city = cityRepository.findById(cityId).get();
        Property property = mapToEntity(propertyDto);
        property.setCity(city);
        property.setCountry(country);
        Property save = propertyRepository.save(property);
        PropertyDto dto = mapToDto(save);
        return dto;
    }

    public PropertyDto mapToDto(Property property){
        PropertyDto dto = modelMapper.map(property, PropertyDto.class);
        return dto;
    }

    public Property mapToEntity(PropertyDto propertyDto){
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setNoOfGuset(propertyDto.getNoOfGuest());
        return property;
    }

    public void deleteProperty(long id) {
        propertyRepository.deleteById(id);
    }

    public PropertyDto updateProperty(PropertyDto propertyDto, long id, long countryId, long cityId) {
        Country country = countryRepository.findById(countryId).get();
        City city = cityRepository.findById(cityId).get();
        Property property = mapToEntity(propertyDto);
        property.setId(id);
        property.setCountry(country);
        property.setCity(city);
        Property save = propertyRepository.save(property);
        PropertyDto dto = mapToDto(save);
        return dto;
    }

    public List<PropertyDto> getAllProperty() {
        List<Property> properties = propertyRepository.findAll();
        List<PropertyDto> dtos = properties.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dtos;
    }

    public List<PropertyDto> seachProperty(String searchParam) {
        List<Property> properties = propertyRepository.searchProperty(searchParam);
        List<PropertyDto> dtos = properties.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dtos;
    }
}
