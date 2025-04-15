package com.rookies.ecomerce_services.controller;

import com.rookies.ecomerce_services.dto.request.RequestRating;
import com.rookies.ecomerce_services.dto.response.ApiResponse;
import com.rookies.ecomerce_services.dto.response.RatingResponse;
import com.rookies.ecomerce_services.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping()
    public ApiResponse<RatingResponse> addReview(@RequestBody RequestRating rating) {
        RatingResponse response = ratingService.addReview(rating);
        return ApiResponse.<RatingResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm đánh giá thành công!")
                .data(response)
                .build();
    }
    @PutMapping("/{ratingId}")
    public ApiResponse<RatingResponse> updateReview(@PathVariable Long ratingId, @RequestBody RequestRating requestRating) {
        RatingResponse response = ratingService.updateReview(ratingId, requestRating);
        return ApiResponse.<RatingResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật đánh giá thành công!")
                .data(response)
                .build();
    }
    @DeleteMapping("/{ratingId}")
    public ApiResponse<Void> deleteReview(@PathVariable Long ratingId) {
        ratingService.deleteReview(ratingId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa đánh giá thành công!")
                .build();
    }
    @GetMapping("/{ratingId}")
    public ApiResponse<RatingResponse> getReviewById(@PathVariable Long ratingId) {
        RatingResponse response = ratingService.getReviewById(ratingId);
        return ApiResponse.<RatingResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy đánh giá thành công!")
                .data(response)
                .build();
    }
    @GetMapping("/product/{productId}")
    public ApiResponse<List<RatingResponse>> getAllReviewsByProductId(@PathVariable Long productId) {
        List<RatingResponse> response = ratingService.getAllReviewsByProductId(productId);
        return ApiResponse.<List<RatingResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy tất cả đánh giá thành công!")
                .data(response)
                .build();
    }
}
