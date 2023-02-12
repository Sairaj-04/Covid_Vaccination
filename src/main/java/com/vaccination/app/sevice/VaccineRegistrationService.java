package com.vaccination.app.sevice;

import java.util.List;

import com.vaccination.app.entity.Member;
import com.vaccination.app.entity.VaccineRegistration;
import com.vaccination.app.exception.VaccineRegistrationException;

public interface VaccineRegistrationService {
           
	public List<VaccineRegistration> allVaccineRegistration() throws VaccineRegistrationException;
	
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;

	public VaccineRegistration getVaccineRegistration(String mobileNumber) throws VaccineRegistrationException;

	public List<Member> getAllMember(String mobileNumber) throws VaccineRegistrationException ;

	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;

	public Boolean deleteVaccineRegistration(String mobileNo) throws VaccineRegistrationException;
}   
