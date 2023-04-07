package myhibernate.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerID;

    @Column(name = "Username")
    private String username;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "City")
    private String city;
    
    @Column(name = "MobilePhone")
    private String mobilePhone;
    

    @Column(name = "Role")
    private String role;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Review> restaurantsReviewed;
    

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Reservation> tablesReserved;
    

    public Set<Review> getReview() {
        return restaurantsReviewed;
    }
    public void setReview(Set<Review> review) {
        this.restaurantsReviewed = review;
    }
    
    public Set<Reservation> getReservation() {
        return tablesReserved;
    }
    public void setReservation(Set<Reservation> tablesReserved) {
        this.tablesReserved = tablesReserved;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    

    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String MobilePhone) {
        this.mobilePhone = MobilePhone;
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}