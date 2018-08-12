package com.wf.domain.factory;

import com.wf.domain.ReservationDescription;

public interface ReservationDescriptionFactory {
	public ReservationDescription createReservationDescription(Class klass);
}
