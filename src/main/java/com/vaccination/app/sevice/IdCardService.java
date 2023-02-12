package com.vaccination.app.sevice;

import java.util.List;

import com.vaccination.app.entity.IdCard;
import com.vaccination.app.exception.IdCardException;


public interface IdCardService {
	

	public IdCard getIdCardByAdharNo(Long adharNumber) throws IdCardException;

	public IdCard addIdCard(IdCard idCard) throws IdCardException;
	
	public IdCard updateIdCard(IdCard idCard) throws IdCardException;
	
	public List<IdCard> getAllIdCard() throws IdCardException;
	
	public Boolean deleteIdCardByAdharNo(Long adharNo) throws IdCardException;

}
