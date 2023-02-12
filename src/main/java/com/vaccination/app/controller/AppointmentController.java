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

import com.vaccination.app.entity.Appointment;
import com.vaccination.app.exception.AppointmentException;
import com.vaccination.app.exception.MemberException;
import com.vaccination.app.exception.VaccinationCenterException;
import com.vaccination.app.exception.VaccineRegistrationException;
import com.vaccination.app.sevice.AppointmentService;

@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
//	public List<Appointment> getAllAppointment() throws AppointmentException;
//
//	public Appointment getAppointmentByBookingId(Long bookingId)throws AppointmentException;;
//
//	public Appointment addAppointment(Appointment app, Integer memId) throws VaccineRegistrationException, MemberNotFoundException, AppointmentException;
//
//	public Appointment updateAppointment(Appointment app)throws AppointmentException;;
//
//	public boolean deleteAppointment(Long bookingId)throws AppointmentException;
//	
//	public Appointment addAppointmentTest(Appointment app) ;
	
//	@PostMapping("/appointment")
//	public Appointment addAppointment(@RequestBody Appointment appointment) throws AppointmentException{
//		return this.appointmentService.addAppointmentTest(appointment);
//	}
	
	
	@PostMapping("/appointmentM/{memId}")
	public Appointment addAppointment(@RequestBody Appointment appointment, @PathVariable("memId") Integer memId) throws AppointmentException, VaccineRegistrationException, MemberException, VaccinationCenterException {
		return this.appointmentService.addAppointment(appointment, memId);
	}
	
	@GetMapping("appointmentById/{bookingId}")
	public Appointment getAppointment(@PathVariable("bookingId") Long bookingId) throws AppointmentException{
		return this.appointmentService.getAppointmentByBookingId(bookingId);
	}
	
	@GetMapping("/allAppointments")
	public List<Appointment> getAppointments() throws AppointmentException {
		return this.appointmentService.getAllAppointment();
	}
	
	@PutMapping("/appointment")
	public Appointment updateAppointment(@RequestBody Appointment appointment) throws AppointmentException {
		return this.appointmentService.updateAppointment(appointment);
	}
	
	@DeleteMapping("/appointment/{bookingId}")
	public boolean deleteAppointment(@PathVariable("bookingId") Long bookingId) throws AppointmentException {
		return this.appointmentService.deleteAppointment(bookingId);
	}
	
	
}
