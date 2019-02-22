package org.springframework.samples.petclinic.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Offer;
import org.springframework.samples.petclinic.repository.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferService{

@Autowired
private OfferRepository offerRepository;

public List<Offer> findOfferNotExpired(){
	return offerRepository.findOfferNotExpired();
}
public List<Offer> findAll(){
	return offerRepository.findAll();
}
public Offer findOne(int id) {
	return offerRepository.findOne(id);
}
public boolean save(Offer offer) {
	if(validate(offer)) {
	offerRepository.save(offer);
	return true;
	}
	else {
		return false;
	}
}
public void delete(Offer offer) {
	offerRepository.delete(offer);
}
public void deleteById(int id) {
	offerRepository.delete(id);
}

public boolean validate(Offer offer) {
	boolean res = true;
	if(offer.getTitle().length()>30 || offer.getTitle()==null)
		res=false;
	else if(offer.getDetail().length()>250 || offer.getDetail()==null)
		res=false;
	else if(offer.getDiscount()<0 || offer.getDiscount()>100)
		
		res=false;
	else if(offer.getExpDate().before(new Date()) || offer.getExpDate()==null)
		res=false;
	return res;
}
}
