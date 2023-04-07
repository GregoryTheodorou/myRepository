package myhibernate.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Embeddable
public class ReservationId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "FK_CustomerID")
	   private int customerID;
	 
	 @Column(name = "FK_ResID")
	 private int resID;
	 
	 /*@Column(name = "ReservationID")
	 private int reservationID;*/
	
	 public ReservationId() {
	 }
	
	 public ReservationId(int customerID, int rID) {
	     this.customerID = customerID;
	     this.resID = rID;
	 }
	 
	 public int getCustomerID() {
	     return  customerID;
	 }
	
	 public int getResID() {
	     return resID;
	 }
	 
	 public void setCustomerID(int customerID) {
	     this.customerID=customerID;
	 }
	
	 public void setResID(int ResID) {
	     this.resID=ResID;
	 }
	
	 @Override
	 public boolean equals(Object o) {
	     if (this == o) return true;
	     if (!(o instanceof ReservationId)) return false;
	     ReservationId that = (ReservationId) o;
	     return Objects.equals(getCustomerID(), that.getCustomerID()) &&
	             Objects.equals(getResID(), that.getResID());
	 }
	
	 @Override
	 public int hashCode() {
	     return Objects.hash(getCustomerID(), getResID());
	 }
	
	 
}
