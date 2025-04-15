package com.rookies.ecomerce_services.service.impl;

import com.rookies.ecomerce_services.dto.request.RequestRating;
import com.rookies.ecomerce_services.dto.response.RatingResponse;
import com.rookies.ecomerce_services.entity.Customer;
import com.rookies.ecomerce_services.entity.Product;
import com.rookies.ecomerce_services.entity.Rating;
import com.rookies.ecomerce_services.exception.AppException;
import com.rookies.ecomerce_services.exception.ErrorCode;
import com.rookies.ecomerce_services.mapper.RatingMapper;
import com.rookies.ecomerce_services.repository.RatingRepository;
import com.rookies.ecomerce_services.service.CustomerService;
import com.rookies.ecomerce_services.service.ProductService;
import com.rookies.ecomerce_services.service.RatingService;
import com.rookies.ecomerce_services.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final ProductService productService;
    private final CustomerService customerService;



    @Override
    @Transactional
    public RatingResponse addReview(RequestRating rating) {
        Customer customer=customerService.findByUserId(rating.getUserId());
        Long productId = rating.getProductId();
        int ratingValue = rating.getRatingStar();
        Product product =productService.getByProductId(productId);
        Rating newRating = ratingMapper.toRating(rating);
        newRating.setCustomer(customer);
        newRating.setProduct(product);
        ratingRepository.save(newRating);
        productService.updateProductRating(productId, ratingValue);
        return toRatingResponse(newRating);
    }

    @Override
    @Transactional
    public RatingResponse updateReview(Long ratingId, RequestRating requestRating) {
        Rating rating=getRatingById(ratingId);
        ratingMapper.updateRating(rating, requestRating);
        ratingRepository.save(rating);
        Long productId = rating.getProduct().getProductId();
        int ratingValue = requestRating.getRatingStar();
        productService.updateProductRating(productId, ratingValue);
        return toRatingResponse(rating);
    }

    @Override
    @Transactional
    public void deleteReview(Long ratingId) {
        Rating rating = getRatingById(ratingId);
        ratingRepository.delete(rating);
    }

    @Override
    public RatingResponse getReviewById(Long ratingId) {
        Rating rating = getRatingById(ratingId);
        return toRatingResponse(rating);
    }

    @Override
    public List<RatingResponse> getAllReviewsByProductId(Long productId) {
        List<Rating> ratings = ratingRepository.findAllByProduct_ProductId(productId);
        return ratings.stream()
                .map(this::toRatingResponse).toList();
    }

    @Override
    public Rating getRatingById(Long ratingId) {
        return ratingRepository.findById(ratingId)
                .orElseThrow(() -> new AppException(ErrorCode.RATING_NOT_FOUND));
    }

    @Override
    public RatingResponse toRatingResponse(Rating rating) {
        Customer customer=rating.getCustomer();
        return RatingResponse.builder()
                .ratingId(rating.getRatingId())
                .productId(rating.getProduct().getProductId())
                .customerId(customer.getCustomerId())
                .customerName(customer.getFirstName() + " " + customer.getLastName())
                .ratingStar(rating.getRatingStar())
                .comment(rating.getComment())
                .createdOn(rating.getCreatedOn())
                .build();
    }
}
