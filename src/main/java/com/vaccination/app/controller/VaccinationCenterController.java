package com.vaccination.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaccination.app.entity.VaccinationCenter;
import com.vaccination.app.exception.VaccinationCenterException;
import com.vaccination.app.sevice.VaccinationCenterService;

@RestController
public class VaccinationCenterController {
	
	@Autowired
	private VaccinationCenterService vaccinationCenterService;
	
	
	@PostMapping("/vaccinationCenter")
	public VaccinationCenter addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter) throws VaccinationCenterException {
		return this.vaccinationCenterService.addVaccineCenter(vaccinationCenter);
	}
	
	@GetMapping("/vaccinationCenter/{centerId}")
	public VaccinationCenter getVaccinationCenter(@PathVariable("centerId") Integer centerId) throws VaccinationCenterException {
		return this.vaccinationCenterService.getVaccineCenter(centerId);
	}
	
	@GetMapping("/allVaccinationCenters")
	public List<VaccinationCenter> getAllVaccinationCenters() throws VaccinationCenterException {
		return this.vaccinationCenterService.allVaccineCenters();
	}
	
	@PutMapping("/vaccinationCenter")
	public VaccinationCenter updateVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter) throws VaccinationCenterException {
		return this.vaccinationCenterService.updateVaccineCenter(vaccinationCenter);
	}
	
	@DeleteMapping("/vaccinationCenter/{centerId}")
	public boolean deleteVaccinationCenter(@PathVariable("centerId") Integer centerId) throws VaccinationCenterException {
		return this.vaccinationCenterService.deleteVaccineCenter(centerId);
	}
}
