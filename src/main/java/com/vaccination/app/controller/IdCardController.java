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

import com.vaccination.app.entity.IdCard;
import com.vaccination.app.exception.IdCardException;
import com.vaccination.app.sevice.IdCardService;

@RestController
public class IdCardController {
	
	@Autowired
	private IdCardService idCardService;
	
	@PostMapping("/idCard")
	public IdCard addIdCard(@RequestBody IdCard idCard) throws IdCardException {
		return this.idCardService.addIdCard(idCard);
	}
	
	@GetMapping("/idCard/{adharNo}")
	public IdCard getIdCard(@PathVariable("adharNo") Long adharNo) throws IdCardException {
		return this.idCardService.getIdCardByAdharNo(adharNo);
	}
	
	@GetMapping("/idCards")
	public List<IdCard> getAllIdCards() throws IdCardException {
		return this.idCardService.getAllIdCard();
	}
	
	@PutMapping("/idCard/")
	public IdCard updateIdCard(@RequestBody IdCard idCard) throws IdCardException {
		return this.idCardService.updateIdCard(idCard);
	}
	
	@DeleteMapping("/idCard/{adharNo}")
	public Boolean deleteIdCard(@PathVariable("adharNo") Long adharNo) throws IdCardException {
		return this.idCardService.deleteIdCardByAdharNo(adharNo);
	}
	
}
