
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resID;

    @Column(name = "Name")
    private String name;
    
    @Column(name = "Type")
    private String type;
    
    @Column(name = "Description")
    private String description;

    @Column(name = "Menu")
    private String menu;
    
    @Column(name = "ContactPhone")
    private String contactPhone;
    
    @Column(name = "City")
    private String city;
    
    @Column(name = "Address")
    private String address;
    
    @Column(name = "Tables")
    private int tables;
    
    @Column(name = "Capacity")
    private int capacity;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Review> review;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Reservation> tablesReserved;
    

    public Set<Reservation> getReservation() {
        return tablesReserved;
    }
    public void setReservation(Set<Reservation> reservation) {
        this.tablesReserved = reservation;
    }
    
    
    public Set<Review> getReview() {
        return review;
    }
    public void setReview(Set<Review> review) {
        this.review = review;
    }
    
    public int getResID() {
        return resID;
    }
    public void setResID(int ResID) {
        this.resID = ResID;
    }
    public int getTables() {
        return tables;
    }
    public void setTables(int tables) {
        this.tables = tables;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type= type;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}