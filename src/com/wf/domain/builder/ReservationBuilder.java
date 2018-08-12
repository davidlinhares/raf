package com.wf.domain.builder;

import com.wf.domain.Rental;
import com.wf.domain.Rentee;
import com.wf.domain.Reservation;
import com.wf.domain.ReservationDescription;

public class ReservationBuilder implements Builder {
	
	private Reservation reservation;
	
	public ReservationBuilder() {
		reservation = new Reservation();
	}

	@Override
	public void newReservation() {
		reservation = new Reservation();
	}

	@Override
	public void buildRental(Rental rental) {
		this.reservation.setRental(rental);
	}

	@Override
	public void buildRentee(Rentee rentee) {
		this.reservation.setRentee(rentee);
	}

	@Override
	public void buildReservationDescription(ReservationDescription reservationDescription) {
		this.reservation.setReservationDescription(reservationDescription);
	}

	@Override
	public Reservation getReservation() {
		return reservation;
	}

}
