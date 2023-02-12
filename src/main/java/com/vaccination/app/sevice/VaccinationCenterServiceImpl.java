package com.vaccination.app.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.entity.VaccinationCenter;
import com.vaccination.app.exception.VaccinationCenterException;
import com.vaccination.app.repository.VaccinationCenterRepository;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService {

	@Autowired
	private VaccinationCenterRepository vaccinationCenterRepository;
	

	@Override
	public List<VaccinationCenter> allVaccineCenters() throws VaccinationCenterException {
		
		List<VaccinationCenter> list = vaccinationCenterRepository.findAll();
		if (list.isEmpty())
			throw new VaccinationCenterException("No Vaccination Center Found...");
		return list;
	}

	@Override
	public VaccinationCenter getVaccineCenter(Integer centerid) throws VaccinationCenterException {
		
		Optional<VaccinationCenter> vc = vaccinationCenterRepository.findById(centerid);
		
		if(vc.isEmpty()) 
			throw new VaccinationCenterException("No vaccination center is found by the id : " + centerid);
		
		return vc.get();

	}

	@Override
	public VaccinationCenter addVaccineCenter(VaccinationCenter center) throws VaccinationCenterException {
		Optional<VaccinationCenter> vc = vaccinationCenterRepository.findById(center.getCenterId());

		if (vc.isPresent()) {
			throw new VaccinationCenterException("Vaccination center is present with the same Id");
		}
		return vaccinationCenterRepository.save(center);
	}

	@Override
	public VaccinationCenter updateVaccineCenter(VaccinationCenter center) throws VaccinationCenterException {
		
		Optional<VaccinationCenter> vc = vaccinationCenterRepository.findById(center.getCenterId());

		if (vc.isPresent()) {
			return vaccinationCenterRepository.save(center);
		} else
			throw new VaccinationCenterException("Vaccination center is not present with the same Id");
	}
	
	
	@Override
	public Boolean deleteVaccineCenter(Integer centerId) throws VaccinationCenterException {
		
		Optional<VaccinationCenter> vc = vaccinationCenterRepository.findById(centerId);

		if (vc.isPresent()) {
			vaccinationCenterRepository.delete(vc.get());
			return true;
		} else
			throw new VaccinationCenterException("Vaccination center is not present with the same Id");
	}
	

//	@Override
//	public boolean deleteVaccineCenter(VaccinationCenter center) throws VaccineCenterNotFoundException {
//		
//		Optional<VaccinationCenter> vc = vaccinationCenterRepository.findById(center.getCenterId());
//
//		if (vc.isPresent()) {
//			vaccinationCenterRepository.delete(center);
//			return true;
//		} else
//			throw new VaccineCenterNotFoundException("Vaccination center is not present with the same Id");
//	}

}
