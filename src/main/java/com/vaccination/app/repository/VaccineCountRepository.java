package com.vaccination.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.app.entity.VaccineCount;

@Repository
public interface VaccineCountRepository extends JpaRepository<VaccineCount, Integer>{

}
