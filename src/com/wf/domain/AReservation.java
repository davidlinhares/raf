package com.wf.domain;

public abstract class AReservation {
	
	public final Double getFinalPrice() {
		Double totalPrice = 0.0;
		totalPrice = getTotalPrice() - getDiscount();
		return totalPrice;
	}
	
	public abstract Double getDiscount();
	
	public abstract Double getTotalPrice();
}
