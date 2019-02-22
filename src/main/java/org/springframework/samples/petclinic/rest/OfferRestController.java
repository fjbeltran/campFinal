package org.springframework.samples.petclinic.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Offer;
import org.springframework.samples.petclinic.service.OfferService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/offers")
public class OfferRestController {
	@Autowired
	private OfferService offerService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Offer> getOffers()
	{
		return offerService.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Offer getOfferById(@PathVariable(value="id") int id)
	{
		
		return offerService.findOne(id);
	}
	
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity saveOffer(@RequestBody Offer entity)
	{
		if(entity.getId() != null)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else if(!offerService.validate(entity))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		else
		{
			offerService.save(entity);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateOffer(@PathVariable(value="id") int id, @RequestBody Offer entity)
	{
		if(entity == null)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		else if(!offerService.validate(entity))
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		else
		{
			
			if(offerService.findOne(id) == null)
			{
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); //update de uno que no existe
			}
			else
			{
				entity.setId(id);
				offerService.save(entity);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
			

		}
		
	}
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteById(@PathVariable(value="id") int id)
	{
		if(offerService.findOne(id) == null)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); //no existe
		}
		else
		{
			offerService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@RequestMapping(value="/notExpired", method = RequestMethod.GET)
	public ResponseEntity<List<Offer>> findOfferNotExpired() {
		return ResponseEntity.status(HttpStatus.OK).body(offerService.findOfferNotExpired());
	}
	
	
	
}
