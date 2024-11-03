// ReviewService.java
package com.byteme.services;

import com.byteme.models.Review;
import java.util.List;

public interface ReviewService {
    void addReview(Review review);
    List<Review> getReviewsByMenuItem(String menuItemId);
}