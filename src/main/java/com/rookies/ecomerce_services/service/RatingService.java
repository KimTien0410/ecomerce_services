package com.rookies.ecomerce_services.service;

import com.rookies.ecomerce_services.dto.request.RequestRating;
import com.rookies.ecomerce_services.dto.response.RatingResponse;
import com.rookies.ecomerce_services.entity.Rating;

import java.util.List;

public interface RatingService {
    public RatingResponse addReview(RequestRating rating);
    public RatingResponse updateReview(Long ratingId, RequestRating rating);
    public void deleteReview(Long ratingId);
    public RatingResponse getReviewById(Long ratingId);
    public List<RatingResponse> getAllReviewsByProductId(Long productId);
    public Rating getRatingById(Long ratingId);

    public RatingResponse toRatingResponse(Rating rating);
}
