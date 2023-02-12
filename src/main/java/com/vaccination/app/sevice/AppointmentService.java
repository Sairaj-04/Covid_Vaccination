package com.vaccination.app.sevice;

import java.util.List;

import com.vaccination.app.entity.Appointment;
import com.vaccination.app.exception.AppointmentException;
import com.vaccination.app.exception.MemberException;
import com.vaccination.app.exception.VaccinationCenterException;
import com.vaccination.app.exception.VaccineRegistrationException;



public interface AppointmentService {

	public List<Appointment> getAllAppointment() throws AppointmentException;

	public Appointment getAppointmentByBookingId(Long bookingId) throws AppointmentException;;

	public Appointment addAppointment(Appointment app, Integer memId) throws VaccineRegistrationException, AppointmentException, MemberException, VaccinationCenterException;

	public Appointment updateAppointment(Appointment app)throws AppointmentException;;

	public Boolean deleteAppointment(Long bookingId)throws AppointmentException;
	
//	public Appointment addAppointmentTest(Appointment app) ;
	
}

