package com.project.promo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.project.promo.entity.Promo;


@Repository
@Transactional(readOnly = true)
public interface PromoRepository  extends JpaRepository<Promo, Long> {
	
	
	@Query(value="SELECT * FROM promo p WHERE p.payment_type_id = :paymentTypeId and p.restaurant_id = :restaurantId", nativeQuery=true) 
	List<Promo> findByPaymentTypeIdAndRestaurantId(@Param("paymentTypeId") Integer paymentTypeId, @Param("restaurantId") Integer restaurantId);
	
	
}
