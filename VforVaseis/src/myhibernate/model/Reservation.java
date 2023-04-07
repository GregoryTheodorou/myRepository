package myhibernate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	ReservationId reservationId;
	 
	@Column(name = "ReservationDateTime")
    private LocalDateTime reservationDateTime;
	
	@Column(name = "GuestsNumber")
    private String guestsNumber;
	
	@ManyToOne
    @MapsId("customerID")
    @JoinColumn(name = "FK_CustomerID")
    User customer;
	 
	@ManyToOne//dn 3erw
    @MapsId("resID")
    @JoinColumn(name = "FK_ResID")
    Restaurant restaurant;
	
    
	public ReservationId getReservationId() {
        return reservationId;
    }
    public void setReservationId(ReservationId reservationId) {
        this.reservationId=reservationId;
    }
    
    public User getUser() {
        return customer;
    }
    public void setUser(User user) {
        this.customer=user;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    /*
    public Tables getTable() {
        return table;
    }
    
    public void setTable(Tables table) {
        this.table = table;
    }
    */
    public String getGuestsNumber() {
        return guestsNumber;
    }
    
    public void setGuestsNumber(String guestsNumber) {
        this.guestsNumber = guestsNumber;
    }
    public LocalDateTime getRDateTime() {
        return reservationDateTime;
    }
    
    public void setRDateTime(LocalDateTime rDateTime) {
        this.reservationDateTime = rDateTime;
    }
    
    
    
    
}
