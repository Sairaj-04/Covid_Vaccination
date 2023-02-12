package com.vaccination.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.app.entity.IdCard;


@Repository
public interface IdCardRepository extends JpaRepository<IdCard,Integer> {

	public Optional<IdCard> findByAdharNo(Long adharNumber);
	
	
}
