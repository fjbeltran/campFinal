package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.Offer;


public interface OfferRepository extends JpaRepository<Offer,Integer> {

	@Query("select o from Offer o where o.expDate > CURRENT_DATE")
	List<Offer> findOfferNotExpired ();
	
	
	
}
