package com.hmsapp_test.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class BucketService {
    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file, String bucketName){
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty");
        }
        try{
            File convFile=new File(System.getProperty("java.io.tmpder")+"/"+file.getOriginalFilename());
            file.transferTo(convFile);
            try{
                amazonS3.putObject(bucketName,convFile.getName(),convFile);
                return amazonS3.getUrl(bucketName,file.getOriginalFilename()).toString();

            }
            catch(AmazonS3Exception S3Exception)
            {
                return  "Unable to aplode File :"+S3Exception.getMessage();
            }
        }catch(Exception e){
            return "unable to aplode file"+e.getMessage();
        }
    }
}
