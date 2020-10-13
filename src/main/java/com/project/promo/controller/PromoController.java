package com.project.promo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.promo.entity.Promo;
import com.project.promo.service.PromoService;
import com.project.promo.errorhandler.CommitFailedException;
import com.project.promo.errorhandler.DataNotFoundException;
import com.project.promo.errorhandler.UndefinedException;

@RestController
@RequestMapping("promo")
public class PromoController {
    @Autowired
    private PromoService promoService;

    @GetMapping
    public List<Promo> get(){

        return promoService.getAll();
    }
    
    @GetMapping("paymentTypeId/{paymentTypeId}/restaurantId/{restaurantId}")
	public @ResponseBody List<Promo> getPromo(@PathVariable Integer paymentTypeId, @PathVariable Integer restaurantId) {
		return promoService.getPromo(paymentTypeId, restaurantId);
	}
    
    @PostMapping("/create")
	public @ResponseBody Promo createNew(@RequestBody Promo promo) {
		try {
			return promoService.createNew(promo);
		} catch (CommitFailedException e) {
			throw new ResponseStatusException(e.getStatus(), e.getMessage());
		} catch (UndefinedException e) {
			throw new ResponseStatusException(e.getStatus(), e.getMessage());
		}
	}
    
    @GetMapping("/{id}/detail")
	public @ResponseBody Promo findById(@PathVariable Long id) {
		try {
			return promoService.getById(id);
		} catch (DataNotFoundException e) {
			throw new ResponseStatusException(e.getStatus(), e.getMessage());
		}
	}

	@PutMapping("/{id}/update-data")
	public @ResponseBody Promo updateData(@RequestBody Promo promo, @PathVariable Long id) {
		return promoService.updateById(promo, id);
	}

	@DeleteMapping("/{id}/delete-data")
	public @ResponseBody ResponseEntity<Object> deleteById(@PathVariable Long id) {
		try {
			return promoService.deleteById(id);

		} catch (DataNotFoundException e) {
			throw new ResponseStatusException(e.getStatus(), e.getMessage());
		}

	}
    
    
}

