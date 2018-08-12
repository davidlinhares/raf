package com.wf.domain;

@Reservable
public abstract class Rental {
	private Double priceRate = 0.0;
	private Double reservationTimeslot = 0.0;
	
	public Double getPriceRate() {
		return priceRate;
	}
	
	public Double getReservationTimeslot() {
		return reservationTimeslot;
	}
}
