package myhibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Worker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int workerID;// an uparxei
	
	@Column(name = "Classification")
    private String classification;
	
	@Column(name = "Worker")
    private String worker;
	
	@Column(name = "Reg.Rate")
    private String RegRate;
	
	@Column(name = "OverRate")
    private String overRate;
	
	@Column(name = "Over2Rate")
    private String over2Rate;
	
	public String getClassification()	{
		return classification;
	}
	public void setClassification()	{
		
	}
	//....
}
