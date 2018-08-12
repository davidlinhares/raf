package com.wf.domain.builder;

import com.wf.domain.Rental;
import com.wf.domain.Rentee;
import com.wf.domain.Reservation;
import com.wf.domain.ReservationDescription;

public interface Builder {
	public void newReservation();
	public void buildRental(Rental rental);
	public void buildRentee(Rentee rentee);
	public void buildReservationDescription(ReservationDescription reservationDescription);
	public Reservation getReservation();
}
