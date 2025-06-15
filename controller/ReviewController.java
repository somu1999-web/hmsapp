package com.hmsapp_test.controller;

import com.hmsapp_test.entity.User;
import com.hmsapp_test.payload.ReviewDto;
import com.hmsapp_test.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<?> addReview(
            @RequestBody ReviewDto reviewDto ,
            @RequestParam("propertyId") long propertyId ,
            @AuthenticationPrincipal User user
            ){
        ResponseEntity<?> dto = reviewService.addReview(reviewDto, propertyId, user);
        return new ResponseEntity<>(dto , HttpStatus.OK);
    }

    @GetMapping("/user/reviews")
    public ResponseEntity<List<ReviewDto>> viewMyReviews(@AuthenticationPrincipal User user){
        List<ReviewDto> dtos = reviewService.viewMyReviews(user);
        return new ResponseEntity<>(dtos , HttpStatus.OK);
    }
}
