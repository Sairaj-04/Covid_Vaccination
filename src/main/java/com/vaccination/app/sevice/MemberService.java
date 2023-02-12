package com.vaccination.app.sevice;

import java.util.List;

import com.vaccination.app.entity.Member;
import com.vaccination.app.exception.MemberException;


public interface MemberService {
	
    
	public List<Member> getAllMembers() throws MemberException;
     
	public Member getMemberById(Integer idcardid) throws MemberException;

	public Member getMemberByAdharNo(Long adharNo) throws MemberException;

	public Member addMemberbyMobileNo(Member member, String mobileNo) throws MemberException;

	public Member updateMember(Member member) throws MemberException;

	public boolean deleteMemberRecord(Integer memberId) throws MemberException;

	

}
