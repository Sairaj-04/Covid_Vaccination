package com.vaccination.app.sevice;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.entity.Appointment;
import com.vaccination.app.entity.Member;
import com.vaccination.app.entity.VaccinationCenter;
import com.vaccination.app.entity.VaccineRegistration;
import com.vaccination.app.exception.AppointmentException;
import com.vaccination.app.exception.MemberException;
import com.vaccination.app.exception.VaccinationCenterException;
import com.vaccination.app.exception.VaccineRegistrationException;
import com.vaccination.app.repository.AppointmentRepository;



@Service
public class AppointmentServiceImpl implements AppointmentService{

	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private VaccineRegistrationService vaccineRegistrationService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private VaccinationCenterService vaccinationCenterService;
	
	


	@Override
	public List<Appointment> getAllAppointment() throws AppointmentException {
		
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty())
			throw new AppointmentException("No appointment found");
		else
			return appointments;
	}



	@Override
	public Appointment getAppointmentByBookingId(Long bookingId) throws AppointmentException {
		Optional<Appointment> opt =  appointmentRepository.findByBookingId(bookingId) ;
		if(opt.isEmpty())
			throw new AppointmentException("Appointment not found by same booking id!");
		else 
			return opt.get();
	}



	@Override
	public Appointment addAppointment(Appointment app, Integer memId) throws AppointmentException, VaccineRegistrationException, MemberException, VaccinationCenterException {
		
		VaccineRegistration reg = vaccineRegistrationService.getVaccineRegistration(app.getMobileNumber()); // Finding vaccine registration using entered appointment's mobile no in database
		
		if (reg == null)                                                                                    // If vaccine registration is not present in database
			throw new VaccineRegistrationException("First do the registration...");                         // Register first
		else {                                                                                              // If vaccine registration is present in database
			
			List<Member> list = reg.getMemberList();                                                        // Getting members list from registrations
		
			for (Member m : list) {
				if (m.getMemberId() == memId) {                                                             // If registered member id and entered member id are equal -> Give appointment to him
					app.setMember(m);                                                                       // Set that member to that appointment
				    app.setDateofBooking(LocalDate.now());                                                  // Set today's date of booking to that appointment
					app.setBookingStatus(true);                                                             // Set booking status true
					
					Integer id = app.getVaccinationCenter().getCenterId();                                  // Get vaccination center id from appointment
					
					
					VaccinationCenter vaccinationCenter = vaccinationCenterService.getVaccineCenter(id);    // Get vaccination center for that id
					
					
					app.setVaccinationCenter(vaccinationCenter);                                            // Set that vaccination center to that appointment
					
					
					Appointment a = appointmentRepository.save(app);                                        // Saving appointment in database
					m.getListofappointments().add(a);                                                       // Get appointment list of that member and add this new appointment to it
					memberService.updateMember(m);                                                          // Update that member
					
					return a;                                                                               // return appointment
				}
			}
			throw new AppointmentException("Member not found...");                                          // If entered memId's member is not present in registered member list 
		}
		
	}



	@Override
	public Appointment updateAppointment(Appointment app) throws AppointmentException {
		
		Optional<Appointment> appointment = appointmentRepository.findByBookingId(app.getBookingId());
		
		if(appointment.isEmpty())
			throw new AppointmentException("Appointment not found!");
		
		appointment.get().setDateofBooking(app.getDateofBooking());
		appointment.get().setVaccinationCenter(app.getVaccinationCenter());
		appointment.get().setSlot(app.getSlot());
		return appointmentRepository.save(appointment.get());
		
	}



	@Override
	public Boolean deleteAppointment(Long bookingId) throws AppointmentException {
		
		Optional<Appointment> appointment = appointmentRepository.findByBookingId(bookingId);
		
		if(appointment.isEmpty())
			throw new AppointmentException("Appointment not found");
		
		appointmentRepository.delete(appointment.get());
		
		return true;
	}



//	@Override
//	public Appointment addAppointmentTest(Appointment app) {
//		return appointmentRepository.save(app);
//	}

	
	
}
