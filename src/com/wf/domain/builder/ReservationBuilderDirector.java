package com.wf.domain.builder;

import com.wf.domain.Rental;
import com.wf.domain.Rentee;
import com.wf.domain.Reservation;
import com.wf.domain.ReservationDescription;

public class ReservationBuilderDirector {

	private Builder builder = null;
	
	public ReservationBuilderDirector(Builder builder) {
		this.builder = builder;
	}
	
	public void constructReservation(Rental rental, Rentee rentee, ReservationDescription reservationDescription) {
		builder.newReservation();
		if(rental != null) {
			builder.buildRental(rental);
		}
		if(rentee != null) {
			builder.buildRentee(rentee);
		}
		if(reservationDescription != null) {
			builder.buildReservationDescription(reservationDescription);
		}
	}
	
	public Reservation getReservation() {
		return builder.getReservation();
	}
}
