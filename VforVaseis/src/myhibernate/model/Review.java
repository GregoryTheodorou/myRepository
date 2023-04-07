package myhibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



@Entity
@Table(name = "review")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    
    @EmbeddedId
    ReviewId reviewId;
    
    @Column(name = "Review")
    private String review;
    
    @ManyToOne
    @MapsId("customerID")
    @JoinColumn(name = "FK_CustomerID")
    User customer;
    
    @ManyToOne
    @MapsId("resID")
    @JoinColumn(name = "FK_ResID")
    Restaurant restaurant;
    
    
    public ReviewId getReviewId() {
        return reviewId;
    }
    public void setReviewId(ReviewId reviewId) {
        this.reviewId=reviewId;
    }
    public User getUser() {
        return customer;
    }
    public void setUser(User user) {
        this.customer=user;
    }
    
    /*
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }*/
    
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    public String getReview() {
        return review;
    }
    
    public void setReview(String review) {
        this.review = review;
    }
    
}