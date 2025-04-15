package com.rookies.ecomerce_services.mapper;

import com.rookies.ecomerce_services.dto.request.RequestRating;
import com.rookies.ecomerce_services.dto.response.RatingResponse;
import com.rookies.ecomerce_services.entity.Rating;
import jakarta.mail.MailSessionDefinition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    Rating toRating(RequestRating requestRating);

    void updateRating(@MappingTarget Rating rating, RequestRating requestRating);
    RatingResponse toRatingResponse(Rating rating);
    List<RatingResponse> toRatingResponseList(List<Rating> ratings);
}
