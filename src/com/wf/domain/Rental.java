package com.wf.domain;

import com.wf.domain.annotation.Reservable;

@Reservable
public abstract class Rental {
	private Double priceRate = 0.0;
	private Double rentalTimeslot = 0.0;
	
	public Double getPriceRate() {
		return priceRate;
	}
	
	public void setPriceRate(Double priceRate) {
		this.priceRate = priceRate;
	}
	
	public Double getRentalTimeslot() {
		return rentalTimeslot;
	}
	
	public void setRentalTimeslot(Double rentalTimeslot) {
		this.rentalTimeslot = rentalTimeslot;
	}
	
	public abstract Rental clone();
}
