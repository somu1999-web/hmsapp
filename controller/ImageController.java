package com.hmsapp_test.controller;

import com.hmsapp_test.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private BucketService bucketService;

    public ImageController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("upload/file/{bucketName}/property/{propertyId}")
    public ResponseEntity<?> uploadPropertyPhotos(
            @RequestParam("file") MultipartFile file ,
            @PathVariable("bucketName") String bucketName ,
            @PathVariable("propertyId") long propertyId
            ){
        String imageUrl = bucketService.uploadFile(file , bucketName);
        return new ResponseEntity<>(imageUrl , HttpStatus.OK);
    }
}
