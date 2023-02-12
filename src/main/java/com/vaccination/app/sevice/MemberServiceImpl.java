package com.vaccination.app.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.entity.Appointment;
import com.vaccination.app.entity.IdCard;
import com.vaccination.app.entity.Member;
import com.vaccination.app.entity.Vaccine;
import com.vaccination.app.entity.VaccineRegistration;
import com.vaccination.app.exception.MemberException;

import com.vaccination.app.repository.AppointmentRepository;
import com.vaccination.app.repository.IdCardRepository;
import com.vaccination.app.repository.MemberRepository;
import com.vaccination.app.repository.VaccineRegistrationRepository;
import com.vaccination.app.repository.VaccineRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	IdCardRepository idCardRepository;

	@Autowired
	VaccineRegistrationRepository vaccineRegistrationRepository;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	VaccineRepository vaccineRepository;

	@Override
	public List<Member> getAllMembers() throws MemberException {
		List<Member> member = memberRepository.findAll();
		if (member.size() > 0)
			return member;
		else
			throw new MemberException("No member is Registered");
	}
	

	@Override
	public Member getMemberById(Integer idcardId) throws MemberException {
		Optional<IdCard> idcard = idCardRepository.findById(idcardId);
		if (idcard.isPresent()) {
			Member member = memberRepository.findByIdCard(idcard.get());
			if (member != null) {
				List<Appointment> appointment = appointmentRepository.findByMember(member);
				member.setListofappointments(appointment);

				return member;
			} else
				throw new MemberException("Member not found  with this ID:" + idcardId);
		} else
			throw new MemberException("Member not found  with this ID:" + idcardId);
	}
	

	@Override
	public Member getMemberByAdharNo(Long adharNo) throws MemberException {

		Optional<IdCard> idcard = idCardRepository.findByAdharNo(adharNo);

		if (idcard != null) {
			Member member = memberRepository.findByIdCard(idcard.get());
			if (member != null)
				return member;
			else
				throw new MemberException("Member not available with this adhar no: " + adharNo);
		} else
			throw new MemberException("Member not available with this adhar no: " + adharNo);
	}
	

	@Override
	public Member addMemberbyMobileNo(Member member, String mobileNo) throws MemberException {
		Optional<VaccineRegistration> vacc = vaccineRegistrationRepository.findById(mobileNo);  // Finding vaccine registration using entered mobile no in database
		if (vacc.isPresent()) {                                                                 // If vaccine registration is present in database
			Optional<IdCard> idcard = idCardRepository.findById(member.getIdCard().getId());    // Finding entered members Id card in database
			if (idcard.isEmpty()) {                                                             // If Id card is not present in database
				member.setVaccineRegistration(vacc.get());                                      // Setting vaccine registration for that new member with entered mobile no
				member.setDose1date(null);                                                      // Setting dose1 date null
				member.setDose2date(null);                                                      // Setting dose1 date null
				member.setDose1status(false);                                                   // Setting dose1 status false
				member.setDose2status(false);                                                   // Setting dose1 status false
				return memberRepository.save(member);                                           // Saving member in database
			} else
				throw new MemberException("Member is already register");                       // If Id Card is present in database
		} else                                                                                 
			throw new MemberException("This mobile number is not registered :" + mobileNo);    // If registration not present with given mobile no in database
	}
	

	@Override
	public Member updateMember(Member member) throws MemberException {
		Optional<Member> mem = memberRepository.findById(member.getMemberId());
		if (mem.isPresent()) {

			Member OldMember = mem.get();
			if (member.getIdCard() != null) {
				IdCard id = OldMember.getIdCard();

				if (member.getIdCard().getDob() != null)
					id.setDob(member.getIdCard().getDob());

				if (member.getIdCard().getCity() != null)
					id.setCity(member.getIdCard().getCity());

				if (member.getIdCard().getGender() != null)
					id.setGender(member.getIdCard().getGender());

				if (member.getIdCard().getAddress() != null)
					id.setAddress(member.getIdCard().getAddress());

				if (member.getIdCard().getPinCode() != null)
					id.setPinCode(member.getIdCard().getPinCode());

				if (member.getIdCard().getState() != null)
					id.setState(member.getIdCard().getState());

				if (member.getIdCard().getAdharNo() != null) 
					id.setAdharNo(member.getIdCard().getAdharNo());
			}

			if (member.getDose1date() != null)
				OldMember.setDose1date(member.getDose1date());

			if (member.getDose2date() != null)
				OldMember.setDose2date(member.getDose2date());

			if (member.getDose1status() != null)
				OldMember.setDose1status(member.getDose1status());

			if (member.getDose1status() != null)
				OldMember.setDose2status(member.getDose2status());

			if (member.getVaccine() != null) {
				Vaccine vaccine = OldMember.getVaccine();
				vaccine.setVaccineName(member.getVaccine().getVaccineName());

				vaccine.setDescription(member.getVaccine().getDescription());

				vaccine.setVaccineCount(member.getVaccine().getVaccineCount());
			}

			if (member.getVaccineRegistration() != null) {
				VaccineRegistration vr = OldMember.getVaccineRegistration();

				vr.setDateofRegistration(member.getVaccineRegistration().getDateofRegistration());

				vr.setMobileNumber(member.getVaccineRegistration().getMobileNumber());
			}

			return memberRepository.save(mem.get());
		} else
			throw new MemberException("Member not available with this details :" + member);
	}
	

	@Override
	public boolean deleteMemberRecord(Integer memberId) throws MemberException {
		Optional<Member> mem = memberRepository.findById(memberId);
		if (mem.isPresent()) {
			Member oldMember = mem.get();

			if (oldMember.getVaccineRegistration() != null)
				vaccineRegistrationRepository.delete(oldMember.getVaccineRegistration());

			if (oldMember.getIdCard() != null)
				idCardRepository.delete(oldMember.getIdCard());

			if (oldMember.getListofappointments() != null)
				appointmentRepository.deleteAll(oldMember.getListofappointments());

			if (oldMember.getVaccine() != null)
				vaccineRepository.delete(oldMember.getVaccine());

			memberRepository.delete(mem.get());
			return true;
		} else
			throw new MemberException("Member not available with this details :" + mem.get());
	}

}
