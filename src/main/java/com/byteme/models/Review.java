// Review.java
package com.byteme.models;

import java.util.Date;

public class Review {
    private String id;
    private String menuItemId;
    private String customerId;
    private int rating;
    private String comment;
    private Date reviewDate;

    public Review(String id, String menuItemId, String customerId, int rating, String comment) {
        this.id = id;
        this.menuItemId = menuItemId;
        this.customerId = customerId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = new Date();
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMenuItemId() { return menuItemId; }
    public void setMenuItemId(String menuItemId) { this.menuItemId = menuItemId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Date getReviewDate() { return reviewDate; }

    @Override
    public String toString() {
        return String.format("Rating: %d/5 - %s (by Customer: %s on %s)", 
            rating, comment, customerId, reviewDate);
    }
}