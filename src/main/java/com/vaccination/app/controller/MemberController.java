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

import com.vaccination.app.entity.Member;
import com.vaccination.app.exception.MemberException;
import com.vaccination.app.sevice.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/member/{mobileNo}")
	public Member addMember(@RequestBody Member member, @PathVariable("mobileNo") String mobileNo) throws MemberException {
		return this.memberService.addMemberbyMobileNo(member, mobileNo);
	}
	
	@GetMapping("member/{idCardId}")
	public Member getMemberByIdCardId(@PathVariable("idCardId") Integer idCardId) throws MemberException {
		return this.memberService.getMemberById(idCardId);
	}
	
	@GetMapping("member/{adharNo}")
	public Member getMemberByAdharNO(@PathVariable("adharNo") Long adharNo) throws MemberException {
		return this.memberService.getMemberByAdharNo(adharNo);
	}
	
	@GetMapping("/allMembers")
	public List<Member> getMembers() throws MemberException {
		return this.memberService.getAllMembers();
	}
	
	@PutMapping("/member")
	public Member updateMember(@RequestBody Member member) throws MemberException {
		return this.memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{memberId}")
	public boolean deleteMember(@PathVariable("memberId") Integer memberId) throws MemberException {
		return this.memberService.deleteMemberRecord(memberId);
	}
}
