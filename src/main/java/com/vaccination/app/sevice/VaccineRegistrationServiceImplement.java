package com.vaccination.app.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.entity.Member;
import com.vaccination.app.entity.VaccineRegistration;
import com.vaccination.app.exception.VaccineRegistrationException;
import com.vaccination.app.repository.VaccineRegistrationRepository;

@Service
public class VaccineRegistrationServiceImplement implements VaccineRegistrationService {

	@Autowired
	private VaccineRegistrationRepository vaccineRegistrationRepository;

	@Override
	public List<VaccineRegistration> allVaccineRegistration() throws VaccineRegistrationException {

		List<VaccineRegistration> allRegistration = vaccineRegistrationRepository.findAll();

		if (allRegistration.isEmpty()) {
			throw new VaccineRegistrationException("Registration of  vaccine doesn't exist..");
		}

		return allRegistration;
	}

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration registration) throws VaccineRegistrationException {
		Optional<VaccineRegistration> vr = vaccineRegistrationRepository.findById(registration.getMobileNumber());
		if(!vr.isEmpty()) {
			throw new VaccineRegistrationException("Mobile number is already registered");
		}
		return vaccineRegistrationRepository.save(registration);
	}

	@Override
	public VaccineRegistration getVaccineRegistration(String mobileNumber) throws VaccineRegistrationException {

		Optional<VaccineRegistration> vr = vaccineRegistrationRepository.findById(mobileNumber);

		if (vr.isPresent()) {
			return vr.get();
		}
		throw new VaccineRegistrationException("Vaccine Registration not found with this Number..");
	}

	@Override
	public List<Member> getAllMember(String mobileNumber) throws VaccineRegistrationException {

		Optional<VaccineRegistration> vr = vaccineRegistrationRepository.findById(mobileNumber);

		if (vr.isEmpty()) {
			throw new VaccineRegistrationException("Member Not found with this number..");
		}

		VaccineRegistration vaccineRegistration = vr.get();

		return vaccineRegistration.getMemberList();

	}

	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration registration)
			throws VaccineRegistrationException {

		Optional<VaccineRegistration> vr = vaccineRegistrationRepository.findById(registration.getMobileNumber());

		if (vr.isEmpty()) {
			throw new VaccineRegistrationException("Vaccine registration doesn't exist..");
		}

		vaccineRegistrationRepository.save(registration);
		return vr.get();
	}

	@Override
	public Boolean deleteVaccineRegistration(String mobileNo) throws VaccineRegistrationException {

		Optional<VaccineRegistration> vr = vaccineRegistrationRepository.findById(mobileNo);

		if (!vr.isPresent()) {
			throw new VaccineRegistrationException("Vaccine registration can not delete because its not exist..");

		}

		vaccineRegistrationRepository.delete(vr.get());

		return true;
	}

}
