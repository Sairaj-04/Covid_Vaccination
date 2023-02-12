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

import com.vaccination.app.entity.Vaccine;
import com.vaccination.app.exception.VaccineException;
import com.vaccination.app.sevice.VaccineService;

@RestController
public class VaccineController {
	
	@Autowired
	private VaccineService vaccineService;
	
//	public Vaccine addVaccine(Vaccine vaccine) throws VaccineNotFoundException;
//	
//	public List<Vaccine> getAllVaccine()throws VaccineNotFoundException;
//	
//	public List<Vaccine> getVaccineByVccineName(String vaccineName) throws VaccineNotFoundException;
//	
//	public Vaccine getVaccineById(Integer vaccineId)throws VaccineNotFoundException;
//	
//	public Vaccine updateVaccine(Vaccine vaccine)throws VaccineNotFoundException;
//	
//	public Boolean deleteVaccine(Integer vaccineId) throws VaccineNotFoundException;
	
	@PostMapping("/vaccine")
	public Vaccine addVaccine(@RequestBody Vaccine vaccine) throws VaccineException {
		return this.vaccineService.addVaccine(vaccine);
	}
	
//	@GetMapping("vaccine/{vaccineId}")
//	public Vaccine getVaccineByVaccineId(@PathVariable("vaccineId") Integer vaccineId) throws VaccineException {
//		return this.vaccineService.getVaccineById(vaccineId);
//	}
//	
	@GetMapping("vaccine/{vaccineName}")
	public List<Vaccine> getVaccineByVaccineName(@PathVariable("vaccineName") String vaccineName) throws VaccineException {
		return this.vaccineService.getVaccineByVccineName(vaccineName);
	}
	
	@GetMapping("vaccineI/{vaccineId}")
	public Vaccine getVaccineByVaccineId(@PathVariable("vaccineId") Integer vaccineId) throws VaccineException {
		return this.vaccineService.getVaccineById(vaccineId);
	}
	
//	@GetMapping("vaccine/{vaccineName}")
//	public Vaccine getVaccineByVaccineName(@PathVariable("vaccineName") String vaccineName) throws VaccineException {
//		return this.vaccineService.getVaccineByVaccineName(vaccineName);
//	}
	
	@GetMapping("vaccines")
	public List<Vaccine> getAllVaccines() throws VaccineException {
		return this.vaccineService.getAllVaccine();
	}
	
	@PutMapping("/vaccine")
	public Vaccine updateVaccine(@RequestBody Vaccine vaccine) throws VaccineException {
		return this.vaccineService.updateVaccine(vaccine);
	}
	
	@DeleteMapping("/vaccine/{vaccineId}")
	public Boolean deleteVaccine(@PathVariable("vaccineId") Integer vaccineId) throws VaccineException {
		return this.vaccineService.deleteVaccine(vaccineId);
	}
}
