package com.vaccination.app.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.entity.IdCard;
import com.vaccination.app.exception.IdCardException;

import com.vaccination.app.repository.IdCardRepository;


@Service
public class IdCardServiceImpl implements IdCardService {

	@Autowired
	private IdCardRepository idCardRepository;
	
	

	@Override
	public IdCard getIdCardByAdharNo(Long adharNumber) throws IdCardException {

		Optional<IdCard> idcard = idCardRepository.findByAdharNo(adharNumber);
		if (idcard.isEmpty())
			throw new IdCardException("IdCard not found with the adharNo : " + adharNumber);
		else
			return idcard.get();
	}

	@Override
	public IdCard addIdCard(IdCard idCard) throws IdCardException {
	
		Optional<IdCard> idCard1 = this.idCardRepository.findByAdharNo(idCard.getAdharNo());
		if (!idCard1.isEmpty())
			throw new IdCardException("Id card already exist with adhar number : " + idCard.getAdharNo());
		else
			return idCardRepository.save(idCard);
	}

	@Override
	public IdCard updateIdCard(IdCard idCard) throws IdCardException {
		Optional<IdCard> idCard1 = this.idCardRepository.findByAdharNo(idCard.getAdharNo());
		
		if(idCard1.isEmpty())
			throw new IdCardException("This ID card is not found");
		
		return idCardRepository.save(idCard);
	}

	@Override
	public List<IdCard> getAllIdCard() throws IdCardException {
		List<IdCard> listOfIdCards = this.idCardRepository.findAll();
		
		if(listOfIdCards.isEmpty())
			throw new IdCardException("IdCards not found");
		
		return listOfIdCards;
	}

	@Override
	public Boolean deleteIdCardByAdharNo(Long adharNo) throws IdCardException {
		Optional<IdCard> idCard1 = this.idCardRepository.findByAdharNo(adharNo);
		
		if(idCard1.isEmpty())
			throw new IdCardException("ID card is not found for this adhar number");
		
		this.idCardRepository.delete(idCard1.get());
		return true;
	}

}
