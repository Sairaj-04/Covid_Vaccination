package com.vaccination.app.sevice;

import java.util.List;

import com.vaccination.app.entity.VaccinationCenter;
import com.vaccination.app.exception.VaccinationCenterException;


public interface VaccinationCenterService {

	public List<VaccinationCenter> allVaccineCenters() throws VaccinationCenterException;

	public VaccinationCenter getVaccineCenter(Integer centerid) throws VaccinationCenterException;

	public VaccinationCenter addVaccineCenter(VaccinationCenter center) throws VaccinationCenterException;

	public VaccinationCenter updateVaccineCenter(VaccinationCenter center) throws VaccinationCenterException;

	public Boolean deleteVaccineCenter(Integer centerId) throws VaccinationCenterException;
}