package com.vaccination.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.app.entity.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
	
	public List<Vaccine> findByVaccineName(String vaccineName);
	
//	public Vaccine findByVaccineName(String vaccineName);

}
