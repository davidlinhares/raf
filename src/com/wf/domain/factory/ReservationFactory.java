package com.wf.domain.factory;

import com.wf.domain.Reservation;

public interface ReservationFactory {
	public Reservation createReservation(Class klass);
}
