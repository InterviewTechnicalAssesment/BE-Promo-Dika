package com.project.promo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "promo")
public class Promo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "promo_type")
    private String promo_type;

    @Column(name = "payment_type_id")
    private Integer paymentTypeId;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "req_bonus_menu_id")
    private Integer reqBonusMenuId;
    
    @Column(name = "req_bonus_menu_id_qty")
    private Integer reqBonusMenuIdQty;
    
    @Column(name = "menu_id_bonus")
    private Integer menuIdBonus;

    @Column(name = "menu_id_bonus_qty")
    private Integer menuIdBonusQty;

    @Column(name = "req_discount_min")
    private Double reqDiscountMin;
    
    @Column(name = "discount")
    private Integer discount;

    @Column(name = "discount_max")
    private Double discountMax;
    
}
