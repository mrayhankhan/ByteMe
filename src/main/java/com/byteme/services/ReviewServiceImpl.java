// ReviewServiceImpl.java
package com.byteme.services;

import com.byteme.models.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService {
    private List<Review> reviews = new ArrayList<>();

    @Override
    public void addReview(Review review) {
        reviews.add(review);
    }

    @Override
    public List<Review> getReviewsByMenuItem(String menuItemId) {
        return reviews.stream()
                .filter(review -> review.getMenuItemId().equals(menuItemId))
                .collect(Collectors.toList());
    }
}