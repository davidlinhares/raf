package com.wf.domain.factory;

import com.wf.domain.ReservationDescription;

public class SimpleReservationDescriptionFactory implements ReservationDescriptionFactory {

	@Override
	public ReservationDescription createReservationDescription(Class klass) {
		if(ReservationDescription.class.isAssignableFrom(klass.getClass())) {
			try {
				Object object = klass.newInstance();
				ReservationDescription rentee = (ReservationDescription) object;
				return rentee;
			} catch(Exception e) {
				// IllegalAccessException, InstantiationException
				System.out.println(e);
				return null;
			}
		} else {
			System.out.println("Class " + klass.getName() + " not Assignable From " + ReservationDescription.class.getName());
			return null;
		}
	}

}
