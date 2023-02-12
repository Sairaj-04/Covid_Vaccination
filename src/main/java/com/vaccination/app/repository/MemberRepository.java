package com.vaccination.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.app.entity.IdCard;
import com.vaccination.app.entity.Member;
import com.vaccination.app.entity.VaccineRegistration;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByIdCard(IdCard idcard);

	public Member findByvaccineRegistration(VaccineRegistration vr);

}
