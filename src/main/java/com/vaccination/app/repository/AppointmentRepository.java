package com.vaccination.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.app.entity.Appointment;
import com.vaccination.app.entity.Member;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	
  public List<Appointment> findByMember(Member member);

  Optional<Appointment> findByBookingId(Long bookingId);
  
}
