package com.vaccination.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaccination.app.entity.Member;
import com.vaccination.app.entity.VaccineRegistration;
import com.vaccination.app.exception.VaccineRegistrationException;
import com.vaccination.app.sevice.VaccineRegistrationService;

@RestController
public class VaccineRegistrationController {

	@Autowired
	private VaccineRegistrationService vaccineRegistrationService;
	
//	public List<VaccineRegistration> allVaccineRegistration() throws VaccineRegistrationException;
//	
//	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;
//
//	public VaccineRegistration getVaccineRegistration(String mobileNumber) throws VaccineRegistrationException;
//
//	public List<Member> getAllMember(String mobileNumber) throws VaccineRegistrationException ;
//
//	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;
//
//	public Boolean deleteVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException;
	
	@PostMapping("/vaccineRegistration")
	public VaccineRegistration addVaccineRegistration(@Valid @RequestBody VaccineRegistration vaccineRegistration) throws VaccineRegistrationException {
		return this.vaccineRegistrationService.addVaccineRegistration(vaccineRegistration);
	}
	
	@GetMapping("/vaccineRegistration/{mobileNo}")
	public VaccineRegistration getVaccineRegistrationByMobileNo(@PathVariable("mobileNo") String mobileNo) throws VaccineRegistrationException {
		return this.vaccineRegistrationService.getVaccineRegistration(mobileNo);
	}
	
	@GetMapping("/allMembers/{mobileNo}")
	public List<Member> getAllMembersByMobileNo(@PathVariable("mobileNo") String mobleNo) throws VaccineRegistrationException {
		return this.vaccineRegistrationService.getAllMember(mobleNo);
	}
	
	@GetMapping("/vaccineRegistrations")
	public List<VaccineRegistration> getVaccineRegistrations() throws VaccineRegistrationException {
		return this.vaccineRegistrationService.allVaccineRegistration();
	}
	
	@PutMapping("/vaccineRegistration")
	public VaccineRegistration updateVaccineRegistration(@RequestBody VaccineRegistration vaccineRegistration) throws VaccineRegistrationException {
		return this.vaccineRegistrationService.updateVaccineRegistration(vaccineRegistration);
	}
	
	@DeleteMapping("/vaccineRegistration/{mobileNo}")
	public Boolean deleteVaccineRegistration(@PathVariable("mobileNo") String mobileNo) throws VaccineRegistrationException {
		return this.vaccineRegistrationService.deleteVaccineRegistration(mobileNo);
	}
}
