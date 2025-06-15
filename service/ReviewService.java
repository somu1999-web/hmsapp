package com.hmsapp_test.service;

import com.hmsapp_test.entity.Property;
import com.hmsapp_test.entity.Reviews;
import com.hmsapp_test.entity.User;
import com.hmsapp_test.payload.PropertyDto;
import com.hmsapp_test.payload.ReviewDto;
import com.hmsapp_test.repository.PropertyRepository;
import com.hmsapp_test.repository.ReviewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private ModelMapper modelMapper;
    private ReviewsRepository reviewsRepository;
    private PropertyRepository propertyRepository;

    public ReviewService(ModelMapper modelMapper, ReviewsRepository reviewsRepository, PropertyRepository propertyRepository) {
        this.modelMapper = modelMapper;
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
    }

    public ReviewDto mapToDto(Reviews reviews){
        ReviewDto dto = modelMapper.map(reviews , ReviewDto.class);
        return dto;
    }

    public Reviews mapToEntity(ReviewDto reviewDto){
        Reviews reviews = modelMapper.map(reviewDto, Reviews.class);
        return reviews;
    }

    public ResponseEntity<?> addReview(ReviewDto reviewDto, long propertyId, User user) {
//        System.out.println(user.getName());
//        System.out.println(user.getEmail());
        Property property = propertyRepository.findById(propertyId).get();
        Reviews reviewStatus = reviewsRepository.findByPropertyAndUser(property, user);

        if (reviewStatus != null){
            Reviews reviews = mapToEntity(reviewDto);
            reviews.setProperty(property);
            reviews.setUser(user);
            Reviews save = reviewsRepository.save(reviews);
            ReviewDto dto = mapToDto(save);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

        return new ResponseEntity<>("User already has a review for this property" , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<ReviewDto> viewMyReviews(User user) {
        List<Reviews> reviews = reviewsRepository.findByUser(user);
        List<ReviewDto> dtos = reviews.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dtos;
    }
}
