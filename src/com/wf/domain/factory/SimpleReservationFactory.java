package com.wf.domain.factory;

import com.wf.domain.Reservation;

public class SimpleReservationFactory implements ReservationFactory {

	@Override
	public Reservation createReservation(Class klass) {
		if(Reservation.class.isAssignableFrom(klass.getClass())) {
			try {
				Object object = klass.newInstance();
				Reservation reservation = (Reservation) object;
				return reservation;
			} catch(Exception e) {
				// IllegalAccessException, InstantiationException
				System.out.println(e);
				return null;
			}
		} else {
			System.out.println("Class " + klass.getName() + " not Assignable From " + Reservation.class.getName());
			return null;
		}
	}

}
