package com.project.promo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.promo.entity.Promo;
import com.project.promo.errorhandler.CommitFailedException;
import com.project.promo.errorhandler.DataNotFoundException;
import com.project.promo.errorhandler.UndefinedException;
import com.project.promo.repository.PromoRepository;
import com.project.promo.util.SuccessTemplateMessage;

@Service
public class PromoService{

	
	@Autowired
    private PromoRepository promoRepository;
	
	public List<Promo> getAll() {
		// TODO Auto-generated method stub
		return promoRepository.findAll();
	}
	
	public List<Promo> getPromo(Integer paymentTypeId, Integer restaurantId) {
		// TODO Auto-generated method stub
		return promoRepository.findByPaymentTypeIdAndRestaurantId(paymentTypeId, restaurantId);
	}
	
	public Promo createNew(Promo newData) throws CommitFailedException, UndefinedException {
		try {
			return promoRepository.save(newData);
		} catch (Exception e) {
			if (e.getMessage().contains("Error while committing")) {
				throw new CommitFailedException();
			} else {
				throw new UndefinedException(e.toString());
			}
		}
	}

	public Promo getById(Long id) throws DataNotFoundException {
		return promoRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
	}

	public Promo updateById(Promo updatedData, Long id) {

		return promoRepository.findById(id).map(data -> {
			updatedData.setId(id);
			data = updatedData;

			return promoRepository.save(data);
		}).orElseGet(() -> {
			return promoRepository.save(updatedData);
		});
	}

	public ResponseEntity<Object> deleteById(long id) throws DataNotFoundException {

		try {
			if (promoRepository.findById(id) != null) {
				promoRepository.deleteById(id);
			}
		} catch (Exception e) {
			throw new DataNotFoundException();
		}
		return new ResponseEntity<Object>(new SuccessTemplateMessage(), HttpStatus.OK);
	}

}
