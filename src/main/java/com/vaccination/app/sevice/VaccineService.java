package com.vaccination.app.sevice;

import java.util.List;

import com.vaccination.app.entity.Vaccine;
import com.vaccination.app.exception.VaccineException;


public interface VaccineService {
	
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineException;
	
	public List<Vaccine> getAllVaccine()throws VaccineException;
	
	public List<Vaccine> getVaccineByVccineName(String vaccineName) throws VaccineException;
	
	public Vaccine getVaccineById(Integer vaccineId)throws VaccineException;
	
	public Vaccine updateVaccine(Vaccine vaccine)throws VaccineException;
	
	public Boolean deleteVaccine(Integer vaccineId) throws VaccineException;

//	public Vaccine getVaccineByVaccineName(String vaccineName)throws VaccineException;

}
