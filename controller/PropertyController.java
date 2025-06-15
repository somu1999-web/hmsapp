package com.hmsapp_test.controller;

import com.hmsapp_test.entity.Property;
import com.hmsapp_test.entity.PropertyImage;
import com.hmsapp_test.payload.PropertyDto;
import com.hmsapp_test.repository.PropertyImageRepository;
import com.hmsapp_test.repository.PropertyRepository;
import com.hmsapp_test.service.BucketService;
import com.hmsapp_test.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private PropertyService propertyService;
    private BucketService bucketService;
    private PropertyRepository propertyRepository;
    private PropertyImageRepository propertyImageRepository;

    public PropertyController(PropertyService propertyService, PropertyRepository propertyRepository, PropertyImageRepository propertyImageRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
        this.propertyImageRepository = propertyImageRepository;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> addProperty(
            @RequestBody PropertyDto propertyDto ,
            @RequestParam("countryId") long countryId ,
            @RequestParam("cityId") long cityId){

        PropertyDto dto = propertyService.addProperty(propertyDto, countryId, cityId);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProperty(@RequestParam("id") long id){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>("Property deleted successfully" , HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PropertyDto> updateProperty(
            @RequestBody PropertyDto propertyDto ,
            @RequestParam("id") long id ,
            @RequestParam("countryId") long countryId ,
            @RequestParam("cityId") long cityId
    ){
        PropertyDto dto = propertyService.updateProperty(propertyDto , id , countryId , cityId);
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllProperty(){
        List<PropertyDto> dtos = propertyService.getAllProperty();
        return new ResponseEntity<>(dtos , HttpStatus.OK);
    }

    @GetMapping("/{searchParam}")
    public ResponseEntity<List<PropertyDto>> seachProperty(
            @PathVariable String searchParam
    ){
        List<PropertyDto> dtos = propertyService.seachProperty(searchParam);
        return new ResponseEntity<>(dtos , HttpStatus.OK);
    }

    @PostMapping("/upload/file/{bucketName}/property/{propertyId}")
    public String uploadPropertyPhotos(
            @RequestParam("file") MultipartFile file ,
            @PathVariable("bucketName") String bucketName ,
            @PathVariable("propertyId") long propertyId
            ){
        String imageUrl = bucketService.uploadFile(file, bucketName);
        PropertyImage propertyImage = new PropertyImage();
        propertyImage.setUrl(imageUrl);

        Property property = propertyRepository.findById(propertyId).get();
        propertyImage.setProperty(property);
        propertyImageRepository.save(propertyImage);
        return "image uploaded successfully";

    }

    @GetMapping("/get/property/images")
    public List<PropertyImage> getPropertyImages(
            @RequestBody long id
    ){
        Property property = propertyRepository.findById(id).get();
        return propertyImageRepository.findByProperty(property);
    }
}
