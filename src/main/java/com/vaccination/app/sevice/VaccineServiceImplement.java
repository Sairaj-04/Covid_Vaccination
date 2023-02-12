package com.vaccination.app.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.entity.Vaccine;
import com.vaccination.app.exception.VaccineException;

import com.vaccination.app.repository.VaccineRepository;

@Service
public class VaccineServiceImplement implements VaccineService {

	@Autowired
	private VaccineRepository vaccineRepository;

	@Override
	public Vaccine addVaccine(Vaccine vaccine) throws VaccineException {

		Vaccine savedVaccine = vaccineRepository.save(vaccine);

		return savedVaccine;
	}

	@Override
	public List<Vaccine> getAllVaccine() throws VaccineException {

		List<Vaccine> allVaccine = vaccineRepository.findAll();

		if (allVaccine.size() > 0) {
			return allVaccine;
		}

		throw new VaccineException("Vaccine not found..");
	}

	@Override
	public List<Vaccine> getVaccineByVccineName(String vaccineName) throws VaccineException {

		List<Vaccine> vaccinesbyName = vaccineRepository.findByVaccineName(vaccineName);

		if (vaccinesbyName.size() > 0) {

			return vaccinesbyName;
		}

		throw new VaccineException("This vaccine doesn't exist in vaccine list..");
	}
//	
//	@Override
//	public Vaccine getVaccineByVaccineName(String vaccineName) throws VaccineException {
//		Vaccine vaccine = vaccineRepository.findByVaccineName(vaccineName);
//		
//		if(vaccine == null) {
//			throw new VaccineException("Vaccine not exists");
//		}
//		return vaccine;
//	}

	@Override
	public Vaccine getVaccineById(Integer vaccineId) throws VaccineException {

		Optional<Vaccine> vaccine = vaccineRepository.findById(vaccineId);

		if(vaccine.isEmpty()) {

			throw new VaccineException("Vaccine can not find with this Id Number - " + vaccineId);
		}

		return vaccine.get();
	}

	@Override
	public Vaccine updateVaccine(Vaccine vaccine) throws VaccineException {

		Optional<Vaccine> updateVaccine = vaccineRepository.findById(vaccine.getVaccineId());

		if(updateVaccine.isEmpty()) {
			throw new VaccineException("vaccine not updated because vaccine not found");
		}

		return vaccineRepository.save(vaccine);

//		return updateVaccine.get();
	}

	@Override
	public Boolean deleteVaccine(Integer vaccineId) throws VaccineException {

		Optional<Vaccine> deletevaccine = vaccineRepository.findById(vaccineId);

		if (!deletevaccine.isPresent()) {
			throw new VaccineException("vaccine not deleted..");
		}

		vaccineRepository.delete(deletevaccine.get());

		return true;
	}

}
