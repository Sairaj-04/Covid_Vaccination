package com.vaccination.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VaccineRegistration {

	@Id
	@Size(min = 10  , max = 10 , message = "Please Enter valid mobile no ")
	@Pattern(regexp = "^[6-9][0-9]{10}$",message = "Phone number must be 10 digits")
	private String mobileNumber;

	private String dateofRegistration;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Member> memberList ;

	public VaccineRegistration() {
		super();
	}

	
	
	public VaccineRegistration(String mobileNumber, String dateofRegistration, List<Member> memberList) {
		super();
		this.mobileNumber = mobileNumber;
		this.dateofRegistration = dateofRegistration;
		this.memberList = memberList;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDateofRegistration() {
		return dateofRegistration;
	}

	public void setDateofRegistration(String dateofRegistration) {
		this.dateofRegistration = dateofRegistration;
	}


	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
	
	@Override
	public String toString() {
		return "VaccineRegistration [mobileNumber=" + mobileNumber + ", dateofRegistration=" + dateofRegistration + "]";
	}

	

}
